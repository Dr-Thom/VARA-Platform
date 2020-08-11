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
    Button signup, login;
    FirebaseAuth fAUTH;
    FirebaseFirestore fstor;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname = findViewById(R.id.Fname);
        lname = findViewById(R.id.Lname);
        email = findViewById(R.id.Email);
        phoneN = findViewById(R.id.Phone);
        City = findViewById(R.id.Address);
        fAUTH = FirebaseAuth.getInstance();
        signup = findViewById(R.id.singUpButton);
        password = findViewById(R.id.Password);
        fstor  = FirebaseFirestore.getInstance();

//        if (fAUTH.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }
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
                    password.setError("Password must be 6 characters long");
                    return;
                }
                fAUTH.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User created", Toast.LENGTH_SHORT).show();

                            userID = fAUTH.getCurrentUser().getUid();
                            DocumentReference documentReference = fstor.collection("UsersInfos").document(userID);
                            Map<String ,Object> user = new HashMap<>();
                            user.put("First name", firstName);
                            user.put("last name",lastName);
                            user.put("email", Email);
                            user.put("password",Password);
                            user.put("phone",phone);
                            user.put("city",city);

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
        });

    }
}
