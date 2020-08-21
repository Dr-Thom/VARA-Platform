package com.vara.platform;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
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
        getSupportActionBar().hide(); // hide the title bar


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



