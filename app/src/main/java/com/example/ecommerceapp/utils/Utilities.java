package com.example.ecommerceapp.utils;

import android.content.Context;
import android.widget.Toast;

public class Utilities {
    public static final void toast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
