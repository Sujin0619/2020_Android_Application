package edu.skku.map.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPost extends AppCompatActivity {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    private DrawerLayout drawerLayout;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        final EditText contents = findViewById(R.id.editText9);
        final EditText tags = findViewById(R.id.editText10);

        final Intent intent = getIntent();

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contents.getText().toString();
                String tag = tags.getText().toString();
                String id = intent.getStringExtra("id");
                writeNewPost(id,content,tag);
                Intent intent = new Intent(AddPost.this, PersonalPosts.class);
                intent.putExtra("content",content);
                intent.putExtra("tag",  tag);
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //뒤로가기 버튼 이미지 지정

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.FullName){
                    Toast.makeText(context, title + ": 이름", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.Birthday){
                    Toast.makeText(context, title + ": 생일", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.email){
                    Toast.makeText(context, title + ": 이메일", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void writeNewPost(String password, String name,String birth) {
        Post post = new Post( password, name, birth);
        mDatabase.child("post").child(password).setValue(post);
    }

    public class Post {
        public String Id;
        public String contents;
        public String tags;

        public Post() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Post(String password, String name, String birth) {
            this.Id = password;
            this.contents = name;
            this.tags = birth;
        }

    }

}
