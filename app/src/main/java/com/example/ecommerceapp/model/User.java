package com.example.ecommerceapp.model;

import com.example.ecommerceapp.utils.PaymentMethod;

import java.util.ArrayList;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private PaymentMethod paymentMethod = PaymentMethod.PAYMENT_ON_DELIVERY;

    public User() {
    }


    public User(String userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userId, String userName, String email, String password, PaymentMethod paymentMethod) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.paymentMethod = paymentMethod;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
