package com.sdi.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;

/**
 * Created by MIGUEL on 30/04/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

    //Cuando el usuario pulsa el botón de login
    public void loginUser(View view) {
        EditText userET = (EditText) findViewById(R.id.user);
        EditText passwordET = (EditText) findViewById(R.id.password);

        String user = userET.getText().toString();
        String password = passwordET.getText().toString();
        RequestParams params = new RequestParams();

        navigateToHomeActivity(user, password);
    }

    public void navigateToHomeActivity(String user, String password){
        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.putExtra("user", user);
        homeIntent.putExtra("password", password);
        startActivity(homeIntent);
    }

}
