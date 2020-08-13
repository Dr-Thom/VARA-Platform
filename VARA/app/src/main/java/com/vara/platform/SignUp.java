package com.vara.platform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vara.platform.Models.User;

public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText fname, email, lname, phoneN, City, password;
    Button signup, loginButton;
    FirebaseAuth fAUTH;
    FirebaseFirestore fstor;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname = findViewById(R.id.textFirstName);
        lname = findViewById(R.id.textLastName);
        email = findViewById(R.id.textEmail);
        phoneN = findViewById(R.id.textPhone);
        City = findViewById(R.id.textAddress);
        fAUTH = FirebaseAuth.getInstance();
        signup = findViewById(R.id.buttonSignUp);
        loginButton = findViewById(R.id.buttonLogin);
        password = findViewById(R.id.textPassword);
        fstor = FirebaseFirestore.getInstance();

//        if (fAUTH.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = fname.getText().toString();
                final String lastName = lname.getText().toString();
                final String Email = email.getText().toString().trim();
                final String Password = password.getText().toString().trim();
                final String city = City.getText().toString();
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
                    City.setError("City Name is Required");
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

                //creating a new user object
                user = new User(firstName, lastName, Email, Password, phone, city);
                //creating new user in database
                DBHelper.authenticate(getApplicationContext(), user);
            }
        });

    }
}