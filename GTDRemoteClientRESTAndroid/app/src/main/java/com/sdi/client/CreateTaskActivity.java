package com.sdi.client;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sdi.client.dto.Task;

import java.util.Calendar;
import java.util.Date;

import alb.util.date.DateUtil;


public class CreateTaskActivity extends AppCompatActivity {

    Task t;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);

        Calendar c = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText planned = (EditText) findViewById(R.id.createPlanned);
                planned.setText(dayOfMonth + "/" + (month+1) + "/" + year);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        EditText txtDate = (EditText) findViewById(R.id.createPlanned);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    datePickerDialog.show();
            }
        });

    }


    public void create(View view){
        EditText title = (EditText) findViewById(R.id.createTitle);
        EditText comment = (EditText) findViewById(R.id.createComment);
        EditText planned = (EditText) findViewById(R.id.createPlanned);
        Date toDate = DateUtil.fromString(planned.getText().toString());
        EditText catId = (EditText) findViewById(R.id.createCatId);

        t = new Task();
        t.setTitle(title.getText().toString());
        t.setComments(comment.getText().toString());
        t.setCreated(new Date());
        t.setPlanned(toDate);
        t.setCategoryId(Long.parseLong(catId.getText().toString()));

        finish();
    }

    @Override
    public void finish() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("task", t);
        Intent i = new Intent();
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        super.finish();
    }

}
