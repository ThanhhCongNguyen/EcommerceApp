package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentMainBinding;
import com.example.ecommerceapp.model.Favorites;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.ui.home_fragment.HomeFragment;
import com.example.ecommerceapp.utils.AddData;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private HomeViewModel homeViewModel;
    private final HomeFragment homeFragment = new HomeFragment();
    private final MyCartFragment myCartFragment = new MyCartFragment();
    private final NotificationFragment notificationFragment = new NotificationFragment();
    private final SettingFragment settingFragment = new SettingFragment();

    private FragmentManager fragmentManager;
    private Fragment active = homeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
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
        fragmentManager.beginTransaction().add(R.id.frameLayout1, myCartFragment).hide(myCartFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, homeFragment).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initData();
        homeFragmentCallback();
        myCartFragmentCallback();
        settingFragmentCallback();
    }

    @Override
    public void onResume() {
        super.onResume();
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
                    fragmentManager.beginTransaction().hide(active).show(myCartFragment).commit();
                    active = myCartFragment;
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

    private void initData() {
        binding.bottomNavigation.getOrCreateBadge(R.id.notifications).setNumber(4);
        if (homeViewModel.isLogin()) {
            homeViewModel.getUserFromShare().observe(getViewLifecycleOwner(), user -> {
                if (user != null) {

                }
            });
            homeViewModel.getMyCartMutableLiveDataFromServer().observe(getViewLifecycleOwner(), myCarts -> {
                if (myCarts != null) {
                    if (myCarts.size() > 0) {
                        HashMap<String, MyCart> hashMap = new HashMap<>();
                        for (int i = 0; i < myCarts.size(); i++) {
                            hashMap.put(myCarts.get(i).getCartId(), myCarts.get(i));
                        }
                        homeViewModel.setMyCartHashMap(hashMap);
                        binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(myCarts.size());
                    } else {
                        homeViewModel.setMyCartHashMap(new HashMap<>());
                        binding.bottomNavigation.removeBadge(R.id.save);
                    }
                }
            });

            homeViewModel.getCartAfterAdd().observe(getViewLifecycleOwner(), myCart -> {
                if (myCart != null) {
                    Log.d("thanh1", "main fragment: " + myCart.getCartId());
                    homeViewModel.addMyCartHashMap(myCart);
                    binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(homeViewModel.getMyCartHashMap().size());
                }
            });

            homeViewModel.getLiveDataAfterDeleted().observe(getViewLifecycleOwner(), myCart -> {
                if (myCart != null) {
                    homeViewModel.removeItemInMyCartHashMap(myCart);
                    Log.d("thanh1", "remove: " + homeViewModel.getMyCartHashMap().size());
                    if (homeViewModel.getMyCartHashMap().size() > 0) {
                        binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(homeViewModel.getMyCartHashMap().size());
                    } else {
                        binding.bottomNavigation.removeBadge(R.id.save);
                    }
                }
            });

        } else {
            // Anonymous User
        }
    }


    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frameLayout1, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void settingFragmentCallback() {
        settingFragment.setSettingFragmentCallback(new SettingFragment.SettingFragmentCallback() {
            @Override
            public void openMyOrderFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                MyOrderFragment myOrderFragment = new MyOrderFragment();
                transactionFragment(myOrderFragment);
//                AddData addData = new AddData();
//                addData.addProductToFirestore();
            }

            @Override
            public void openShippingAddressFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                ShippingAddressFragment shippingAddressFragment = new ShippingAddressFragment();
                transactionFragment(shippingAddressFragment);
            }

            @Override
            public void openMyReviewsFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                MyReviewFragment myReviewFragment = new MyReviewFragment();
                transactionFragment(myReviewFragment);
            }

            @Override
            public void openChangeInfoFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                ChangeInfoFragment changeInfoFragment = new ChangeInfoFragment();
                transactionFragment(changeInfoFragment);
            }

            @Override
            public void onClickBackButton() {
                binding.bottomNavigation.setVisibility(View.VISIBLE);
            }
        });
    }

    private void homeFragmentCallback() {
        homeFragment.setHomeFragmentCallback(new HomeFragment.HomeFragmentCallback() {
            @Override
            public void openMyCartFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                MyCartFragment myCartFragment = new MyCartFragment();
                transactionFragment(myCartFragment);
            }

            @Override
            public void openDetailFragment(Product product) {
                binding.bottomNavigation.setVisibility(View.GONE);
                DetailFragment detailFragment = new DetailFragment();
                transactionFragment(detailFragment);
            }

            @Override
            public void onClickBackButton() {
                binding.bottomNavigation.setVisibility(View.VISIBLE);
            }
        });
    }

    private void myCartFragmentCallback() {
        myCartFragment.setMyCartFragmentCallback(new MyCartFragment.MyCartCallback() {
            @Override
            public void openCheckOutFragment() {
                binding.bottomNavigation.setVisibility(View.GONE);
                CheckOutFragment checkOutFragment = new CheckOutFragment();
                transactionFragment(checkOutFragment);
            }
        });
    }
}