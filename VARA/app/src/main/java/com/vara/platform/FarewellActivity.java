package com.vara.platform;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class FarewellActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (DBHelper.getUser() != null) {
        setContentView(R.layout.activity_farewell);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(FarewellActivity.this, LogoActivity.class);
                startActivity(intent);
                finish();
            }
        }, 15000);
        //} else { return; }
    }
}
