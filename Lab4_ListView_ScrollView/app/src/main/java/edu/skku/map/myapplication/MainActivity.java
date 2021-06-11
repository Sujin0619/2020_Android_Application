package edu.skku.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

import edu.skku.map.myapplication.R;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.textView);
        container = (LinearLayout)findViewById(R.id.container);

        Button btn1 = (Button) findViewById(R.id.first_button);
        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                text.setText("First");

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub_layout, container, true);

                ListView listView = (ListView)findViewById(R.id.listView);
                ArrayList<String> data = new ArrayList<String>();

                data.add("0");
                data.add("1");
                data.add("2");
                data.add("3");
                data.add("4");
                data.add("5");
                data.add("6");
                data.add("7");
                data.add("8");
                data.add("9");
                data.add("10");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1,data);

                listView.setAdapter(adapter);
            }
        });

        Button btn2 = (Button) findViewById(R.id.second_button);
        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                text.setText("Second");

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub_layout, container, true);

                ListView listView = (ListView)findViewById(R.id.listView);
                ArrayList<String> data = new ArrayList<String>();

                data.add("2");
                data.add("4");
                data.add("8");
                data.add("16");
                data.add("32");
                data.add("64");
                data.add("128");
                data.add("256");
                data.add("512");
                data.add("1024");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1,data);

                listView.setAdapter(adapter);
            }
        });

        Button btn3 = (Button) findViewById(R.id.third_button);
        btn3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                text.setText("Third");

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub_layout, container, true);

                ListView listView = (ListView)findViewById(R.id.listView);
                ArrayList<String> data = new ArrayList<String>();

                data.add("2017310435");
                data.add("Sujin Kim");
                data.add("Department of Economics");
                data.add("College of Economics");
                data.add("Sungkyunkwan University");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1,data);

                listView.setAdapter(adapter);
            }
        });

    }

}
