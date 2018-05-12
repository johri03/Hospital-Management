package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    Button Bloginregister;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(TextView)findViewById(R.id.forgot);

        //SET UP THE DATABASE
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        Bloginregister = (Button) findViewById(R.id.bloginregister);
        Bloginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });
    }

    public void onClick(View view)
    {
        Intent i = new Intent(MainActivity.this, doctorlist.class);
        startActivity(i);
    }

    public void serviceprovided(View view)
    {

        Intent i=new Intent(MainActivity.this,Serviceprovided.class);
        startActivity(i);
    }



}
