package com.example.myshopping;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import com.example.myshopping.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PriceListAdapter extends ArrayAdapter<Object> {

    private ArrayList<PriceDataStruct> items;

    View v;

    public PriceListAdapter(Context context, int textViewResourceId,
                            ArrayList items) {
        super(context, textViewResourceId, items);
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyLog().build());

        v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listitem, null);
        }

        int Last = this.getCount();
        if( (Last - 1) == position ) {
            TextView tv1 = (TextView) v.findViewById(R.id.dataItem01);
            TextView tv2 = (TextView) v.findViewById(R.id.dataItem02);
            TextView tv3 = (TextView) v.findViewById(R.id.dataItem03);
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
        }
        else {

            final PriceDataStruct xmlData = (PriceDataStruct) items.get(position);
            if (xmlData != null) {

                final TextView tv1 = (TextView) v.findViewById(R.id.dataItem01);
                final TextView tv2 = (TextView) v.findViewById(R.id.dataItem02);
                final TextView tv3 = (TextView) v.findViewById(R.id.dataItem03);
                /*Thread mThread = new Thread(){
                    @Override
                    public void run(){
                        try{
                            URL url = new URL(xmlData.getImage());
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();
                            BufferedInputStream is = new BufferedInputStream(conn.getInputStream(), 1024);
                            Bitmap bitmap = BitmapFactory.decodeStream(is);
                            iv.setImageBitmap(bitmap);
                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();;
                        }
                    }
                };
                mThread.start();*/
                tv1.setText(xmlData.getTitle());
                tv2.setText(xmlData.getMallName().replace("</b>", ""));
                tv3.setText("가격 : " + xmlData.getLprice() + "원");
            }
        }

        return v;
    }
}