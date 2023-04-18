package com.example.ecommerceapp.repository.database.local_database;

import static com.example.ecommerceapp.utils.Utilities.PREFS_NAME;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.net.PortUnreachableException;

public class LocalDatabase {

    public void saveUserToSharePreferences(Application application) {
        SharedPreferences sharedpreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.apply();
    }

    public void saveUserIdToSharePreferences(Application application, String userId) {
        SharedPreferences sharedpreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    public void clearUserIdToSharePreferences(Application application) {
        SharedPreferences sharedpreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("userId", "");
        editor.apply();
    }

    public void clearUserToSharePreferences(Application application) {
        SharedPreferences sharedpreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.apply();
    }

    public boolean isLogin(Application application) {
        SharedPreferences shared = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isLogin = (shared.getBoolean("isLogin", false));
        return isLogin;
    }

    public String getUserId(Application application) {
        SharedPreferences shared = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String userId = (shared.getString("userId", "default"));
        return userId;
    }

}
