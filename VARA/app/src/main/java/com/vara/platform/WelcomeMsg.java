package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.TimerStat;

import com.vara.platform.HelperMethods.DBHelper;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeMsg extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (DBHelper.getUser() != null) {
        setContentView(R.layout.activity_welcome_msg);
        getSupportActionBar().hide(); // hide the title bar


            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeMsg.this, AdsDisplay.class);
                    startActivity(intent);
                    finish();
                }
            }, 15000);
        //} else { return; }
    }
}