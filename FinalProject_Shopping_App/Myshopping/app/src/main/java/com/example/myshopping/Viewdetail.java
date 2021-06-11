package com.example.myshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Viewdetail extends AppCompatActivity{

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView5;

    ImageView imageView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail);

        textView1 =(TextView)findViewById(R.id.TextView01);
        textView2 =(TextView)findViewById(R.id.TextView02);
        textView3 =(TextView)findViewById(R.id.TextView03);
        textView5 =(TextView)findViewById(R.id.TextView05);

        imageView = (ImageView)findViewById(R.id.ImageView01);


        Intent intent = getIntent();
        Bundle myBundle = intent.getExtras();
        String StringArrayData[] = myBundle.getStringArray("key");


        URL url;
        try {
            url = new URL(StringArrayData[5]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), 10240);
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imageView.setImageBitmap(bm);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textView1.setText(StringArrayData[0]);
        textView2.setText(StringArrayData[1]);
        if(StringArrayData[4].equals("0"))
        {
            textView3.setText("판매 가격:" +StringArrayData[3]+"원");//가격차이가 없을경우 0원으로 출력

        }
        else
        {
            textView3.setText("최저 가격:" +StringArrayData[3]+"원"+" 최고 가격:" +StringArrayData[4]+"원");//가격차이가 없을경우 0원으로 출력

        }
        textView5.setText(Html.fromHtml("<a href='"+StringArrayData[2]+"'>" + "구매하러 가기" + "</a>"));
        textView5.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
