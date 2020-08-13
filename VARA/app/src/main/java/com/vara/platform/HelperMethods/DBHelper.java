package com.vara.platform.HelperMethods;

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
import com.vara.platform.MainActivity;
import com.vara.platform.Models.User;
import java.util.Objects;


public class DBHelper {
    String TAG = "DBHelper";
    static FirebaseAuth fAUTH = FirebaseAuth.getInstance();
    static FirebaseFirestore fstor = FirebaseFirestore.getInstance();
    static String userId;

    private static DBHelper dbHelper;

    private DBHelper() {
        super();
    }

    public static synchronized DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    };

    //Signing up the new user and then creating the user profile in UserInfo collection on Firestore database
    public static void authenticate(final Context context, final User user) {
        fAUTH.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    userId = Objects.requireNonNull(fAUTH.getCurrentUser()).getUid();
                    Toast.makeText(context, "User created", Toast.LENGTH_LONG).show();

                    DocumentReference documentReference = fstor.collection("UserInfo").document(userId);

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
