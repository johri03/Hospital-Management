package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Serviceprovided extends AppCompatActivity
{  ListView servicelist;
    String[] services={"Blood Bank","Ambulance","Medicines","Admit"};
    String[] Desc={"Get Blood","Book Ambulance","Search Medicine","Availability of Bed"};
    Integer[] imgid={R.drawable.bloodbank,R.drawable.ambulance,R.drawable.medicines,R.drawable.admit};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovided);



        servicelist=(ListView)findViewById(R.id.servicelist);
        customlistview customlistview=new customlistview(this,services,Desc,imgid);
        servicelist.setAdapter(customlistview);
        servicelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                if(services[i]=="Blood Bank")
                {
                    Intent in=new Intent(Serviceprovided.this,bloodbank.class);
                    startActivity(in);
                }
                if(services[i]=="Ambulance")
                {
                    Intent in=new Intent(Serviceprovided.this,ambulance.class);
                    startActivity(in);
                }

                if(services[i]=="Medicines")
                {
                    Intent in=new Intent(Serviceprovided.this,heart2.class);
                    startActivity(in);
                }

                if(services[i]=="Admit")
                {
                    Intent in=new Intent(Serviceprovided.this,admit.class);
                    startActivity(in);
                }

            }
        });




        }





    }

