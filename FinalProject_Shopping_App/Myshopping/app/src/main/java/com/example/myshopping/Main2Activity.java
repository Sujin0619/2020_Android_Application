package com.example.myshopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
        Button src;
        final int[] selecteditem = {0};
        final int[] selecteditem1 = {0};
        final String[] items = new String[]{"치마","바지","반팔티","셔츠"};
        final String[] items1 = new String[]{"면","린넨","청","가죽"};
       private ArrayList<String> currentStates = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyLog().build());
        final Button type = findViewById(R.id.button2);
        final Button src = findViewById(R.id.button3);

        if(savedInstanceState != null)
        {
            currentStates=savedInstanceState.getStringArrayList("CS");
            type.setText(currentStates.get(0));
            src.setText(currentStates.get(1));
           // currentStates.remove(0);
           // currentStates.remove(1);


        }
        final Button search = findViewById(R.id.button4);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = items1[selecteditem1[0]]+items[selecteditem[0]];
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("PRODUCT_NAME", productName);
                startActivity(intent);
            }
        });
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog. setTitle("옷의 종류를 고르세요")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selecteditem[0]=which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type.setText(items[selecteditem[0]]);
                                //Toast.makeText(Main2Activity.this, items[selecteditem[0]], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Main2Activity.this,
                                        "취소했습니다"
                                ,Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();

            }

        });

        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog. setTitle("옷의 재질을 고르세요")
                        .setSingleChoiceItems(items1, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selecteditem1[0]=which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                src.setText(items1[selecteditem1[0]]);
                                //Toast.makeText(Main2Activity.this, items1[selecteditem1[0]], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Main2Activity.this,
                                        "취소했습니다"
                                        ,Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Button bt2 = findViewById(R.id.button2);
        Button bt3 = findViewById(R.id.button3);
         currentStates.add(bt2.getText().toString());
        currentStates.add(bt3.getText().toString());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
           //Toast.makeText(this, "세로모드", Toast.LENGTH_SHORT).show();
        }

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
           //Toast.makeText(this, "가로모드", Toast.LENGTH_SHORT).show();
        }

    }
    public String getNaverSearch(String keyword) {

        String clientID = "Yo3jGXgyuzqOSBOCuUCG";
        String clientSecret = "0frgBtucsy";
        StringBuffer sb = new StringBuffer();

        try {


            String text = URLEncoder.encode(keyword, "UTF-8");



            String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query=" + text + "&display=5" + "&start=1";


            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientID);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            String tag;
            //inputStream으로부터 xml값 받기
            xpp.setInput(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); //태그 이름 얻어오기

                        if (tag.equals("item")) ; //첫번째 검색 결과
                        else if (tag.equals("title")) {

                            sb.append("제목 : ");

                            xpp.next();


                            sb.append(xpp.getText());
                            sb.append("\n");

                        } else if (tag.equals("link")) {

                            sb.append("링크 : ");
                            xpp.next();


                            sb.append(xpp.getText());
                            sb.append("\n");


                        }
                        break;
                }

                eventType = xpp.next();


            }

        } catch (Exception e) {
            return e.toString();

        }

        return sb.toString();
    }

    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("CS",currentStates);
    }
}
