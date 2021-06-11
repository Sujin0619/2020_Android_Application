package edu.skku.map.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        final Intent intent = getIntent();
        String text = intent.getStringExtra("name");

        TextView textView = findViewById(R.id.textView3);
        textView.setText(text);

        Button button = findViewById(R.id.button); //search button id가 button 임
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text1 = intent.getStringExtra("keyword");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, text1);

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });
    }
}
