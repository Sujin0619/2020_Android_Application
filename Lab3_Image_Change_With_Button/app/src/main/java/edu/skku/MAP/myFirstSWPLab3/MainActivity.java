package edu.skku.MAP.myFirstSWPLab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    int j = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.textView);

        Button btn1 = (Button)findViewById(R.id.button);
        btn1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                i = 1 - i;
                if(i == 0){
                    text.setText("Sujin Kim");
                }
                if(i == 1){
                    text.setText("2017310435");
                }
            }
        });

        Button btn2 = (Button)findViewById(R.id.button2);
        final ImageView img1 = (ImageView)findViewById(R.id.imageView);
        btn2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int index = j % 3;
                if(index == 0){
                    img1.setImageResource(R.drawable.chicken);
                }
                if(index == 1){
                    img1.setImageResource(R.drawable.pizza);
                }
                if(index == 2){
                    img1.setImageResource(R.drawable.hotdog);
                }
                j++;
            }
        });
    }
}
