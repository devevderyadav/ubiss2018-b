package com.ubiss2018.dev.ubiss2018;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.aware.*;

import com.aware.providers.Accelerometer_Provider;
import com.aware.ui.PermissionsHandler;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /*Context context;*/

    private TextView mTextMessage;

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
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* mTextMessage = (TextView) findViewById(R.id.message);*/
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


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

            Uri database = Uri.parse("content://"+getPackageName()+".provider.fitbit/fitbit_data");

            Cursor fitbit_data = getContentResolver().query(database,null, null, null,null);

            if (fitbit_data != null && fitbit_data.moveToFirst()) {
                Log.d("Calmify", DatabaseUtils.dumpCursorToString(fitbit_data));
            }

            Uri devices  = Uri.parse("content://"+getPackageName()+".provider.fitbit/fitbit_devices");
            Cursor fitbit_device = getContentResolver().query(devices,null, null, null,null);
            if (fitbit_device != null && fitbit_device.moveToFirst()) {
                Log.d("Calmify-devices", DatabaseUtils.dumpCursorToString(fitbit_device));
            }

            Aware.startAccelerometer(this);

            Accelerometer.setSensorObserver(new Accelerometer.AWARESensorObserver() {
                @Override
                public void onAccelerometerChanged(ContentValues contentValues) {
                    double x = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_0);
                    double y = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_1);
                    double z = contentValues.getAsDouble(Accelerometer_Provider.Accelerometer_Data.VALUES_2);

                    double axis_power = Math.sqrt(x*x + y*y + z*z);

                    /*Log.d("debug", String.format("Power: %f",axis_power));
*/
                    Log.d("debug", String.format("Z: %f",z*z));
                   /* Log.d("debug", String.format("Y: %f",y*y));*/
                }
            });

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
