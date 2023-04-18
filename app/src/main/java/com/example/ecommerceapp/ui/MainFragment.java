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
    private final FavoritesFragment favoritesFragment = new FavoritesFragment();
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
        fragmentManager.beginTransaction().add(R.id.frameLayout1, favoritesFragment).hide(favoritesFragment).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayout1, homeFragment).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initData();
        homeFragmentCallback();
        settingFragmentCallback();
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

    private void initData() {
        binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(2);
        binding.bottomNavigation.getOrCreateBadge(R.id.notifications).setNumber(4);
        if (homeViewModel.isLogin()) {
            homeViewModel.getUserFromShare().observe(getViewLifecycleOwner(), user -> {
                if (user != null) {

                }
            });

            homeViewModel.getFavoritesLiveDataFromServer(homeViewModel.getUserId()).observe(getViewLifecycleOwner(), favorites -> {
                if (favorites != null) {
                    binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(favorites.size());
                    homeViewModel.setMyFavoritesList(favorites);
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int i = 0; i < favorites.size(); i++) {
                        list.add(Integer.parseInt(favorites.get(i).getProduct().getProductId()));
                    }
                    homeViewModel.setIdOfFavorites(list);
                }
            });

            homeViewModel.isDeletedFavorite().observe(getViewLifecycleOwner(), favoriteId -> {
                if (favoriteId != null) {
                    Log.d("thanh1", "is deleted from main: " + favoriteId);
                    homeViewModel.getMyFavoritesList().remove(favoriteId);
                    binding.bottomNavigation.getOrCreateBadge(R.id.save).setNumber(homeViewModel.getMyFavoritesList().size() - 1);
                }
            });

//            homeViewModel.getMyCartMutableLiveDataFromServer().observe(getViewLifecycleOwner(), myCarts -> {
//                HashMap<String, MyCart> myCartHashMap = new HashMap<>();
//                if (myCarts != null) {
//                    for (int i = 0; i < myCarts.size(); i++) {
//                        myCartHashMap.put(myCarts.get(i).getCartId(), myCarts.get(i));
//                    }
//                }
//                homeViewModel.setMyCartHashMap(myCartHashMap);
//            });

        } else {
            // Anonymous User
        }
    }


    private void transactionFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
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
}