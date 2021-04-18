package com.example.exam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// The activity_splash_screen.xml layout with a complete, pleasing layout [3 morks]
// Retrieve and display the number of trees chopped down from prior launches [5 marks]
// Add an action button to the splash.xml layout that resets the count to 0 [3 marks]
//      when the reset is done show a Toast message to indicate [2 marks]

// don't forget to set the icon to be the axe! [2 marks]

public class SplashScreen extends AppCompatActivity {
    private int splashTime = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // do all the things for setting up this screen:

        // trigger the next activity after 5 seconds
        new Handler(Looper.getMainLooper()).postDelayed(
                () -> {
                    Intent myIntent = new Intent(this, ChopTrees.class);
                    startActivity(myIntent);
                }, splashTime);



           }

    // need any helper functions? put them here





}