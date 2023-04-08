package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentMainBinding;
import com.example.ecommerceapp.ui.home_fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private final HomeFragment homeFragment = new HomeFragment();
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();
    private final NotificationFragment notificationFragment = new NotificationFragment();
    private final SettingFragment settingFragment = new SettingFragment();

    private FragmentManager fragmentManager;
    private Fragment active = homeFragment;


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

        fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, settingFragment).hide(settingFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, notificationFragment).hide(notificationFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, favoritesFragment).hide(favoritesFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, homeFragment).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.save:
                    fragmentManager.beginTransaction().hide(active).show(favoritesFragment).commit();
                    active = favoritesFragment;
                    return true;
                case R.id.notifications:
                    fragmentManager.beginTransaction().hide(active).show(notificationFragment).commit();
                    active = notificationFragment;
                    return true;
                case R.id.personal:
                    fragmentManager.beginTransaction().hide(active).show(settingFragment).commit();
                    active = settingFragment;
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