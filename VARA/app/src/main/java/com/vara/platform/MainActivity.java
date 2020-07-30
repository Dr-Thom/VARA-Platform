package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.logopage);
        Button button = (Button) findViewById(R.id.okButton);
        Button homeButton = (Button) findViewById(R.id.homeButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.loginpage);
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