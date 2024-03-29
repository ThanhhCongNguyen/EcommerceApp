package com.example.ecommerceapp.ui.home_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CategoryAdapter;
import com.example.ecommerceapp.adapter.ViewPagerAdapter;
import com.example.ecommerceapp.databinding.FragmentHomeBinding;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.ui.MyCartFragment;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    private HomeFragmentCallback homeFragmentCallback;

    private ViewPagerAdapter viewPagerAdapter;
    private CategoryAdapter categoryAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        categoryAdapter = new CategoryAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        binding.viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Off plants");
            } else if (position == 1) {
                tab.setText("Des plants");
            } else if (position == 2) {
                tab.setText("Und plants");
            } else {
                tab.setText("Han plants");
            }
        }).attach();

        if (homeFragmentCallback != null) {
            homeFragmentCallback.onClickBackButton();
        }

        viewPagerAdapter.setViewPagerAdapterCallback(new ViewPagerAdapter.ViewPagerAdapterCallback() {
            @Override
            public void getCurrentFragment(Fragment fragment) {
                if (fragment instanceof ArmChairFragment) {
                    ArmChairFragment armChairFragment = (ArmChairFragment) fragment;
                    armChairFragment.setArmChairFragmentCallback(new ArmChairFragment.ArmChairFragmentCallback() {
                        @Override
                        public void openDetailFragment(Product product) {
                            homeFragmentCallback.openDetailFragment(product);
                        }
                    });
                } else if (fragment instanceof BedFragment) {
                    BedFragment bedFragment = (BedFragment) fragment;
                    bedFragment.setBedFragmentCallback(new BedFragment.BedFragmentCallback() {
                        @Override
                        public void openDetailFragment(Product product) {
                            homeFragmentCallback.openDetailFragment(product);
                        }
                    });
                } else if (fragment instanceof TableFragment) {
                    TableFragment bedFragment = (TableFragment) fragment;
                    bedFragment.setTableFragmentCallback(new TableFragment.TableFragmentCallback() {
                        @Override
                        public void openDetailFragment(Product product) {
                            homeFragmentCallback.openDetailFragment(product);
                        }
                    });
                } else if (fragment instanceof ChairFragment) {
                    ChairFragment bedFragment = (ChairFragment) fragment;
                    bedFragment.setChairFragmentCallback(new ChairFragment.ChairFragmentCallback() {
                        @Override
                        public void openDetailFragment(Product product) {
                            homeFragmentCallback.openDetailFragment(product);
                        }
                    });
                }
            }
        });

        init();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cartBtn) {
            openMyCartFragment();
        }
    }

    public void setHomeFragmentCallback(HomeFragmentCallback homeFragmentCallback) {
        this.homeFragmentCallback = homeFragmentCallback;
    }

    private void init() {
        binding.cartBtn.setOnClickListener(this);

        if (homeViewModel.isLogin()) {

        } else {
            binding.quantityItemInCart.setVisibility(View.GONE);
        }
    }

    private void openMyCartFragment() {
        homeFragmentCallback.openMyCartFragment();
    }

    public interface HomeFragmentCallback {
        void openMyCartFragment();

        void openDetailFragment(Product product);

        void onClickBackButton();
    }
}