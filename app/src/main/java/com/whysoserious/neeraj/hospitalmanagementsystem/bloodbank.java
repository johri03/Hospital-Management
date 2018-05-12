package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IntegerRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;

public class bloodbank extends AppCompatActivity
{
    String searchtext;
    TextView bloodname,bloodcap;
    SQLiteDatabase mydatabase;
    EditText editText;
    Button b1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        final SearchView searchView=(SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager=(SearchManager)getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Enter Blood Group");
         SearchView.OnQueryTextListener queryTextListener= new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchtext=query;
                viewtext(searchtext);
                //Toast.makeText(bloodbank.this,searchtext,Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                /*if(newText=="")
                {
                    bloodcap.setText("");
                    bloodname.setText("");
                    b1.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                }*/

                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);



        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_bloodbank);
        try {
             mydatabase = this.openOrCreateDatabase("Blood", MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS blood (name VARCHAR,capacity INT(3))");
            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('A+',34)");
           mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('B+',21)");

            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('B-',80)");
            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('O+',98)");
            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('O-',65)");
            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('AB+',45)");
            mydatabase.execSQL("INSERT INTO blood (name,capacity) VALUES ('AB-',35)");






            //bloodcap.setText(Integer.toString(c.getInt(capacityindex)));

            //while (c != null)
            //{
                //Log.i("name", c.getString(nameindex));
                //Log.i("age", Integer.toString(c.getInt(capacityindex)));

                //Toast.makeText(bloodbank.this,c.getString(nameindex),Toast.LENGTH_SHORT).show();
                //Toast.makeText(bloodbank.this,Integer.toString(c.getInt(capacityindex)),Toast.LENGTH_SHORT).show();

                //c.moveToNext();
        }
        catch (Exception e)
        {
            e.printStackTrace();


        }

    }

    public void viewtext(final String searchtext)
    {
        bloodname=(TextView)findViewById(R.id.textView);
        bloodcap=(TextView)findViewById(R.id.textView2);
        editText=(EditText)findViewById(R.id.editText);
        b1=(Button)findViewById(R.id.button2);




        try
        {
           final Cursor c = mydatabase.rawQuery("SELECT * FROM blood WHERE name='" + searchtext + "'", null);

            int nameindex = c.getColumnIndex("name");
            final int capacityindex = c.getColumnIndex("capacity");
            c.moveToFirst();
            bloodname.setText("Blood Group :     " +  c.getString(nameindex));
            bloodcap.setText("Blood Capacity :   "+ Integer.toString(c.getInt(capacityindex)));
            editText.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    String entry=editText.getText().toString();
                    if(TextUtils.isEmpty(entry))
                    {
                        Toast.makeText(bloodbank.this, "Please enter the capacity", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        int val = Integer.parseInt(String.valueOf(editText.getText()));
                        int actualvalue = c.getInt(capacityindex);
                        int updatedval = actualvalue - val;
                        ContentValues dataToInsert = new ContentValues();
                        dataToInsert.put("capacity", updatedval);
                        String where = "name=?";
                        String[] whereArgs = new String[]{String.valueOf(searchtext)};
                       if(updatedval>0)
                       {
                           mydatabase.update("blood", dataToInsert, where, whereArgs);
                           //Toast.makeText(bloodbank.this, Integer.toString(updatedval), Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           Toast.makeText(bloodbank.this, "Your request can't be approved", Toast.LENGTH_SHORT).show();
                       }
                    }
                }
            });





        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }



}
