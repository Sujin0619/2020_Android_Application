package edu.skku.map.myfirstswplab11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mPostReference;
    String title = "", owner = "", contents="";
    String sort = "memo";
    EditText titleET, ownerET, contentsET;
    Button btn;
    ListView listView;

    ArrayList<String> data;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<String>();
        titleET = (EditText)findViewById(R.id.titleet);
        ownerET = (EditText)findViewById(R.id.owneret);
        contentsET = (EditText)findViewById(R.id.contentset);
        btn = (Button)findViewById(R.id.button);
        listView = (ListView)findViewById(R.id.datalist);

        mPostReference = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=titleET.getText().toString();
                owner=ownerET.getText().toString();
                contents=contentsET.getText().toString();

                if((title.length()*owner.length()*contents.length())==0){
                    Toast.makeText(MainActivity.this, "Data is missing", Toast.LENGTH_SHORT).show();
                }

                else{
                    postFirebaseDatabase(true);
                }

            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);

        getFirebaseDatabase();
    }

    public void getFirebaseDatabase(){
        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("onDataChange", "Data is Updated");
                data.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String key = postSnapshot.getKey();
                    FirebasePost get = postSnapshot.getValue(FirebasePost.class);
                    String[] info = {get.title, get.owner, get.contents};
                    String result = info[0] + " : "+info[1] + "(" + info[2] + ")";

                    data.add(result);
                    Log.d("getFirebaseDatabase", "key: " + key);
                    Log.d("getFirebaseDatabase", "info: "+info[0]+info[1]+info[2]);
                }

                arrayAdapter.clear();
                arrayAdapter.addAll(data);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mPostReference.child("memo_list").addValueEventListener(postListener);
    }

    public void postFirebaseDatabase(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        if(add){
            FirebasePost post = new FirebasePost(title, owner, contents);
            postValues = post.toMap();
        }

        childUpdates.put("/memo_list/"+title, postValues);
        mPostReference.updateChildren(childUpdates);
        clearET();
    }

    public void clearET(){
        titleET.setText("");
        ownerET.setText("");
        contentsET.setText("");
        title="";
        owner="";
        contents="";
    }
}
