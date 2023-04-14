package com.example.ecommerceapp.model;

import java.util.ArrayList;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private ArrayList<Product> myCarts;
    private ArrayList<Product> myFavorites;
    private ArrayList<Address> addresses;
    private ArrayList<Review> reviews;

    public User() {
    }

    public User(String userId, String userName, String email, String password, ArrayList<Address> addresses, ArrayList<Review> reviews) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.addresses = addresses;
        this.reviews = reviews;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
