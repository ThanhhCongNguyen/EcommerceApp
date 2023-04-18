package com.example.ecommerceapp.utils;

import android.content.Context;
import android.widget.Toast;

public class Utilities {
    public static final String TAG = "thanh";
    public static final String PREFS_NAME = "sharePreferences";

    public static final void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static final String convertCurrency(String price) {
        int n = price.length();
        String totalPrice = "";
        if (n == 4) {
            String s1 = price.substring(0, 1);
            String s2 = price.substring(1);
            totalPrice = s1.concat(",").concat(s2);
        } else if (n == 5) {
            String s1 = price.substring(0, 2);
            String s2 = price.substring(2);
            totalPrice = s1.concat(",").concat(s2);
        } else if (n == 6) {
            String s1 = price.substring(0, 3);
            String s2 = price.substring(3);
            totalPrice = s1.concat(",").concat(s2);
        } else if (n == 7) {
            String s1 = price.substring(0, 1);
            String s2 = price.substring(1, 4);
            String s3 = price.substring(4);
            totalPrice = s1.concat(",").concat(s2).concat(",").concat(s3);
        } else {
            String s1 = price.substring(0, 2);
            String s2 = price.substring(2, 5);
            String s3 = price.substring(5);
            totalPrice = s1.concat(",").concat(s2).concat(",").concat(s3);
        }
        return totalPrice;
    }
}
