package edu.skku.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;


public class Main2Activity extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){//id, password, name , birth, email
                EditText id = (EditText)findViewById(R.id.editText3);
                EditText password= (EditText)findViewById(R.id.editText4);
                EditText name = (EditText)findViewById(R.id.editText5);
                EditText birth = (EditText)findViewById(R.id.editText6);
                EditText email = (EditText)findViewById(R.id.editText7);
                writeNewUser(id.getText().toString() ,password.getText().toString() ,name.getText().toString(),birth.getText().toString() , email.getText().toString() );
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                //intent.putExtra("username", editText3.getText().toString());
                startActivity(intent);
                //submit 버튼 누르면 첫 번째 edit text 에서 이름 받아서 another activity 에서 보여주는 것 -> explicit intent
                //submit 버튼 누르면 두 번째 edit text 에서 받아서 넘기고 another activity 에서 버튼 누르면 그걸 웹에서 검색 -> implicit intent
                //한번에 쓰기
            }
        });
    }
    private void writeNewUser(String userId, String password, String name,String birth, String email) {
        User user = new User( password, name, birth, email);
        mDatabase.child("users").child(userId).setValue(user);
    }
    @IgnoreExtraProperties
    public class User {
        public String password;
        public String name;
        public String birth;
        public String email;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String password, String name, String birth, String email) {
            this.password = password;
            this.name = name;
            this.birth = birth;
            this.email = email;
        }

    }
}
