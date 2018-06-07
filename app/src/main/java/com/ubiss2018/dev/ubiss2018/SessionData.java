package com.androidapps.muradahmad.calmify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionData extends AppCompatActivity {


    Button btnPlay;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_data);


 /*       Button btnSessionSetting = (Button) findViewById(R.id.session);
       btnSessionSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SessionData.this,SessionSetting.class);
                startActivity(intent);

            }
        });*/

        btnPlay = (Button) findViewById(R.id.btnStart);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SessionData.this,Play.class);
                startActivity(intent);

            }
        });







            // get the listview
            expListView = (ExpandableListView) findViewById(R.id.lvExpandable);

            // preparing list data
            prepareListData();

            listAdapter = new ExpandableAdapter(this, listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);

            // Listview Group click listener
            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    // Toast.makeText(getApplicationContext(),
                    // "Group Clicked " + listDataHeader.get(groupPosition),
                    // Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // Listview Group expanded listener
            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                @Override
                public void onGroupExpand(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Listview Group collasped listener
            expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                @Override
                public void onGroupCollapse(int groupPosition) {
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();

                }
            });

            // Listview on child click listener
            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    // TODO Auto-generated method stub
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    return false;
                }
            });
        }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("DURATION");
        listDataHeader.add("SOUNDSCAPE");
        listDataHeader.add("AUDIO GUIDANCE");

        // Adding child data
        List<String> duration = new ArrayList<String>();
        duration.add("30 seconds ");
        duration.add("10 minutes");
        duration.add("15 minutes");
        duration.add("20 minutes");
        duration.add("open ended");


        List<String> sound = new ArrayList<String>();
        sound.add("Silence");
        sound.add("Birds");
        sound.add("Windy day");
        sound.add("Coffeeshop");
        sound.add("Fireplace");


        List<String> guidance = new ArrayList<String>();
        guidance.add("No guidance");
        guidance.add("Relaxation");
        guidance.add("Focus");
        guidance.add("Calm mind");
        guidance.add("Happy thoughts");

        listDataChild.put(listDataHeader.get(0), duration); // Header, Child data
        listDataChild.put(listDataHeader.get(1), sound);
        listDataChild.put(listDataHeader.get(2), guidance);





    }
/*

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

*/


}
