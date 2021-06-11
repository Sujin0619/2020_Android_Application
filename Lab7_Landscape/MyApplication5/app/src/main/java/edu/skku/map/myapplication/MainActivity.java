package edu.skku.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;
    String text_content;

    public void println(String data){
        textView.append(data+"\n");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float curX = event.getX();
                float curY = event.getY();

                if (action == event.ACTION_DOWN) {
                    println("Touch event occur : " + curX + ", " + curY);
                } else if (action == event.ACTION_MOVE) {
                    println("finger move: " + curX + ", " + curY);
                } else if (action == event.ACTION_UP) {
                    textView.setText("");
                    println("Touch event end: " + curX + ", " + curY);
                }
                return true;

            }
        });

        detector=new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown event occur.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onSingleTapUp event occur.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp event occur.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll event occur: "+distanceX+", "+distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress event occur.");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling event occur: "+velocityX+", "+velocityY);

                return true;
            }

        });

        text_content = textView.getText().toString();
        textView.setText(text_content);

        if (savedInstanceState!=null){
            text_content = savedInstanceState.getString("text_context");
            textView.setText(text_content);
        }

        View view3 = findViewById(R.id.view3);
        view3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });


    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        text_content = textView.getText().toString();
        outState.putString("text_content", text_content);
    }

}
