package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class changepass extends Activity
{

EditText e1;
TextView t;
Button b1,b2;
//String number="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        //Bundle extras = getIntent().getExtras();

             //number = extras.getString("phone");


         e1=(EditText)findViewById(R.id.editText2);
         t=(TextView)findViewById(R.id.textView4) ;
         String newpass=e1.getText().toString();
         b1=(Button)findViewById(R.id.button3);
         b2=(Button)findViewById(R.id.button7);






        //Toast.makeText(getApplicationContext(),number,Toast.LENGTH_SHORT).show();
    }

}
