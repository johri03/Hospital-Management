package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.whysoserious.neeraj.hospitalmanagementsystem.Patient.Messageactivity;

import java.util.ArrayList;

public class doctorlist extends Activity {
    ArrayList<String> doclist=new ArrayList<>();
    ListView l1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlist);
        l1=(ListView)findViewById(R.id.list);
        doclist.add("Orthopedics");
        doclist.add("Cardialogy");
        doclist.add("Dermatology");
        doclist.add("Homeopathy");
        doclist.add("Neurology");

        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(doctorlist.this,android.R.layout.simple_list_item_1,doclist);
        l1.setAdapter(arrayadapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(((String)adapterView.getItemAtPosition(i)).equals("Orthopedics"))
                {
                    Intent is=new Intent(doctorlist.this,Doctors_available.class);
                    is.putExtra("link","http://www.sgrh.com/departments/details/172/18");
                    startActivity(is);
                }
                if(((String)adapterView.getItemAtPosition(i)).equals("Cardialogy"))
                {
                    Intent is=new Intent(doctorlist.this,Doctors_available.class);
                    is.putExtra("link","http://www.sgrh.com/departments/details/265/3");

                    startActivity(is);
                }
                if(((String)adapterView.getItemAtPosition(i)).equals("Dermatology"))
                {
                    Intent is=new Intent(doctorlist.this,Doctors_available.class);
                    is.putExtra("link","http://www.sgrh.com/departments/details/217/6");
                    startActivity(is);
                }
                if(((String)adapterView.getItemAtPosition(i)).equals("Homeopathy"))
                {
                    Intent is=new Intent(doctorlist.this,Doctors_available.class);
                    is.putExtra("link","http://www.sgrh.com/departments/details/248/11");
                    startActivity(is);
                }
                if(((String)adapterView.getItemAtPosition(i)).equals("Neurology"))
                {
                    Intent is=new Intent(doctorlist.this,Doctors_available.class);
                    is.putExtra("link","http://www.sgrh.com/departments/details/95/15");
                    startActivity(is);
                }
            }
        });
    }

}
