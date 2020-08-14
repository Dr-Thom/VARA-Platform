package com.vara.platform.Models;

import android.view.View;

import java.util.ArrayList;

public class ViewArray {
    View firstName, lastName, email, phone, city;
    ArrayList<View> views = new ArrayList<>();

    public ViewArray(View... views) {
        for (int i = 0; i < views.length; i++) {
            this.views.add(views[i]);
        }
    }

    public View getViewArray(int i) {
        return this.views.get(i);
    }

    //making all views invisible
    public void makeInvisible() {
        for (int i = 0; i < this.views.size(); i++) {
            this.views.get(i).setVisibility(View.GONE);
        }
    }
    //making all views visible
    public void makeVisible() {
        for (int i = 0; i < this.views.size(); i++) {
            this.views.get(i).setVisibility(View.VISIBLE);
        }
    }
}
