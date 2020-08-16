package com.vara.platform.HelperMethods;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.vara.platform.LogoActivity;
import com.vara.platform.MainActivity;
import com.vara.platform.Models.User;
import java.util.Objects;


public class DBHelper {
    static String TAG = "DBHelper";
    static FirebaseAuth fAUTH = FirebaseAuth.getInstance();
    static FirebaseFirestore fstor = FirebaseFirestore.getInstance();
    static DocumentReference docRef;
    static String userId;
    static User user = new User();
    static DBHelper dbHelper;

    private DBHelper() {
        super();
    }

    public interface Update {
        void updateUI();
    }

    public static User getUser() {
        return user;
    }

    //Signing up the new user and then creating the user profile in UserInfo collection on Firestore database
    public static void authenticate(final Context context, final User user, final String password) {
        fAUTH.createUserWithEmailAndPassword(user.getEmail(), password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    userId = Objects.requireNonNull(fAUTH.getCurrentUser()).getUid();
                    Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show();

                    docRef = fstor.collection("UserInfo").document(userId);

                    docRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(context, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void getUserProfile(final Update update) {
        Log.w(TAG, "user Id is " + userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    user = documentSnapshot.toObject(User.class);
                    update.updateUI();
                } else {
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public static void updateUserProfile(final User user) {
        docRef.update(
                "firstName", user.getFirstName(),
                "lastName", user.getLastName(),
                "phone", user.getPhone(),
                "city", user.getCity()
        );
    }

    public static void signIn(final Context context, final String email, final String password) {
        fAUTH.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "TAG";

                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser fUser = fAUTH.getCurrentUser();
                            userId = fUser.getUid();
                            docRef = fstor.collection("UserInfo").document(userId);
                            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        user = documentSnapshot.toObject(User.class);
                                        Toast.makeText(context, "Sign-in Successful" + userId, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(context, LogoActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(context, "Sign-in failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }

    public static void signOut(){
        fAUTH.signOut();
        userId= null;
        user = null;
    }

}
