package com.vara.platform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.vara.platform.HelperMethods.ConfigClass;
import com.vara.platform.HelperMethods.DBHelper;

public class LogoActivity extends AppCompatActivity {

    Button signOffButton, adminButton;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        //        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        Window window = LogoActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //// clear FLAG_TRANSLUCENT_STATUS flag:
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); //// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.setStatusBarColor(ContextCompat.getColor(LogoActivity.this, R.color.colorPink)); // finally change the color
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        signOffButton = (Button) findViewById(R.id.signOffButton);
        adminButton = (Button) findViewById(R.id.adminButton);
        //homeButton = (Button) findViewById(R.id.homeButton);


        signOffButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DBHelper.signOut();
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(intent);
                //setContentView(R.layout.loginpage);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(intent);
                //setContentView(R.layout.loginpage);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
            }
        });

        /*homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.logopage);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();

            }
        });*/
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
}