package com.vara.platform;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DBHelper {
    String TAG = "DBHelper";
    static FirebaseAuth fAUTH = FirebaseAuth.getInstance();
    static FirebaseFirestore fstor = FirebaseFirestore.getInstance();
    static String userId;

    private static DBHelper dbHelper;

    public DBHelper() {
        super();
    }

    public static synchronized DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    };

    protected static void authenticate(final Context context,
                                final String firstName,
                                final String lastName,
                                final String email,
                                final String password,
                                final String phone,
                                final String city) {
        fAUTH.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    userId = Objects.requireNonNull(fAUTH.getCurrentUser()).getUid();
                    Toast.makeText(context, "User created", Toast.LENGTH_LONG).show();

                    DocumentReference documentReference = fstor.collection("UserInfo").document(userId);
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
                        }

                    });
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
