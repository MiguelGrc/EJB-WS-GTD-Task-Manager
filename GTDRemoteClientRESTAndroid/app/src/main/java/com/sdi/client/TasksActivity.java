package com.sdi.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.sdi.client.dto.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Bundle arrayListBundle = extras.getBundle("extra");
            ArrayList<Task> taskList = (ArrayList<Task>) arrayListBundle.getSerializable("list");

            tableLayout = (TableLayout) findViewById(R.id.tableLayout);

            if(taskList != null) {
                for (Task t : taskList) {
                    View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item_task, null, false);
                    TextView taskId = (TextView) tableRow.findViewById(R.id.taskId);
                    TextView taskTitle = (TextView) tableRow.findViewById(R.id.taskTitle);
                    TextView taskCreated = (TextView) tableRow.findViewById(R.id.taskCreated);
                    TextView taskPlanned = (TextView) tableRow.findViewById(R.id.taskPlanned);
                    TextView taskIdCat = (TextView) tableRow.findViewById(R.id.taskIdCat);
                    TextView taskComments = (TextView) tableRow.findViewById(R.id.taskComments);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    taskId.setText(t.getId().toString());
                    taskTitle.setText(t.getTitle());
                    taskCreated.setText(t.getCreated() != null ? sdf.format(t.getCreated()) : "");
                    taskPlanned.setText(t.getPlanned() != null ? sdf.format(t.getPlanned()) : "");
                    taskIdCat.setText(t.getCategoryId().toString());
                    taskComments.setText(t.getComments());

                    tableLayout.addView(tableRow);
                }
            }
        }
    }
}
