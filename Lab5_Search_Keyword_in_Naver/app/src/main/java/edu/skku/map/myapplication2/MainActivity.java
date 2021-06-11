package edu.skku.map.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit); //submit 버튼 id 도 submit 임
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText edit = findViewById(R.id.editText);
                EditText edit2 = findViewById(R.id.editText2);
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.putExtra("name", edit.getText().toString());
                intent.putExtra("keyword", edit2.getText().toString());
                startActivity(intent);
                //submit 버튼 누르면 첫 번째 edit text 에서 이름 받아서 another activity 에서 보여주는 것 -> explicit intent
                //submit 버튼 누르면 두 번째 edit text 에서 받아서 넘기고 another activity 에서 버튼 누르면 그걸 웹에서 검색 -> implicit intent
                //한번에 쓰기
            }
        });
    }


}
