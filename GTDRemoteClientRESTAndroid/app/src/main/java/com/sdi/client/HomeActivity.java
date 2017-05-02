package com.sdi.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.sdi.client.dto.Category;
import com.sdi.client.dto.Task;
import com.sdi.client.service.GTDRestService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeActivity extends AppCompatActivity {

    GTDRestService client;
    TableLayout tableLayout;
    String user;
    String password;
    private static final int CREATE_TASK_ACTIVITY_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        user = getIntent().getStringExtra("user");
        password = getIntent().getStringExtra("password");

        View inflatedView = getLayoutInflater().inflate(R.layout.categories, null);
        tableLayout = (TableLayout) inflatedView.findViewById(R.id.tableLayout);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request().newBuilder();
                        String basicAuthorization = getBasicAuthorization();
                        ongoing.addHeader("Authorization", basicAuthorization);
                        return chain.proceed(ongoing.build());
                    }
                })
                .build();

        //Para parsear correctamente los Dates que vienen del JSON en formato Unix
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .setDateFormat("yyyy-MM-dd")
                .create();

        //No podemos acceder directamente a localhost desde el emulador. Debemos hacerlo al host con 10.0.2.2
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8280/GTDTaskManagerWeb/rest/")    //A la hora de compilar a APK, cambiar la IP de 10.0.2.2 a la del PC
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .build();

        client = retrofit.create(GTDRestService.class);

    }

    private String getBasicAuthorization(){
        String token = user + ":" + password;
        try {
            //Importante usar NO_WRAP para eliminar posibles saltos de linea que tirarían excepciones
            return "Basic " + Base64.encodeToString(token.getBytes("UTF-8"), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Cannot encode with UTF-8", e);
        }
    }

    //Al pulsar el botón de mostrar categorías
    public void showCats(View view){
        Call<List<Category>> call = client.findCategoriesByUserId();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.code() == 200) {
                    List<Category> result = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (ArrayList<Category>) result);
                    Intent i = new Intent(getApplicationContext(), CategoriesActivity.class);
                    i.putExtra("extra", bundle);
                    startActivity(i);
                }

                checkCodes(response.code());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable throwable) {
                failureMessage();
            }
        });
    }

    //Al pulsar el botón de mostrar tareas
    public void showTasks(View view){
        EditText cat = (EditText) findViewById(R.id.cat);
        Call<List<Task>> call = client.findDelayedTasksByCategoryId(Long.parseLong(cat.getText().toString()));
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if(response.code() == 200) {
                    List<Task> result = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (ArrayList<Task>) result);
                    Intent i = new Intent(getApplicationContext(), TasksActivity.class);
                    i.putExtra("extra", bundle);
                    startActivity(i);
                }

                checkCodes(response.code());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable throwable) {
                failureMessage();
            }
        });
    }

    //Al pulsar el botón de marcar como finalizada
    public void markAsFinished(View view){
        EditText task = (EditText) findViewById(R.id.task);
        Call<Void> call = client.markTaskAsFinished(Long.parseLong(task.getText().toString()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200)
                    Toast.makeText(getApplicationContext(),"Tarea marcada como finalizada", Toast.LENGTH_SHORT).show();

                checkCodes(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                failureMessage();
            }
        });
    }

    //Al pulsar el botón de crear tarea
    public void createTask(View view){
        Intent i = new Intent(getApplicationContext(), CreateTaskActivity.class);
        startActivityForResult(i, CREATE_TASK_ACTIVITY_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREATE_TASK_ACTIVITY_RESULT_CODE){
            if(resultCode == RESULT_CANCELED)
                Toast.makeText(getApplicationContext(),"[Error] Algo no ha salido como se esperaba", Toast.LENGTH_LONG).show();
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Task t = (Task) bundle.getSerializable("task");
                if(t == null)   //Si volvemos atrás. No nos interesa llegar a hacer la petición si no se han introducido datos y pulsado "Crear"
                    return;

                Call<Void> call = client.createTask(t);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200)
                            Toast.makeText(getApplicationContext(), "Tarea creada con éxito", Toast.LENGTH_SHORT).show();

                        checkCodes(response.code());
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        failureMessage();
                    }
                });
            }
        }
    }

    private void checkCodes(int code){
        if(code == 401)
            Toast.makeText(getApplicationContext(),"[Error] Acceso denegado (pruebe a logearse de nuevo)", Toast.LENGTH_SHORT).show();
        else if(code == 403)
            Toast.makeText(getApplicationContext(),"[Error] Recurso inaccesible", Toast.LENGTH_SHORT).show();
    }

    private void failureMessage(){
        Toast.makeText(getApplicationContext(),"[Error] Error interno del servidor. Podría estar fuera de servicio", Toast.LENGTH_SHORT).show();
    }

}
