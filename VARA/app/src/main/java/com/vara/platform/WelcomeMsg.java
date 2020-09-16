package com.vara.platform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
//class WelcomeMsg extends AppCompatActivity {
public class WelcomeMsg extends AppCompatActivity {
    TextView txt1, txt2, txt3, txt4, txt5, txt6 ;
    Timer timer;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (DBHelper.getUser() != null) {
        setContentView(R.layout.activity_welcome_msg);
        getSupportActionBar().hide(); // hide the title bar

        txt1 = (TextView) findViewById(R.id.textView1);
        txt2 = (TextView) findViewById(R.id.textView2);
        txt3 = (TextView) findViewById(R.id.textView3);
        txt4 = (TextView) findViewById(R.id.textView4);
        txt5 = (TextView) findViewById(R.id.textView5);
        txt6 = (TextView) findViewById(R.id.textView6);
        displayMsg();
        nextActivity();
    }
    private void nextActivity() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeMsg.this, AdsDisplay.class);
                startActivity(intent);
                finish();
            }
        }, 20000);
    }
    private void displayMsg() {
       Timer t = new Timer();
        final TextView[] ids = new TextView[6];
        ids[0] =txt1;ids[1] =txt2;ids[2] =txt3;ids[3] =txt4;ids[4] =txt5; ids[5] =txt6;
        int a = 0;
        for(int i = 0; i<=ids.length-1; i++)
        {
            final int position = i;
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            TextView txt = ids[position];
                            txt.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }, (a = a + 3000));
        };
    }
}