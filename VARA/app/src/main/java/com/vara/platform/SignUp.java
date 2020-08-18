package com.vara.platform;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vara.platform.HelperMethods.DBHelper;
import com.vara.platform.Models.User;

public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText fname, email, lname, phoneN, city, password;
    Button signUpButton, logInButton;
    FirebaseAuth fAUTH;
    FirebaseFirestore fstor;
    User user;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide(); // hide the title bar
        Window window = SignUp.this.getWindow();
//// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(SignUp.this,R.color.colorPink));

        fname = findViewById(R.id.textFirstName);
        lname = findViewById(R.id.textLastName);
        email = findViewById(R.id.textEmail);
        phoneN = findViewById(R.id.textPhone);
        city = findViewById(R.id.textAddress);
        fAUTH = FirebaseAuth.getInstance();
        signUpButton = findViewById(R.id.buttonSignUp);
        logInButton = findViewById(R.id.buttonLogin);
        password = findViewById(R.id.textPassword);
        fstor = FirebaseFirestore.getInstance();

//        if (fAUTH.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }
        logInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = fname.getText().toString();
                final String lastName = lname.getText().toString();
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                final String city = SignUp.this.city.getText().toString();
                final String phone = phoneN.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(firstName)) {
                    fname.setError("First Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(lastName)) {
                    lname.setError("Last Name  is Required");
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    SignUp.this.city.setError("City Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    phoneN.setError("Phone Number is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("Password is Required");
                    return;
                }
                if (Password.length() < 6) {
                    password.setError("Password must be a minimum of 6 characters long");
                    return;
                }
                //creating a new user object based on the user inputs
                user = new User(firstName, lastName, phone, city, Email);
                onUserSignUp(user, Password);
            }
        });

    }

    private void onUserSignUp(User user, String password) {
        //creating new user in database
        DBHelper.authenticate(getApplicationContext(), user, password);
    }
}