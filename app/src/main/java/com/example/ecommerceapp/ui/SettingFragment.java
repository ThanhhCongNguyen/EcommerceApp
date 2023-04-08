package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyOrderAdapter;
import com.example.ecommerceapp.databinding.FragmentNotificationBinding;
import com.example.ecommerceapp.databinding.FragmentSettingBinding;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private FragmentSettingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.myOrderLayout) {
            openMyOrderFragment();
        } else if (view.getId() == R.id.shippingAddressLayout) {
            openShippingAddressFragment();
        } else if (view.getId() == R.id.paymentMethodLayout) {

        } else if (view.getId() == R.id.myReviewLayout) {
            openMyReviewsFragment();
        } else if (view.getId() == R.id.settingLayout) {
            openChangeInfoFragment();
        }
    }

    private void initView() {
        binding.myOrderLayout.setOnClickListener(this::onClick);
        binding.shippingAddressLayout.setOnClickListener(this::onClick);
        binding.paymentMethodLayout.setOnClickListener(this::onClick);
        binding.myReviewLayout.setOnClickListener(this::onClick);
        binding.settingLayout.setOnClickListener(this::onClick);

    }

    private void initData() {

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


}