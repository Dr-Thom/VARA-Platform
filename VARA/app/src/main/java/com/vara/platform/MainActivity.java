package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vara.platform.HelperMethods.DBHelper;

public class MainActivity extends AppCompatActivity {
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.buttonSignUp);
    }
    public void goToSignUpPage (View view){
        Intent instant = new Intent(this, SignUp.class);
        startActivity(instant);
    }

    public void gotoUserInfo(View view) {
        Intent instant = new Intent(this, UserPage.class);
        startActivity(instant);
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