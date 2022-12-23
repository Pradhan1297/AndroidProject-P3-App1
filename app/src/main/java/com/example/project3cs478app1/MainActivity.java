package com.example.project3cs478app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Request Code
    final private int BROADCAST_PERMISSION_CODE = 1;
    //Button to broadcast intent to the hotels activity.
    Button hotelsButton;
    //Button to broadcast intent to the attractions activity.
    Button attractionsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelsButton = findViewById(R.id.button);
        attractionsButton = findViewById(R.id.button2);
        //Checks if the following permission is granted. If granted then you'll be allowed send broadcasts.
        if(ContextCompat.checkSelfPermission(MainActivity.this,"edu.uic.cs478.fall22.mp3") == PackageManager.PERMISSION_GRANTED){
            //hotelsButton listener
            hotelsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent hotelIntent = new Intent("Chicago Hotels");
                    hotelIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendOrderedBroadcast(hotelIntent,null);
                    Toast.makeText(MainActivity.this,"Hotels Selected",Toast.LENGTH_SHORT).show();
                }
            });
            //attractionsButton listener
            attractionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent attractionsIntent = new Intent("Chicago Attractions");
                    attractionsIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(attractionsIntent,null);
                    Toast.makeText(MainActivity.this,"Attractions Selected",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{ //If the permission is not granted then the permission is requested in the following line
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{"edu.uic.cs478.fall22.mp3"},BROADCAST_PERMISSION_CODE);
        }
    }
    //This callback is executed after the user allows or disallows the permission.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Checks for the request code
        if(requestCode == BROADCAST_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //hotelsButton listener
                hotelsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent hotelIntent = new Intent("Chicago Hotels");
                        hotelIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        sendBroadcast(hotelIntent,null);
                        Toast.makeText(MainActivity.this,"Hotels Selected",Toast.LENGTH_SHORT).show();

                    }
                });
                //attractionsButton listener
                attractionsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent attractionsIntent = new Intent("Chicago Attractions");
                        attractionsIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                        sendBroadcast(attractionsIntent,null);
                        Toast.makeText(MainActivity.this,"Attractions Selected",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else{//If the permission is not granted then the following toast is displayed.
                Toast.makeText(MainActivity.this, "Need permissions to open attractions and hotels", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
