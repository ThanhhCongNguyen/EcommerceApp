package com.example.ecommerceapp.ui;

import static com.example.ecommerceapp.utils.Utilities.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeFragment homeFragment = new HomeFragment();
        Log.d(TAG, "1" );
        transactionFragment(homeFragment);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    HomeFragment homeFragment = new HomeFragment();
                    Log.d(TAG, "1" );
                    transactionFragment(homeFragment);
                    return true;
                case R.id.save:
                    Log.d(TAG, "2" );
                    MyCartFragment myCartFragment = new MyCartFragment();
                    transactionFragment(myCartFragment);
                    return true;
                case R.id.notifications:
                    Log.d(TAG, "3" );
                    NotificationFragment notificationFragment = new NotificationFragment();
                    transactionFragment(notificationFragment);
                    return true;
                case R.id.personal:
                    Log.d(TAG, "4" );
                    SettingFragment settingFragment = new SettingFragment();
                    transactionFragment(settingFragment);
                    return true;
            }
            return false;
        }
    };

    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout1, fragment);
        fragmentTransaction.commit();
    }
}