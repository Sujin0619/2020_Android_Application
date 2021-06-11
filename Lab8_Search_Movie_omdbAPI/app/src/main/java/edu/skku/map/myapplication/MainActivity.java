package edu.skku.map.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.editText);
        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView textView1 = (TextView)findViewById(R.id.textView1);
        final TextView textView2 = (TextView)findViewById(R.id.textView2);
        final TextView textView3 = (TextView)findViewById(R.id.textView3);
        final TextView textView4 = (TextView)findViewById(R.id.textView4);
        final TextView textView5 = (TextView)findViewById(R.id.textView5);
        final TextView textView6 = (TextView)findViewById(R.id.textView6);

        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://www.omdbapi.com").newBuilder();
                urlBuilder.addQueryParameter("apikey", "ea9dfe92");
                urlBuilder.addQueryParameter("t", editText.getText().toString());


                String url = urlBuilder.build().toString();

                Request req = new Request.Builder().url(url).build();

                client.newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String myResponse = response.body().string();

                        Gson gson = new GsonBuilder().create();
                        final DataModel data = gson.fromJson(myResponse, DataModel.class);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText("Title : " + data.getTitle());
                                textView1.setText("Year : " + data.getYear());
                                textView2.setText("Released Date : " + data.getReleased());
                                textView3.setText("Runtime : " + data.getRuntime());
                                textView4.setText("Genre : " + data.getGenre());
                                textView5.setText("IMDB Rates : " + data.getImdbRating());
                                textView6.setText("Metascore : " + data.getMetascore());

                            }
                        });
                    }
                });



            }

        });

    }
}
