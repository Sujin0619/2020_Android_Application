package edu.skku.map.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference tempDatabase = FirebaseDatabase.getInstance().getReference("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView5);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String text = intent.getStringExtra("username");
        final EditText Password = findViewById(R.id.editText2);
        final EditText ID = findViewById(R.id.editText);
        ID.setText(text);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, PersonalPosts.class);
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
                                    intent.putExtra("password",snapshot.child("password").getValue().toString());

                                    intent.putExtra("birth",snapshot.child("birth").getValue().toString());
                                    intent.putExtra("name",snapshot.child("name").getValue().toString());
                                    intent.putExtra("email",snapshot.child("email").getValue().toString());
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
}
