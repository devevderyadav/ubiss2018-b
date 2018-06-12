package com.ubiss2018.dev.ubiss2018;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by muradahmad on 07/06/2018.
 */

public class Play extends AppCompatActivity {
    public int counter = 30;
    Button play;
    TextView txtTimer;
    MediaPlayer mediaPlayer;
    boolean isPlaying = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_pause);
        play= (Button) findViewById(R.id.btnPlay);
        txtTimer= (TextView) findViewById(R.id.textView);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.birds);



        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {




                new CountDownTimer(30000, 1000){
                    public void onTick(long millisUntilFinished){


                        
                        play.setText("Pause");

                        if(counter > 0 ){
                            txtTimer.setText(String.valueOf(counter));
                            counter--;
                        }
                        mediaPlayer.start();


                    }
                    public  void onFinish(){
                        txtTimer.setText("");
                        play.setText("Play");
                        mediaPlayer.stop();

                        Intent myIntent = new Intent(Play.this,
                                PostQuestionnaire.class);
                        startActivity(myIntent);
                    }

                }.start();
            }




        });
    }





    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

}
