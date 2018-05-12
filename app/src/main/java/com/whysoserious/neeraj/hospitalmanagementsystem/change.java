package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class change extends Activity
{
    String newpass;
    EditText e1;
    Button b1,b2;
    String number;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        e1=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button8);

        b2=(Button)findViewById(R.id.button9);
        Bundle bundle=getIntent().getExtras();
        number=bundle.getString("phone");
        mDatabase= FirebaseDatabase.getInstance().getReference("users");


         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                 newpass=e1.getText().toString();
                 //Toast.makeText(getApplicationContext(),newpass,Toast.LENGTH_SHORT).show();
                 mDatabase.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot)
                     {

                         for(DataSnapshot usersnapshot:dataSnapshot.getChildren())
                         {
                             if(String.valueOf(usersnapshot.child("phoneno").getValue()).equals(number))
                             {
                                 Log.i("choot",newpass);
                                 usersnapshot.getRef().child("password").setValue(newpass);
                                 break;
                             }

                         }
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });


             }
         });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(change.this,Login.class);
                startActivity(i);

            }
        });

    }


}
