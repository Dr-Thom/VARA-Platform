package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button homeButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); // hide the title bar
        homeButton = (Button) findViewById(R.id.homeButton);
        loginButton = (Button) findViewById(R.id.loginButton);


         homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                setContentView(R.layout.logopage);
                Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doSomething();
            }
        });
    }
    public void doSomething(){
//        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//        startActivity(intent);
        Toast.makeText(LoginActivity.this, "Did something", Toast.LENGTH_LONG).show();
//
    }
}