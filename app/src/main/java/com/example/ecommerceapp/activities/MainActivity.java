package com.example.ecommerceapp.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.ActivityMainBinding;
import com.example.ecommerceapp.ui.MainFragment;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity {

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
        observeLiveData();
    }

    private void observeLiveData() {
        homeViewModel.getUserLiveData().observe(this, user -> {
            if (user != null) {
                homeViewModel.saveUserToSharePreferences();
                homeViewModel.saveUserIdToSharePreferences(user.getUserId());
                MainFragment mainFragment = new MainFragment();
                transactionFragment(mainFragment);
            }
        });
    }

    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}