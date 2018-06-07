package com.ubiss2018.dev.ubiss2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class PostQuestionnaire extends AppCompatActivity {

    Button next_button;

    RadioButton alert_button, angry_button, anxious_button, frustrated_button, happy_button, tired_button;
    String mood;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postquestionnaire);

        next_button = (Button) findViewById(R.id.next_button);

        alert_button = (RadioButton) findViewById(R.id.alert_button);
        angry_button = (RadioButton) findViewById(R.id.angry_button);
        anxious_button = (RadioButton) findViewById(R.id.anxious_button);
        frustrated_button = (RadioButton) findViewById(R.id.frustrated_button);
        happy_button = (RadioButton) findViewById(R.id.happy_button);
        tired_button = (RadioButton) findViewById(R.id.tired_button);

        mood="";

        // top radio buttons


        alert_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(true);
                angry_button.setChecked(false);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(false);
                happy_button.setChecked(false);
                tired_button.setChecked(false);

                mood="alert";
            }
        });

        angry_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(false);
                angry_button.setChecked(true);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(false);
                happy_button.setChecked(false);
                tired_button.setChecked(false);
                mood="angry";
            }
        });

        anxious_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(false);
                angry_button.setChecked(false);
                anxious_button.setChecked(true);
                frustrated_button.setChecked(false);
                happy_button.setChecked(false);
                tired_button.setChecked(false);
                mood="anxious";
            }
        });

        frustrated_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(false);
                angry_button.setChecked(false);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(true);
                happy_button.setChecked(false);
                tired_button.setChecked(false);
                mood="frustrated";
            }
        });

        happy_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(false);
                angry_button.setChecked(false);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(false);
                happy_button.setChecked(true);
                tired_button.setChecked(false);
                mood="happy";
            }
        });

        tired_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(false);
                angry_button.setChecked(false);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(false);
                happy_button.setChecked(false);
                tired_button.setChecked(true);
                mood="tired";
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("Questionnaire", "mood: " + mood);


                // Start NewActivity.class
              /*  Intent myIntent = new Intent(Questionnaire.this,
                        Play.class);
                startActivity(myIntent);*/

                Intent myIntent = new Intent(PostQuestionnaire.this,
                        Results.class);
                startActivity(myIntent);

                //db = new DatabaseHandler(getApplicationContext());

                //MeditationSession meditation = new MeditationSession("1", "angry", "home", "10 minutes",
                //        "birds", "guided by Erkki", "sad", "10000000000");

                //db.addMeditationSession(meditation);

                //db.getMeditationSessions();
            }
        });
    }
}
