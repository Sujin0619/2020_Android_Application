package edu.skku.map.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Context context = this;

        AsyncTask<Double, Double, String> asyncTask = new AsyncTask<Double, Double, String>() {

            double x,y,pi;
            double point_in_circle, total_point;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Toast.makeText(context, "Start AsyncTsk", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onProgressUpdate(Double... values) {
                super.onProgressUpdate(pi);
                textView.setText(Double.toString(pi));
            }

            @Override
            protected String doInBackground(Double... doubles) {
                for (int i=1; i<100000000; i++){
                    try{
                        x=Math.random();
                        y=Math.random();

                        if (x*x + y*y <= 1)
                        {
                            point_in_circle++;
                        }
                        total_point++;

                        pi = (point_in_circle/total_point)*4;

                        publishProgress(pi);
                        double diff = 3.141592 - pi;
                        if (diff >= 0 )
                        {
                            if(diff <0.000001)
                            {
                                return null;
                            }
                        }
                        else
                        {
                            if(diff> -0.000001)
                            {
                                return null;
                            }
                        }

                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        asyncTask.execute();
    }
}
