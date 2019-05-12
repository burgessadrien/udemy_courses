package com.burgessadrien.timerdemo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  Counter using runnables
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Runnable has run!", "a second must have passed...");
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
        */

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Countdown is counting down every second
                Log.i("Second left: ", String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // Counter is finished, after 10 seconds
                Log.i("DONE!", "Countdown timer has Finished!");
            }
        }.start();

    }
}
