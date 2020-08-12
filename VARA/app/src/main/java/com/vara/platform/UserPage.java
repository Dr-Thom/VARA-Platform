package com.vara.platform;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class UserPage extends AppCompatActivity {

    TextView emailTV, firstNameTV, lastNameTV, phoneTV, cityTV;
    EditText firstNameET, lastNameET, phoneET, cityET;
    ImageButton editButton, saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);
        //initializing TextViews
        emailTV = findViewById(R.id.email_tv);
        firstNameTV = findViewById(R.id.firstName_tv);
        lastNameTV = findViewById(R.id.lastName_tv);
        phoneTV = findViewById(R.id.phone_tv);
        cityTV = findViewById(R.id.city_tv);
        //initializing EditTexts
        firstNameET = findViewById(R.id.firstName_et);
        lastNameET = findViewById(R.id.lastName_et);
        phoneET = findViewById(R.id.phone_et);
        cityET = findViewById(R.id.city_et);
        //initializing ImageButtons
        editButton = findViewById(R.id.editButton);
        saveButton = findViewById(R.id.saveButton);

        //adding click listener to edit button
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.GONE);
                saveButton.setVisibility(View.VISIBLE);
            }
        });
        //adding click listener to save button
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editButton.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.GONE);
            }
        });
    }

}
