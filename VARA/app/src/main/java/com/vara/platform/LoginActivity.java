package com.vara.platform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    Button homeButton, loginButton, signUpButton;
    TextView editEmailAddress, editPassword;
    FirebaseAuth mAuth;
    FirebaseFirestore fstor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); // hide the title bar

        mAuth = FirebaseAuth.getInstance();
        fstor  = FirebaseFirestore.getInstance();

        homeButton = (Button) findViewById(R.id.homeButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);

        editEmailAddress = (TextView) findViewById(R.id.editEmailAddress);
        editPassword = (TextView) findViewById(R.id.editPassword);


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

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "TAG" ;

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(LoginActivity.this, "Sign-In Success", Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
//
    }
}