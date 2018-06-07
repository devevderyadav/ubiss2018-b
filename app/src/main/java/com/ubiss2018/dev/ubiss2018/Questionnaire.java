package com.ubiss2018.dev.ubiss2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Questionnaire extends AppCompatActivity {

    Button back_button;

    RadioButton alert_button, angry_button, anxious_button, frustrated_button, happy_button, tired_button;
    RadioButton home_button, work_button, bed_button, break_button, train_button, airplane_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        back_button = (Button) findViewById(R.id.back_button);

        alert_button = (RadioButton) findViewById(R.id.alert_button);
        angry_button = (RadioButton) findViewById(R.id.angry_button);
        anxious_button = (RadioButton) findViewById(R.id.anxious_button);
        frustrated_button = (RadioButton) findViewById(R.id.frustrated_button);
        happy_button = (RadioButton) findViewById(R.id.happy_button);
        tired_button = (RadioButton) findViewById(R.id.tired_button);

        home_button = (RadioButton) findViewById(R.id.home_button);
        work_button = (RadioButton) findViewById(R.id.work_button);
        bed_button = (RadioButton) findViewById(R.id.bed_button);
        break_button = (RadioButton) findViewById(R.id.break_button);
        train_button = (RadioButton) findViewById(R.id.train_button);
        airplane_button = (RadioButton) findViewById(R.id.airplane_button);

        // top radio buttons

        // Capture button clicks
        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(Questionnaire.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });

        alert_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert_button.setChecked(true);
                angry_button.setChecked(false);
                anxious_button.setChecked(false);
                frustrated_button.setChecked(false);
                happy_button.setChecked(false);
                tired_button.setChecked(false);
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
            }
        });

        // Bottom radiobuttons

        home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(true);
                work_button.setChecked(false);
                bed_button.setChecked(false);
                break_button.setChecked(false);
                train_button.setChecked(false);
                airplane_button.setChecked(false);
            }
        });

        work_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(false);
                work_button.setChecked(true);
                bed_button.setChecked(false);
                break_button.setChecked(false);
                train_button.setChecked(false);
                airplane_button.setChecked(false);
            }
        });

        bed_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(false);
                work_button.setChecked(false);
                bed_button.setChecked(true);
                break_button.setChecked(false);
                train_button.setChecked(false);
                airplane_button.setChecked(false);
            }
        });

        break_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(false);
                work_button.setChecked(false);
                bed_button.setChecked(false);
                break_button.setChecked(true);
                train_button.setChecked(false);
                airplane_button.setChecked(false);
            }
        });

        train_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(false);
                work_button.setChecked(false);
                bed_button.setChecked(false);
                break_button.setChecked(false);
                train_button.setChecked(true);
                airplane_button.setChecked(false);
            }
        });

        airplane_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                home_button.setChecked(false);
                work_button.setChecked(false);
                bed_button.setChecked(false);
                break_button.setChecked(false);
                train_button.setChecked(false);
                airplane_button.setChecked(true);
            }
        });

    }
}
