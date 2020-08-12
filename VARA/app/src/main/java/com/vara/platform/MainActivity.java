package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup =  findViewById(R.id.buttonSignUp);

    }
    public void goToSignUpPage (View view){
        Intent instant = new Intent(this, SignUp.class);
        startActivity(instant);
    }
}