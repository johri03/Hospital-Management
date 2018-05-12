package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Neeraj on 14-Apr-16.
 */
public class Doctors_available extends AppCompatActivity {

    Button click;
    public static TextView board;
    public static TextView board1;
    public static ImageView im;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_available);
        Bundle bundle=getIntent().getExtras();
        final String url = bundle.getString("link");

        click=(Button)findViewById(R.id.docbut);
        board=(TextView)findViewById(R.id.info);
        board1=(TextView)findViewById(R.id.info1);
         im = (ImageView)findViewById(R.id.image);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchdata process=new fetchdata(url);
                process.execute();
            }
        });
    }

}
