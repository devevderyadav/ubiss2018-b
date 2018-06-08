package com.ubiss2018.dev.ubiss2018;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;


import com.aware.*;

import com.aware.plugin.fitbit.Plugin;
import com.aware.plugin.fitbit.Provider;
import com.aware.providers.Accelerometer_Provider;
import com.aware.ui.PermissionsHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    SessionHRs  sessionHRs= new SessionHRs();

    MeditationSession meditationSession;
    private DatabaseHandler db;

    String lowestHR, highestHR ;

    /*Context context;*/

   /* private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/
    Button meditate_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* mTextMessage = (TextView) findViewById(R.id.message);*/
        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);*/
       /* navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
*/



        db = new DatabaseHandler(getApplicationContext());
    }



    @Override
    protected void onResume() {
        super.onResume();

        //Start core library
        if (!Aware.IS_CORE_RUNNING) {
            Intent aware = new Intent(getApplicationContext(), Aware.class);
            startService(aware);
        }

        //Since Android 5+ we need to check in runtime if the permissions were given, so we will check every time the user launches the main UI.
        //These are the same that are on the manifest. External storage and Internet are automatically taken care of by AWARE
        ArrayList<String> REQUIRED_PERMISSIONS = new ArrayList<>();
        REQUIRED_PERMISSIONS.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        boolean permissions_ok = true;
        for (String p : REQUIRED_PERMISSIONS) { //loop to check all the required permissions.
            if (PermissionChecker.checkSelfPermission(this, p) != PermissionChecker.PERMISSION_GRANTED) {
                permissions_ok = false;
                break;
            }
        }

        if (permissions_ok) {

            Intent aware = new Intent(this, Aware.class);
            startService(aware);

            Aware.setSetting(this, Aware_Preferences.DEBUG_FLAG, true);

            Aware.startPlugin(this, "com.aware.plugin.fitbit");

            Cursor fitbit_data = getContentResolver().query(Provider.Fitbit_Data.CONTENT_URI,
                    new String[]{Provider.Fitbit_Data.TIMESTAMP, Provider.Fitbit_Data.FITBIT_JSON},
                    Provider.Fitbit_Data.DATA_TYPE + " like 'heartrate'", null,
                    Provider.Fitbit_Data.TIMESTAMP + " DESC LIMIT 1");

            if (fitbit_data != null && fitbit_data.moveToFirst()) {

                try {
                    JSONObject hr = new JSONObject(fitbit_data.getString(fitbit_data.getColumnIndex(Provider.Fitbit_Data.FITBIT_JSON)));

                    JSONArray restingHR = hr.getJSONArray("activities-heart");

                    Log.d("restingHR", String.format("%d",  restingHR.getJSONObject(0).getJSONObject("value").getInt("restingHeartRate")));
                    restingHR.getJSONObject(0).getJSONObject("value").getInt("restingHeartRate");

                    Log.d("lowestHR", String.format("%d", sessionHRs.getLowestHR(hr)));

                    lowestHR = String.format("%d", sessionHRs.getLowestHR(hr));
                    Log.d("Murad Heart Rate Lowest:", lowestHR);


                    // database


                    //MeditationSession meditation = new MeditationSession("1", "angry", "home", "10 minutes",
                    //        "birds", "guided by Erkki", "sad", "10000000000");

                   // db.addMeditationSession(meditation);

                    //db.getMeditationSessions();

//                    meditationSession.setLHr(lowestHR);

                    Log.d("highestHR", String.format("%d", sessionHRs.getHighestHR(hr)));
                    highestHR = String.format("%d", sessionHRs.getHighestHR(hr));

                    Log.d("Murad Heart Rate Highest:", highestHR);
                   // meditationSession.setHHr(highestHR);


/*
                    meditationSession = new MeditationSession("1", "angry", "home", "10 minutes",
                                    "birds", "guided by Erkki", "sad", lowestHR,highestHR);

                    db.addMeditationSession(meditationSession);


                    */


                    /*Log.d("restingHeartRate",  acthr.get);
*/
                    Log.d("HR", hr.toString(5));

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                }
        meditate_button = (Button) findViewById(R.id.meditate_button);

        // Capture button clicks
        meditate_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Questionnaire.class);
                startActivity(myIntent);
            }
        });

            Uri devices  = Uri.parse("content://"+getPackageName()+".provider.fitbit/fitbit_devices");
            Cursor fitbit_device = getContentResolver().query(devices,null, null, null,null);
            if (fitbit_device != null && fitbit_device.moveToFirst()) {
                Log.d("Calmify-devices", DatabaseUtils.dumpCursorToString(fitbit_device));
            }

            /*Aware.startAccelerometer(this);

            Accelerometer.setSensorObserver(new Accelerometer.AWARESensorObserver() {
                @Override
                public void onAccelerometerChanged(ContentValues contentValues) {
                    double x = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_0);
                    double y = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_1);
                    double z = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_2);

                    double axis_power = Math.sqrt(x*x + y*y + z*z);

                    *//*Log.d("debug", String.format("Power: %f",axis_power));
*//*


                    if(z*z>2.5){

                        Log.d("debug", String.format("Z: %f",z*z));

                    }

                   *//* Log.d("debug", String.format("Y: %f",y*y));*//*

                }
            });

*/
           /* Aware.startScreen(this);*/



           /* Screen.setSensorObserver(new Screen.AWARESensorObserver() {
                @Override
                public void onScreenOn() {
                    Log.d("ScreenStatus", "Screen On");
                }

                @Override
                public void onScreenOff() {
                   *//* Log.d('Screen Status','Screen of time');
*//*
                }

                @Override
                public void onScreenLocked() {

                }

                @Override
                public void onScreenUnlocked() {

                }
            });*/

        } else {
            //ask the permissions using AWARE's PermissionsHandler class. Return to this activity when done.
            Intent permissions = new Intent(this, PermissionsHandler.class);
            permissions.putExtra(PermissionsHandler.EXTRA_REQUIRED_PERMISSIONS, REQUIRED_PERMISSIONS);
            permissions.putExtra(PermissionsHandler.EXTRA_REDIRECT_ACTIVITY, getPackageName() + "/" + getClass().getName());
            permissions.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(permissions);

            finish();
        }
    }


}
