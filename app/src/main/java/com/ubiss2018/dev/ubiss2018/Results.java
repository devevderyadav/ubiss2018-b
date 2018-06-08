package com.ubiss2018.dev.ubiss2018;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.aware.plugin.fitbit.Provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Results extends AppCompatActivity {

    MeditationSession meditationSession;


    private DatabaseHandler db;

    TextView txtlhr,txthhr,txtrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        db = new DatabaseHandler(getApplicationContext());

        SessionHRs sessionHRs = new SessionHRs();

        db.getMeditationSessions();

        txtlhr = (TextView) findViewById(R.id.textView7);
        //String lowestHr = meditationSession.getLHr();



        txthhr = (TextView) findViewById(R.id.textView11);
        txtrest = (TextView) findViewById(R.id.txtInfo);
        //String highestHr = meditationSession.getHHr();



        Cursor fitbit_data = getContentResolver().query(Provider.Fitbit_Data.CONTENT_URI,
                new String[]{Provider.Fitbit_Data.TIMESTAMP, Provider.Fitbit_Data.FITBIT_JSON},
                Provider.Fitbit_Data.DATA_TYPE + " like 'heartrate'", null,
                Provider.Fitbit_Data.TIMESTAMP + " DESC LIMIT 1");

        if (fitbit_data != null && fitbit_data.moveToFirst()) {

            try {
                JSONObject hr = new JSONObject(fitbit_data.getString(fitbit_data.getColumnIndex(Provider.Fitbit_Data.FITBIT_JSON)));

                JSONArray restingHR = hr.getJSONArray("activities-heart");

                Log.d("restingHR", String.format("%d", restingHR.getJSONObject(0).getJSONObject("value").getInt("restingHeartRate")));
                restingHR.getJSONObject(0).getJSONObject("value").getInt("restingHeartRate");

                Log.d("lowestHR", String.format("%d", sessionHRs.getLowestHR(hr)));

                String lowestHR = String.format("%d", sessionHRs.getLowestHR(hr));
                Log.d("Murad Heart Rate Lowest:", lowestHR);

                txtlhr.setText(lowestHR);

                String restingHr = String.format("%d", restingHR.getJSONObject(0).getJSONObject("value").getInt("restingHeartRate"));





                txtrest.setText("During the Session your lowest heart rate was only "+( Integer.valueOf(restingHr) - Integer.valueOf(lowestHR)) + " bpm over your night time average.");

                // database


                //MeditationSession meditation = new MeditationSession("1", "angry", "home", "10 minutes",
                //        "birds", "guided by Erkki", "sad", "10000000000");

                // db.addMeditationSession(meditation);

                //db.getMeditationSessions();

//                    meditationSession.setLHr(lowestHR);

                Log.d("highestHR", String.format("%d", sessionHRs.getHighestHR(hr)));
                String highestHR = String.format("%d", sessionHRs.getHighestHR(hr));

                Log.d("Murad Heart Rate Highest:", highestHR);
                // meditationSession.setHHr(highestHR);

                txthhr.setText(highestHR);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }}
