package com.ubiss2018.dev.ubiss2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.aware.*;

public class MainActivity extends AppCompatActivity {
    Button button;
    private TextView mTextMessage;
    private ImageView imageView;
    private String fullScreenInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.meditate_button);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Questionnaire.class);
                startActivity(myIntent);
            }
        });

        //mTextMessage = (TextView) findViewById(R.id.message);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*Intent aware = new Intent(this, Aware.class);
        startService(aware);
        Aware.startScreen(this);*/

        //Intent aware = new Intent(this, Aware.class);



        //StartService(aware);
        Aware.startScreen(this);

       /* Screen.setSensorObserver(new Screen.AWARESensorObserver() {
            @Override
            public void onScreenOn() {

            }



        });*/

    }

}
