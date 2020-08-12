package com.vara.platform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText fname, email, lname, phoneN, City, password;
    Button signup, loginButton;
    FirebaseAuth fAUTH;
    FirebaseFirestore fstor;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializingVaribles();

        navigateToLogInPage();

        validationAndDataStoring();

    }

    private void validationAndDataStoring() {
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
                storeDataInFireBsae(firstName, lastName, Email, Password, city, phone);
            }
        });
    }

    public void storeDataInFireBsae(final String firstName, final String lastName, final String email, final String password, final String city, final String phone) {
        fAUTH.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();

                    userID = fAUTH.getCurrentUser().getUid();
                    DocumentReference documentReference = fstor.collection("UserInfo").document(userID);
                    Map<String ,Object> user = new HashMap<>();
                    user.put("First Name", firstName);
                    user.put("Last Name",lastName);
                    user.put("Email", email);
                    user.put("Password", password);
                    user.put("Phone",phone);
                    user.put("City",city);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "User is created" + userID);
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(SignUp.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToLogInPage() {
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializingVaribles() {
        fname = findViewById(R.id.textFirstName);
        lname = findViewById(R.id.textLastName);
        email = findViewById(R.id.textEmail);
        phoneN = findViewById(R.id.textPhone);
        City = findViewById(R.id.textAddress);
        fAUTH = FirebaseAuth.getInstance();
        signup = findViewById(R.id.buttonSignUp);
        loginButton = (Button) findViewById(R.id.buttonLogin);
        password = findViewById(R.id.textPassword);
        fstor  = FirebaseFirestore.getInstance();
    }
}
