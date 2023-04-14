package com.example.ecommerceapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.ActivityMainBinding;
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
//        LoginFragment loginFragment = new LoginFragment();
        homeViewModel = new ViewModelProvider(MainActivity.this).get(HomeViewModel.class);
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);

//        @SuppressLint("HardwareIds") String deviceID = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
//        Toast.makeText(this, deviceID, Toast.LENGTH_SHORT).show();
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
    public void onRegisterSuccess(FirebaseUser firebaseUser) {
        homeViewModel.saveUserToSharePreferences();
        MainFragment mainFragment = new MainFragment();
        transactionFragment(mainFragment);
    }
}