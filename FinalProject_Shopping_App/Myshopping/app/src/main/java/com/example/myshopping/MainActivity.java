package com.example.myshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference tempDatabase = FirebaseDatabase.getInstance().getReference("users");
    //private EditText ID;
    //private EditText PA;
    //private ArrayList<String> IDPA = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView5);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sign_in.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String text = intent.getStringExtra("username");
        final EditText ID = findViewById(R.id.editText);
        final EditText Password = findViewById(R.id.editText2);
        //ID.setText(text);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                final String realID = ID.getText().toString();
                final int[] checker = {0};
                final String realPassword = Password.getText().toString();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren())
                        {

                            if(snapshot.getKey().equals(realID))
                            {

                                //Toast.makeText(getApplicationContext(), ,Toast.LENGTH_LONG).show();

                                if(snapshot.child("password").getValue().toString().equals(realPassword))
                                {
                                    intent.putExtra("ID",realID);
                                    startActivity(intent);
                                    checker[0]=1;
                                    break;
                                }

                                Toast.makeText(getApplicationContext(),"wrong password" ,Toast.LENGTH_LONG).show();

                            }

                        }
                        if(checker[0]==0)
                            Toast.makeText(getApplicationContext(), "wrong username", Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //startActivity(intent);
            }
        });

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
         //   Toast.makeText(this, "세로모드", Toast.LENGTH_SHORT).show();
        }

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

           // Toast.makeText(this, "가로모드", Toast.LENGTH_SHORT).show();
        }

    }

}

