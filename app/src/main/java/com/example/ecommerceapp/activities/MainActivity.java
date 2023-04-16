package com.example.ecommerceapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.ActivityMainBinding;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.ui.LoginFragment;
import com.example.ecommerceapp.ui.MainFragment;
import com.example.ecommerceapp.ui.SignUpFragment;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnItemSelectedListener, SignUpFragment.Callback {

    private ActivityMainBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        homeViewModel = new ViewModelProvider(MainActivity.this).get(HomeViewModel.class);
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);

    }

    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
//        fragmentTransaction.addToBackStack(null);
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, signUpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
//        transactionFragment(signUpFragment);
    }

    @Override
    public void onRegisterSuccess(User user) {
        homeViewModel.saveUserToSharePreferences();
        homeViewModel.saveUserIdToSharePreferences(user.getUserId());
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);
    }

    @Override
    public void navigateLoginFragment() {

    }
}