package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogoActivity extends AppCompatActivity {

    Button okbutton, signOffButton, adminButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        okbutton = (Button) findViewById(R.id.okButton);
        signOffButton = (Button) findViewById(R.id.signOffButton);
        adminButton = (Button) findViewById(R.id.adminButton);
        //homeButton = (Button) findViewById(R.id.homeButton);



        okbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
                startActivity(intent);
                //setContentView(R.layout.loginpage);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();

            }
        });

        signOffButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
}