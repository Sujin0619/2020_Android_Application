package com.example.myshopping;


import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.util.Log;


public class PriceListParser {
    String NAVER_SEARCH_KEY;//검색 키
    ArrayList<PriceDataStruct> xmlData_array;//데이터 list

    public PriceListParser(String key) {
        this.NAVER_SEARCH_KEY = key;
    }

    public ArrayList<PriceDataStruct> GetXmlData(String searchTxt, int display, int start) {

        xmlData_array = new ArrayList<PriceDataStruct>();

        PriceDataStruct xmlData = null;

        String m_searchTxt = "";

        try {
            m_searchTxt = URLEncoder.encode(searchTxt, "UTF8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        //할당받은 ID와 Secret
        String clientID = "Yo3jGXgyuzqOSBOCuUCG";
        String clientSecret = "0frgBtucsy";

        try
        {
            boolean startItemTag = false;

            String tagName ="";
            String DISPLAY ="10";//띄우기 원하는 개수
            String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query=" + NAVER_SEARCH_KEY + "&display="+DISPLAY + "&start=1";
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientID);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            XmlPullParserFactory parserCreator = XmlPullParserFactory
                    .newInstance();

            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            int eventType = parser.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT )
            {

                switch( eventType )
                {
                    case XmlPullParser.START_DOCUMENT:
                    {
                        break;
                    }

                    case XmlPullParser.END_DOCUMENT:
                    {
                        break;
                    }

                    case XmlPullParser.START_TAG:
                    {
                        tagName = parser.getName();
                            if( tagName.equals("item") == true ) {
                            startItemTag = true;
                            xmlData = new PriceDataStruct();
                        }

                        break;
                    }

                    case XmlPullParser.TEXT:
                    {
                        if( startItemTag == false ) {
                            break;
                        }

                        if(tagName.equals("total")) {
                            xmlData.setTotal(Integer.parseInt(parser.getText()));
                        }
                        else if(tagName.equals("start")) {
                            xmlData.setStart(Integer.parseInt(parser.getText()));
                        }
                        else if(tagName.equals("display")) {
                            xmlData.setDisplay(Integer.parseInt(parser.getText()));
                        }
                        else if(tagName.equals("title")) {
                            xmlData.setTitle(parser.getText().replace("</b>","").replace("<b>",""));
                        //불필요한 스트링 제거
                        }
                        else if(tagName.equals("link")) {
                            xmlData.setLink(parser.getText());
                        }
                        else if(tagName.equals("image")) {
                            xmlData.setImage(parser.getText());
                        }
                        else if(tagName.equals("lprice")) {
                            xmlData.setLprice(Integer.parseInt(parser.getText()));
                        }
                        else if(tagName.equals("hprice")) {
                            xmlData.setHprice(Integer.parseInt(parser.getText()));
                        }
                        else if(tagName.equals("mallName")) {
                            xmlData.setMallName(parser.getText());
                        }
                        else if(tagName.equals("productId")) {
                            xmlData.setProductId(Long.parseLong(parser.getText()));
                        }

                        break;
                    }

                    case XmlPullParser.END_TAG:
                    {
                        tagName = parser.getName();

                        if(  tagName.equals("item") == true ) {
                            startItemTag = false;
                            xmlData_array.add(xmlData);
                        }

                        break;
                    }
                }

                eventType = parser.next();
            }
        }
        catch (Exception e)
        {
        }

        xmlData = new PriceDataStruct();
        xmlData_array.add(xmlData);

        return xmlData_array;
    }
}
