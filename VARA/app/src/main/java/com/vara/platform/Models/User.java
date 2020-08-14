package com.vara.platform.Models;

import java.util.ArrayList;
import java.util.Arrays;


public class User {
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String email;

    public User() {};

    public User(String firstName, String lastName, String phone, String city, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.email = email;
    }

    public ArrayList<String> userInfoArray() {
        ArrayList<String> infoArray = new ArrayList<>(Arrays.asList(this.firstName, this.lastName, this.phone, this.city, this.email));
        return infoArray;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }
}
