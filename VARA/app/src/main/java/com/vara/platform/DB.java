package com.vara.platform;

import android.content.Intent;

import android.util.Log;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;


public class DB extends SignUp {

    public void storeDataInFireBsae(final String firstName, final String lastName, final String email,
                                    final String password, final String city, final String phone) {
        fAUTH.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DB.this, "User created", Toast.LENGTH_SHORT).show();

                    userID = fAUTH.getCurrentUser().getUid();
                    DocumentReference documentReference = fstor.collection("UserInfo").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("First Name", firstName);
                    user.put("Last Name", lastName);
                    user.put("Email", email);
                    user.put("Password", password);
                    user.put("Phone", phone);
                    user.put("City", city);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "User is created" + userID);
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(DB.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}







