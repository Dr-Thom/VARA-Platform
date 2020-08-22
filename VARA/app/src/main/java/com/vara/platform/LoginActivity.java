package com.vara.platform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.vara.platform.HelperMethods.DBHelper;

public class LoginActivity extends AppCompatActivity {
    Button homeButton, loginButton, signUpButton;
    TextView editEmailAddress, editPassword;
    AlertDialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide(); // hide the title bar
        Window window = LoginActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // clear FLAG_TRANSLUCENT_STATUS flag:
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.colorPink)); // finally change the color

        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);

        editEmailAddress = (TextView) findViewById(R.id.editEmailAddress);
        editPassword = (TextView) findViewById(R.id.editPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    public void doLogin(){
        String email = editEmailAddress.getText().toString();
        String password = editPassword.getText().toString();
        if (email.equals("") && password.equals("") || password.equals("") || email.equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_LONG).show();
            return;
        }

        DBHelper.signIn(this, email, password);
    }

    @Override
    public void onBackPressed() {
        if (DBHelper.getUser() != null) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }
    }
}