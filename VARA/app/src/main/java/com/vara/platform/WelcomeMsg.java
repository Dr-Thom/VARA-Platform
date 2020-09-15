package com.vara.platform;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
//class WelcomeMsg extends AppCompatActivity {
public class WelcomeMsg extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (DBHelper.getUser() != null) {
        setContentView(R.layout.activity_welcome_msg);
        getSupportActionBar().hide(); // hide the title bar

        ArrayList<String> id = new ArrayList<String>();
        id.add("textview1");
        id.add("textview2");
        id.add("textview3");
        id.add("textview4");
        id.add("textview5");
        id.add("textview6");
        System.out.println(id);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeMsg.this, AdsDisplay.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
        //} else { return; }
    }

    Handler handler1 = new Handler() {
            @Override
            public void publish( LogRecord logRecord ){ }
            @Override
            public void flush(){ }
            @Override
            public void close() throws SecurityException{ }

    };

            //    private void getOnCreate( void onCreate ){
            //        onCreate;
            //    }
}