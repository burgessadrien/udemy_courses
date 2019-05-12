package com.burgessadrien.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar timerSeekBar;
    private TextView timerText;
    private Button timerButton;
    private Boolean counterActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerSeekBar.setMax(10*60);
        timerSeekBar.setProgress(30); // Initial is 30 seconds
        timerText = findViewById(R.id.timertext);
        timerButton  = findViewById(R.id.timerButton);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes*60;
        String secondString = (seconds > 9) ? Integer.toString(seconds) : "0" + Integer.toString(seconds);
        timerText.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void controlTimer(View view) {
        if(!counterActive) {
            counterActive = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("Stop!");

            new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    counterActive = false;
                    timerSeekBar.setEnabled(true);
                    timerButton.setText("Go!");
                    timerText.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mplayer.start();
                }
            };
        } else {
            counterActive = false;
            timerSeekBar.setEnabled(true);
            timerButton.setText("Go!");
        }
    }

}
