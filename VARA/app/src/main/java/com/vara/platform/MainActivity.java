package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vara.platform.HelperMethods.VaraDbHelperFb;
import com.vara.platform.MenuPages.UserPage;

public class MainActivity extends AppCompatActivity {
    Button signup, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //signup = findViewById(R.id.buttonSignUp);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogoActivity.class);
                startActivity(intent);
                //setContentView(R.layout.loginpage);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
            }
        });
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
        if (VaraDbHelperFb.getUser() != null) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}