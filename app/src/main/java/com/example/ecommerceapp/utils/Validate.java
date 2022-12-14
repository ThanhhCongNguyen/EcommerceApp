package com.example.ecommerceapp.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validate {
    public static boolean checkIsValidPhoneNumber(String phoneNumber) {
        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public static boolean checkIsValidEmailAddress(String phoneNumber) {
        return Patterns.EMAIL_ADDRESS.matcher(phoneNumber).matches();
    }

    public static boolean checkPasswordLength(String password) {
        return password.length() >= 6;
    }

    public static boolean checkPasswordAndConfirm(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
