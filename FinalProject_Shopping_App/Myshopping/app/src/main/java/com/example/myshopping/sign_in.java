package com.example.myshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;


public class sign_in extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
    private DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent intent=getIntent();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final EditText id = (EditText)findViewById(R.id.editText_id);
                final EditText password= (EditText)findViewById(R.id.editText_pw);
                final EditText name = (EditText)findViewById(R.id.editText_name);

                final Intent intent = new Intent(sign_in.this, MainActivity.class);
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            if(snapshot.getKey().equals(id.getText().toString()))
                            {
                                Toast.makeText(getApplicationContext(),"이미 존재하는 ID 입니다." ,Toast.LENGTH_LONG).show();
                                break;


                            }
                            else
                            {
                                writeNewUser(id.getText().toString() ,password.getText().toString() ,name.getText().toString() );
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                            startActivity(intent);
                    }
                });

            }
        });
    }
    private void writeNewUser(String userId, String password, String name) {
        User user = new User( password, name);
        mDatabase2.child("users").child(userId).setValue(user);
    }
    @IgnoreExtraProperties
    public class User {
        public String password;
        public String name;

        public User() {
            //
        }

        public User(String password, String name) {
            this.password = password;
            this.name = name;
        }

    }
}
