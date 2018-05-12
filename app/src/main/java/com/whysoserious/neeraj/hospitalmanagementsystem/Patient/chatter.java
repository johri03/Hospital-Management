package com.whysoserious.neeraj.hospitalmanagementsystem.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whysoserious.neeraj.hospitalmanagementsystem.MainActivity;
import com.whysoserious.neeraj.hospitalmanagementsystem.R;
import com.whysoserious.neeraj.hospitalmanagementsystem.Serviceprovided;
import com.whysoserious.neeraj.hospitalmanagementsystem.admit;

import java.util.ArrayList;
import java.util.List;

public class chatter extends Activity
{
    Intent startbuttonintent = getIntent();
    DatabaseReference dr;
    //ArrayList<String> Myfamily=new ArrayList<String>();
    ListView list;
    ArrayList<String>Myfamily=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter);
         list=(ListView)findViewById(R.id.userlist);
       //final String sender=startbuttonintent.getStringExtra("user");
        Bundle extras = getIntent().getExtras();
      final   String sender = extras.getString("user");


        dr= FirebaseDatabase.getInstance().getReference("users");

        Myfamily.add("hjd");
        Myfamily.add("hdssdf");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

               //Toast.makeText(chatter.this,"yo",Toast.LENGTH_SHORT).show();
                //Iterable<DataSnapshot> imagesDir = dataSnapshot.getChildren();
                String[] s;


              ArrayList  Userlist = new ArrayList<String>();
                for(DataSnapshot userssnapshot: dataSnapshot.getChildren())
              {


                  Userlist.add(String.valueOf(userssnapshot.child("id").getValue()));
                  //Myfamily.add(String.valueOf(userssnapshot.getValue()));
                 // imagesDir.add(userssnapshot.child("author").getValue(String.class));
                  //Myfamily.add(bullet.getusers());

                  //M.add("adiyua");


              }
                ArrayAdapter<String>arrayadapter = new ArrayAdapter<String>(chatter.this,android.R.layout.simple_list_item_1,Userlist);
                list.setAdapter(arrayadapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                        Intent is=new Intent(chatter.this,Messageactivity.class);
                        is.putExtra("receiver",(String)parent.getItemAtPosition(position));
                        is.putExtra("sender",sender);

                        startActivity(is);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });









    }

   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        //Toast.makeText(this,Myfamily.get(i),Toast.LENGTH_SHORT).show();
    }


}
