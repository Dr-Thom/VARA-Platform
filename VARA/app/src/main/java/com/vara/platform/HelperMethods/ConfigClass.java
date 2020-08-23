package com.vara.platform.HelperMethods;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


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
