package com.vara.platform.Models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String city;

    public User(String firstName, String lastName, String email, String password, String phone, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }
}
