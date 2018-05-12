package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by This PC on 9/27/2017.
 */

public class fetchdata extends AsyncTask<Object,Object,Void>

{
    String url;
    fetchdata(String url1)
    {
        url=url1;
    }

    String data="";
    String dataparsed="";
    String singleparsed="";
    String names="";
    String desc="";
    int i=0;
    ImageView im;
    Bitmap bitmap;
    String src1="";




    @Override
    protected Void doInBackground(Object[] objects)
    {

       try
       {

           Element doc= Jsoup.connect(url).get();
           Elements name=doc.select(".leftcurrent");
           names = name.text();
           Elements tim=doc.select("table tr:nth-child(3) td");
        desc=tim.text();
           Elements image = doc.select("img");
           for( Element element : image ){
               final String src = element.absUrl("src");
               if( src.toLowerCase().endsWith(".jpg") ) {
                   Log.i("johri1", src);
                   src1 = src;
               }

           }


       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
        try {

             bitmap = BitmapFactory.decodeStream((InputStream)new URL(src1).getContent());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Doctors_available.board.setText(names);
        Doctors_available.board1.setText(desc);
        Doctors_available.im.setImageBitmap(bitmap);
    }
}
