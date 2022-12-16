package com.example.ecommerceapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerceapp.databinding.ActivityMainBinding;
import com.example.ecommerceapp.ui.HomeFragment;
import com.example.ecommerceapp.ui.LoginFragment;
import com.example.ecommerceapp.ui.MainFragment;
import com.example.ecommerceapp.ui.MyCartFragment;
import com.example.ecommerceapp.ui.NotificationFragment;
import com.example.ecommerceapp.ui.SettingFragment;
import com.example.ecommerceapp.ui.SignUpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnItemSelectedListener, SignUpFragment.Callback {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        LoginFragment loginFragment = new LoginFragment();
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);
    }

    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void loginSuccess() {
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);
    }

    @Override
    public void navigateSignUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        transactionFragment(signUpFragment);
    }

    @Override
    public void onRegisterSuccess(FirebaseUser firebaseUser) {
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);
    }
}