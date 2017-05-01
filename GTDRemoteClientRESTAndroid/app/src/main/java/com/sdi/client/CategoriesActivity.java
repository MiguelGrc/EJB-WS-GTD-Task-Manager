package com.sdi.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.sdi.client.dto.Category;

import java.util.ArrayList;

/**
 * Created by MIGUEL on 01/05/2017.
 */

public class CategoriesActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            Bundle arrayListBundle = extras.getBundle("extra");
            ArrayList<Category> catList = (ArrayList<Category>) arrayListBundle.getSerializable("list");

            tableLayout = (TableLayout) findViewById(R.id.tableLayout);

            if (catList != null) {
                for (Category c : catList) {
                    View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item_cat, null, false);
                    TextView catId = (TextView) tableRow.findViewById(R.id.catId);
                    TextView catName = (TextView) tableRow.findViewById(R.id.catName);

                    catId.setText(c.getId().toString());
                    catName.setText(c.getName());

                    tableLayout.addView(tableRow);
                }
            }
        }
    }

}
