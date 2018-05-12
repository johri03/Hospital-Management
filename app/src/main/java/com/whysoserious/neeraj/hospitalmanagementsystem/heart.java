package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class heart extends Activity
{
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;

    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12;
    Button b1;

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);

        e1=(EditText)findViewById(R.id.age);
        e2=(EditText)findViewById(R.id.sex);
        e3=(EditText)findViewById(R.id.cp);
        e4=(EditText)findViewById(R.id.bps);
        e5=(EditText)findViewById(R.id.colestrol);
        e6=(EditText)findViewById(R.id.restecg);
        e7=(EditText)findViewById(R.id.hr);
        e8=(EditText)findViewById(R.id.ex);
        e9=(EditText)findViewById(R.id.oldPeak);
        e10=(EditText)findViewById(R.id.Slope);
        e11=(EditText)findViewById(R.id.ca);
        e12=(EditText)findViewById(R.id.Thal);
        b1=(Button)findViewById(R.id.but1);



        s1=e1.getText().toString();
        s2=e2.getText().toString();
        s3=e3.getText().toString();
        s4=e4.getText().toString();
        s5=e5.getText().toString();
        s6=e6.getText().toString();
        s7=e7.getText().toString();
        s8=e8.getText().toString();
        s9=e9.getText().toString();
        s10=e10.getText().toString();
        s11=e11.getText().toString();
        s12=e12.getText().toString();



         result = s1 + " " + s2+" "+s3+" "+s4+" "+s5+" "+s6+" "+s7+" "+s8+" "+s9+" "+s10+" "+s11+" "+s12;

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 upload_string();

             }
         });
    }

    public void upload_string() {

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary =  "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 20*1024;
        String str = "";
        String urlString = "http://192.168.43.242:5000/";
        try
        {
            //------------------ CLIENT REQUEST
            // open a URL connection to the Servlet
            URL url = new URL(urlString);
            // Open a HTTP connection to the URL
            conn = (HttpURLConnection) url.openConnection();
            // Allow Inputs
            conn.setDoInput(true);
            // Allow Outputs
            conn.setDoOutput(true);
            // Don't use a cached copy.
            conn.setUseCaches(false);
            // Use a post method.
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            dos = new DataOutputStream( conn.getOutputStream() );
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\"" + "sample.txt" + "\""+lineEnd);
            dos.writeBytes("Content-Type: multipart/form-data" + lineEnd);
            dos.writeBytes(lineEnd);
            // create a buffer of maximum size
            bufferSize = 2000;

            dos.writeBytes(result);
            Log.d("NAMAN",result.toString());

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // close streams
            Log.e("Debug","File is written");
            dos.flush();
            dos.close();
        }
        catch (MalformedURLException ex)
        {
            Log.e("Debug", "error: " + ex.getMessage(), ex);
        }
        catch (IOException ioe)
        {
            Log.e("Debug", "error: " + ioe.getMessage(), ioe);
        }
        //------------------ read the SERVER RESPONSE
        try {
            inStream = new DataInputStream( conn.getInputStream() );


            str = inStream.readLine();

            //Toast.makeText(MainActivity.this , "GENRE PREDICTED IS :" + str , Toast.LENGTH_LONG).show();
            Log.e("Debug","Server Response "+str);

            Toast.makeText(heart.this , "Probability of having heart disease :" + str , Toast.LENGTH_LONG).show();
            inStream.close();
            //Toast.makeText(MainActivity.this , "GENRE PREDICTED IS :" + str , Toast.LENGTH_LONG).show();

        }
        catch (IOException ioex){
            Log.e("Debug", "error: " + ioex.getMessage(), ioex);
        }

    }

}
