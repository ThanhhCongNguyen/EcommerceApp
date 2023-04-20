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
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyOrderAdapter;
import com.example.ecommerceapp.databinding.FragmentSettingBinding;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.Objects;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private FragmentSettingBinding binding;
    private SettingFragmentCallback settingFragmentCallback;
    private HomeViewModel homeViewModel;

    public void setSettingFragmentCallback(SettingFragmentCallback settingFragmentCallback) {
        this.settingFragmentCallback = settingFragmentCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        if (settingFragmentCallback != null) {
            settingFragmentCallback.onClickBackButton();
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.myOrderLayout) {
            settingFragmentCallback.openMyOrderFragment();
        } else if (view.getId() == R.id.shippingAddressLayout) {
            settingFragmentCallback.openShippingAddressFragment();
        } else if (view.getId() == R.id.paymentMethodLayout) {
            settingFragmentCallback.openPaymentMethodFragment();
        } else if (view.getId() == R.id.myReviewLayout) {
            settingFragmentCallback.openMyReviewsFragment();
        } else if (view.getId() == R.id.settingLayout) {
            settingFragmentCallback.openChangeInfoFragment();
        } else if (view.getId() == R.id.requestLoginButton) {
            openLoginFragment();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        homeViewModel.getUserLiveData().removeObservers(getViewLifecycleOwner());
    }

    private void initView() {
        if (!homeViewModel.isLogin()) {
            binding.nestedScrollView.setVisibility(View.GONE);
            binding.icNotLogin.setVisibility(View.VISIBLE);
            binding.requestLoginText.setVisibility(View.VISIBLE);
            binding.requestLoginButton.setVisibility(View.VISIBLE);
        } else {
            binding.view1.setVisibility(View.GONE);
            binding.nestedScrollView.setVisibility(View.VISIBLE);
            binding.icNotLogin.setVisibility(View.GONE);
            binding.requestLoginText.setVisibility(View.GONE);
            binding.requestLoginButton.setVisibility(View.GONE);
        }

        binding.myOrderLayout.setOnClickListener(this);
        binding.shippingAddressLayout.setOnClickListener(this);
        binding.paymentMethodLayout.setOnClickListener(this);
        binding.myReviewLayout.setOnClickListener(this);
        binding.settingLayout.setOnClickListener(this);
        binding.requestLoginButton.setOnClickListener(this);

    }

    private void initData() {
//        User user = homeViewModel.getUserMutableLiveData().getValue();
        homeViewModel.getUserFromShare().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                binding.userName.setText(user.getUserName());
                binding.userEmail.setText(user.getEmail());
            }
        });
//        if (user != null) {
//            binding.userName.setText(user.getUserName());
//            binding.userEmail.setText(user.getEmail());
//            if (user.getAddresses() == null || user.getAddresses().size() == 0) {
//                binding.addressDetail.setText(R.string.no_order);
//            } else {
//                binding.addressDetail.setText(String.valueOf(user.getAddresses().size()).concat(" Addresses"));
//            }
//
//            if (user.getReviews() == null || user.getReviews().size() == 0) {
//                binding.reviewDetail.setText(R.string.no_review);
//            } else {
//                binding.reviewDetail.setText(String.valueOf(user.getReviews().size()).concat(" Reviews"));
//            }
//        }
    }

    private void openLoginFragment() {
        homeViewModel.clearUserCache();
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new LoginFragment());
        fragmentTransaction.commit();
    }

    private void openMyOrderFragment() {
        MyOrderFragment myOrderFragment = new MyOrderFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, myOrderFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openShippingAddressFragment() {
        ShippingAddressFragment shippingAddressFragment = new ShippingAddressFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, shippingAddressFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openMyReviewsFragment() {
        MyReviewFragment myReviewFragment = new MyReviewFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, myReviewFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openChangeInfoFragment() {
        ChangeInfoFragment changeInfoFragment = new ChangeInfoFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, changeInfoFragment)
                .addToBackStack(null)
                .commit();
    }


    public interface SettingFragmentCallback {
        void openMyOrderFragment();

        void openShippingAddressFragment();

        void openMyReviewsFragment();

        void openPaymentMethodFragment();

        void openChangeInfoFragment();

        void onClickBackButton();
    }


}