package com.whysoserious.neeraj.hospitalmanagementsystem.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whysoserious.neeraj.hospitalmanagementsystem.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Messageactivity extends Activity
{
    //Intent messonintent = getIntent();
    //TextView sen,rec;
    ListView messages;
    DatabaseReference base;

    EditText mess;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageactivity);
        //Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar2);
        //getActionBar().setTitle("Chat");
        //toolbar.setLogo(R.drawable.chat);
        Bundle extras = getIntent().getExtras();
      final   String sener = extras.getString("sender");
       final String recever = extras.getString("receiver");
        mess=(EditText)findViewById(R.id.editText3);
        go=(Button)findViewById(R.id.button5);
        messages=(ListView)findViewById(R.id.chatts);
        final ArrayList<String>messlist=new ArrayList<>();
        FirebaseDatabase database;
        final   DatabaseReference mref;
        final  Map mParent = new HashMap();
        base= FirebaseDatabase.getInstance().getReference("message");
        //DatabaseReference senerbase= (DatabaseReference) base.orderByChild("sender").equalTo(sener).orderByChild("receiver").equalTo(recever);
       // DatabaseReference reverbase=(DatabaseReference)base.orderByChild("sender").equalTo(recever).orderByChild("receiver").equalTo(sener);

        base.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                messlist.clear();
                for(DataSnapshot messsanpshot:dataSnapshot.getChildren())
                {
                    if(String.valueOf(messsanpshot.child("sender").getValue()).equals(sener) &&  String.valueOf(messsanpshot.child("receiver").getValue()).equals(recever) )
                    {
                        messlist.add(String.valueOf(messsanpshot.child("body").getValue())+"                          " +sener);
                    }
                    if(String.valueOf(messsanpshot.child("receiver").getValue()).equals(sener) &&  String.valueOf(messsanpshot.child("sender").getValue()).equals(recever ))
                    {
                        messlist.add(String.valueOf(messsanpshot.child("body").getValue())+"                         "+recever);

                    }



                }
                ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(Messageactivity.this,android.R.layout.simple_list_item_1,messlist);
                messages.setAdapter(arrayadapter);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        //sen=(TextView)findViewById(R.id.textView6);
        //rec=(TextView)findViewById(R.id.reciver);
        //sen.setText(sener);
        //rec.setText(recever);

        database=FirebaseDatabase.getInstance();
        mref=database.getReference();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
      final   String reportDate = df.format(currentTime);
       go.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               mParent.put("body", mess.getText().toString());
               mParent.put("receiver", recever);
               mParent.put("sender",sener );
               mParent.put("time", reportDate);
               mref.child("message").push().setValue(mParent);
               mess.setText("");
           }
       });




    }



}
