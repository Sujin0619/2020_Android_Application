package com.example.myshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Main3Activity extends AppCompatActivity {

    final static String NAVER_SEARCH_KEY = "";

    final static int screen_size = 5;

    int start = 1;
    ListView mProductListview;
    PriceListAdapter adapter;
    PriceListParser naverSearchPaser;
    ArrayList<PriceDataStruct> xmlData_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyLog().build());

        setContentView(R.layout.activity_main3);

        mProductListview = (ListView) findViewById(R.id.productListview);

        Intent intent = getIntent();

        final String productName = intent.getStringExtra("PRODUCT_NAME");
        naverSearchPaser = new PriceListParser(productName);

        getShopList(productName);
        mProductListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int Last = adapter.getCount();

                if( (Last - 1) == arg2 )
                {

                    start += screen_size;
                    getShopList(productName);
                }
                else
                {
                    String[] StringArrayData = StringArrayData(arg2);
                    Intent intent = new Intent (Main3Activity.this,Viewdetail.class);
                    Bundle myData = new Bundle();
                    myData.putStringArray("key",StringArrayData);
                    intent.putExtras(myData);
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    public void getShopList(String searchTxt) {

        xmlData_array = naverSearchPaser.GetXmlData(searchTxt, screen_size, start);
        adapter = new PriceListAdapter(this, R.layout.listitem, xmlData_array);
        mProductListview.setAdapter(adapter);
    }

    public String[] StringArrayData(int arg2) {
        PriceDataStruct xmlData = xmlData_array.get(arg2);
        String[] StringArrayData = new String[6];
        StringArrayData[0] = xmlData.getTitle();
        StringArrayData[1] = xmlData.getMallName();
        StringArrayData[2] = xmlData.getLink();
        StringArrayData[3] = String.valueOf(xmlData.getLprice());
        StringArrayData[4] = String.valueOf(xmlData.getHprice());
        StringArrayData[5] = xmlData.getImage();
        return StringArrayData ;
    }
}

