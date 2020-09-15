package com.vara.platform.MenuPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.vara.platform.HelperMethods.DBHelper;
import com.vara.platform.LoginActivity;
import com.vara.platform.Models.User;
import com.vara.platform.Models.ViewArray;
import com.vara.platform.R;

public class UserPage extends AppCompatActivity {

    String TAG = "UserPage";
    static TextView emailTV, firstNameTV, lastNameTV, phoneTV, cityTV;
    EditText firstNameET, lastNameET, phoneET, cityET;
    ImageButton editButton, saveButton, backButton;
    ViewArray userTVs, userETs;
    User currentUserInfo;
    User updatedUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        getSupportActionBar().hide(); // hide the title bar
        //initializing TextViews
        emailTV = findViewById(R.id.email_tv);
        firstNameTV = findViewById(R.id.firstName_tv);
        lastNameTV = findViewById(R.id.lastName_tv);
        phoneTV = findViewById(R.id.phone_tv);
        cityTV = findViewById(R.id.city_tv);
        //creating an array object of TextViews
        userTVs = new ViewArray(firstNameTV, lastNameTV, phoneTV, cityTV, emailTV);
        //initializing EditTexts
        firstNameET = findViewById(R.id.firstName_et);
        lastNameET = findViewById(R.id.lastName_et);
        phoneET = findViewById(R.id.phone_et);
        cityET = findViewById(R.id.city_et);
        //creating an array object of TextViews
        userETs = new ViewArray(firstNameET, lastNameET, phoneET, cityET);
        //initializing ImageButtons
        backButton = findViewById(R.id.backButton);
        editButton = findViewById(R.id.editButton);
        saveButton = findViewById(R.id.saveButton);

        //adding click listener to edit button
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.GONE);
                saveButton.setVisibility(View.VISIBLE);
                userTVs.makeInvisible();
                emailTV.setVisibility(View.VISIBLE);
                userETs.makeVisible();
                populateUserEditText();
            }
        });
        //adding click listener to save button
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.GONE);

                //creating a new user with the updated inputs
                updatedUserInfo = new User(
                        firstNameET.getText().toString(),
                        lastNameET.getText().toString(),
                        phoneET.getText().toString(),
                        cityET.getText().toString(),
                        emailTV.getText().toString());

                if(currentUserInfo != updatedUserInfo) {
                    DBHelper.updateUserProfile(updatedUserInfo);
                    populateUserTextView();
                }
                userTVs.makeVisible();
                userETs.makeInvisible();
            }
        });
        //adding click listener to back button
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //populating user TextViews with user data from database
        populateUserTextView();
    }

    public void populateUserTextView() {
        DBHelper.Update update = new DBHelper.Update() {
            @Override
            public void updateUI() {
                currentUserInfo = DBHelper.getUser();
                //userTVs is an Object; user.getUserInfoArray returns an ArrayList<String>; 5 is the number of TextViews
                for (int i = 0; i < 5; i++) {
                    TextView tv = (TextView) userTVs.getViewArray(i);
                    tv.setText(currentUserInfo.userInfoArray().get(i));
                }
            }
        };
        DBHelper.getUserProfile(update, this);
    }

    public void populateUserEditText() {
        DBHelper.Update update = new DBHelper.Update() {
            @Override
            public void updateUI() {
                currentUserInfo = DBHelper.getUser();
                //userETs is an Object; user.getUserInfoArray returns an ArrayList<String>; 4 is the number of EditTexts
                for (int i = 0; i < 4 ; i++) {
                    EditText et = (EditText) userETs.getViewArray(i);
                    et.setText(currentUserInfo.userInfoArray().get(i));
                }
            }
        };
        DBHelper.getUserProfile(update, this);
    }

    @Override
    public void onBackPressed() {
        if (DBHelper.getUser() != null) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
