package com.vara.platform.HelperMethods;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ConfigClass {
    private AppCompatActivity thisActivity = new AppCompatActivity();

    public ConfigClass(AppCompatActivity _thisActivity){
        this.thisActivity =  _thisActivity;
    }
    public void setStatusBar(){

        this.thisActivity.getActionBar().hide(); // hide the title bar
        Window window = this.thisActivity.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
}
