package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.whysoserious.neeraj.hospitalmanagementsystem.Patient.Patient;

import java.util.concurrent.TimeUnit;

public class forgot extends Activity
{
    String number;
    FirebaseAuth auth;
    EditText e1,e2;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    String verification_code;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        auth= FirebaseAuth.getInstance();
        mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
            {
                super.onCodeSent(s, forceResendingToken);
                verification_code=s;
                Log.i("tag3","shivli");
                Toast.makeText(getApplicationContext(),"Code sent to the number",Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void sentSMS(View v)
    {
         number=e1.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,this,mcallback);
    }
    public void signInWithPhone(PhoneAuthCredential credential)
    {
        Log.i("tag3","shubham");
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override



            public void onComplete(@NonNull Task<AuthResult> task)
            {

                Log.i("sddk",number);
                if(task.isSuccessful())
                {
                    //Toast.makeText(getApplicationContext(),"hii",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(forgot.this,change.class);
                    i.putExtra("phone",number);
                    startActivity(i);

                }

            }
        });
    }
    public void verify(View v)
    {
        String input_code=e2.getText().toString();
        Log.i("tag2","johri");

        verifyphoneNumber(verification_code,input_code);


    }
    public void verifyphoneNumber(String verifyCode,String input_code)
    {
        Log.i("tag","pranjal");
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verifyCode,input_code);
        signInWithPhone(credential);
    }
}
