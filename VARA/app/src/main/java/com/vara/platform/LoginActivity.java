package com.vara.platform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.vara.platform.HelperMethods.VaraDbHelperFb;
import com.vara.platform.HelperMethods.VaraDbHelperSql;

public class LoginActivity extends AppCompatActivity {
    Button homeButton, loginButton, signUpButton;
    TextView editEmailAddress, editPassword;
    AlertDialog dialog;
    VaraDbHelperSql helper;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        ConfigClass config = new ConfigClass(LogoActivity.this);
//        config.setStatusBar();
        helper = new VaraDbHelperSql(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.onCreate(db);
        getSupportActionBar().hide(); // hide the title bar
        Window window = LoginActivity.this.getWindow();
//// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.colorPink));

//        homeButton = (Button) findViewById(R.id.homeButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);

        editEmailAddress = (TextView) findViewById(R.id.editEmailAddress);
        editPassword = (TextView) findViewById(R.id.editPassword);


//         homeButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                setContentView(R.layout.logopage);
//                Intent intent = new Intent(LoginActivity.this, LogoActivity.class);
//                startActivity(intent);
//                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doSomething();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                setContentView(R.layout.logopage);
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void doSomething(){
//        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//        startActivity(intent);
        String email = editEmailAddress.getText().toString();
        String password = editPassword.getText().toString();
        if (email.equals("") && password.equals("") || password.equals("") || email.equals("")) {
            Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_LONG).show();
            return;
        }

        VaraDbHelperFb.signIn(this, email, password);
    }

    @Override
    public void onBackPressed() {
        if (VaraDbHelperFb.getUser() != null) {
            super.onBackPressed();
        } else {
            moveTaskToBack(true);
        }
    }
}