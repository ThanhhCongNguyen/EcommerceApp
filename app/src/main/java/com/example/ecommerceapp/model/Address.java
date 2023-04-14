package com.example.ecommerceapp.model;

public class Address {
    private String userId;
    private String street;
    private String city;

    public Address(String userId, String street, String city) {
        this.userId = userId;
        this.street = street;
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
