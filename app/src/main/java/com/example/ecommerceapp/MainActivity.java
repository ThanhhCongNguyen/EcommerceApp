package com.example.ecommerceapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerceapp.databinding.ActivityMainBinding;
import com.example.ecommerceapp.ui.LoginFragment;
import com.example.ecommerceapp.ui.MainFragment;
import com.example.ecommerceapp.ui.SignUpFragment;
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

//        @SuppressLint("HardwareIds") String deviceID = Settings.Secure.getString(MainActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID);
//        Toast.makeText(this, deviceID, Toast.LENGTH_SHORT).show();
    }

    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
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