package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ambulance extends AppCompatActivity
{
Button start;
Location l;
    LocationManager locationManager;

    LocationListener locationListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            startListening();

        }

    }

    public void startListening()
    {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        }

    }

    public void updateLocationInfo(Location location) {

        Log.i("LocationInfo", location.toString());

        TextView latTextView = (TextView) findViewById(R.id.latTextView);

        TextView lonTextView = (TextView) findViewById(R.id.lonTextView);

        TextView altTextView = (TextView) findViewById(R.id.altTextView);

        TextView accTextView = (TextView) findViewById(R.id.accTextView);

        latTextView.setText("Latitude: " + location.getLatitude());

        lonTextView.setText("Longitude: " + location.getLongitude());

        altTextView.setText("Altitude: " + location.getAltitude());

        accTextView.setText("Accuracy: " + location.getAccuracy());

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {

            String address = "Could not find address";

            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (listAddresses != null && listAddresses.size() > 0 ) {

                Log.i("PlaceInfo", listAddresses.get(0).toString());

                address = "Address: \n";

                if (listAddresses.get(0).getSubThoroughfare() != null) {

                    address += listAddresses.get(0).getSubThoroughfare() + " ";

                }

                if (listAddresses.get(0).getThoroughfare() != null) {

                    address += listAddresses.get(0).getThoroughfare() + "\n";

                }

                if (listAddresses.get(0).getLocality() != null) {

                    address += listAddresses.get(0).getLocality() + "\n";

                }

                if (listAddresses.get(0).getPostalCode() != null) {

                    address += listAddresses.get(0).getPostalCode() + "\n";

                }

                if (listAddresses.get(0).getCountryName() != null) {

                    address += listAddresses.get(0).getCountryName() + "\n";

                }

            }

            TextView addressTextView = (TextView) findViewById(R.id.addressTextView);

            addressTextView.setText(address);


        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    FirebaseDatabase database;
    DatabaseReference mref;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ambulance);
        start = (Button)findViewById(R.id.btnlocation);
       database=FirebaseDatabase.getInstance();
        mref=database.getReference();

       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               //Toast.makeText(ambulance.this,"hello",Toast.LENGTH_SHORT).show();
//               Log.d("TAGGER", "onClick: Button pressed"+mref.child("Location").toString());
               if(l!=null)
               {
                   mref.child("Location").push().setValue(l.getLatitude()+ ",   "+l.getLongitude());
               }
               else
               {
                   Toast.makeText(ambulance.this,"Unable to fetch location",Toast.LENGTH_SHORT).show();
               }

           }
       });

        //final LocationListener listener;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {

                updateLocationInfo(location);
                l=location;




            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {

            startListening();

        } else {

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {

                    updateLocationInfo(location);

                }

            }

        }




    }

            }















