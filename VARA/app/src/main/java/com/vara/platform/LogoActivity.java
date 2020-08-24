package com.vara.platform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.vara.platform.HelperMethods.DBHelper;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {
    Timer timer;
    Button signOffButton, adminButton;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

       // getSupportActionBar().hide(); // hide the title bar
        Window window = LogoActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // clear FLAG_TRANSLUCENT_STATUS flag:
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.setStatusBarColor(ContextCompat.getColor(LogoActivity.this, R.color.colorPink)); // finally change the color
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        signOffButton = (Button) findViewById(R.id.signOffButton);
        adminButton = (Button) findViewById(R.id.adminButton);
        
        signOffButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DBHelper.signOut();
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(intent);
                timer.cancel();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogoActivity.this, UserPage.class);
                startActivity(intent);
                timer.cancel();
            }
        });

        timer= new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                Intent intent = new Intent(LogoActivity.this, WelcomeMsg.class);
                startActivity(intent);
                finish();
            }
        }, 15000);
    }

    @Override
    public void onBackPressed() {
        if (DBHelper.getUser() != null) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}