package edu.skku.map.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class PersonalPosts extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Context context = this;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_posts);
        mDatabase = FirebaseDatabase.getInstance().getReference("Post");
        Intent intent= getIntent();

        final String id = intent.getStringExtra("ID");//tnwlsl
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView tV = findViewById(R.id.textView13);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String ID = dataSnapshot.child("Id").getValue(String.class);
                    String contents = dataSnapshot.child("contents").getValue(String.class);
                    String tags = dataSnapshot.child("tags").getValue(String.class);
                    tV.setText(ID + "\n" + contents + "\n" + tags);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalPosts.this, AddPost.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        final String name = intent.getStringExtra("name");
        final String birth = intent.getStringExtra("birth");
        final String email = intent.getStringExtra("email");
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
                TextView text = findViewById(R.id.textView);
                text.setText("dsfsd");

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.FullName){
                    Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.Birthday){
                    Toast.makeText(context, birth, Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.email){
                    Toast.makeText(context, email, Toast.LENGTH_SHORT).show();
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




}
