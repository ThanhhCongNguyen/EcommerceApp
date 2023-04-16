package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyCartAdapter;
import com.example.ecommerceapp.adapter.MyFavoritesAdapter;
import com.example.ecommerceapp.databinding.FragmentLoginBinding;
import com.example.ecommerceapp.databinding.FragmentMyCartBinding;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class MyCartFragment extends Fragment implements View.OnClickListener {
    private FragmentMyCartBinding binding;
    private MyCartAdapter myCartAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            backToHomeFragment();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        binding.backBtn.setOnClickListener(this);

        if (!homeViewModel.isLogin()) {
            binding.rcvCart.setVisibility(View.GONE);
            binding.tv1.setVisibility(View.GONE);
            binding.checkOutBtn.setVisibility(View.GONE);
            binding.totalPriceText.setVisibility(View.GONE);
            binding.icNotLogin.setVisibility(View.VISIBLE);
            binding.requestLoginText.setVisibility(View.VISIBLE);
            binding.requestLoginButton.setVisibility(View.VISIBLE);
            binding.cartEmptyImage.setVisibility(View.GONE);
            binding.cartEmptyText.setVisibility(View.GONE);
            binding.purchaseNow.setVisibility(View.GONE);
        } else {
            homeViewModel.getMyCartMutableLiveData().observe(getViewLifecycleOwner(), myCarts -> {
                if (myCarts != null && myCarts.size() > 0) {
                    myCartIsNotEmpty(myCarts);
                } else {
                    myCartIsEmpty();
                }
            });
        }
    }

    private void myCartIsEmpty() {
        binding.rcvCart.setVisibility(View.GONE);
        binding.tv1.setVisibility(View.GONE);
        binding.checkOutBtn.setVisibility(View.GONE);
        binding.totalPriceText.setVisibility(View.GONE);
        binding.icNotLogin.setVisibility(View.GONE);
        binding.requestLoginText.setVisibility(View.GONE);
        binding.requestLoginButton.setVisibility(View.GONE);
        binding.cartEmptyImage.setVisibility(View.VISIBLE);
        binding.cartEmptyText.setVisibility(View.VISIBLE);
        binding.purchaseNow.setVisibility(View.VISIBLE);
    }

    private void myCartIsNotEmpty(ArrayList<MyCart> myCarts) {
        binding.view1.setVisibility(View.GONE);
        binding.tv1.setVisibility(View.VISIBLE);
        binding.checkOutBtn.setVisibility(View.VISIBLE);
        binding.totalPriceText.setVisibility(View.VISIBLE);
        binding.rcvCart.setVisibility(View.VISIBLE);
        binding.icNotLogin.setVisibility(View.GONE);
        binding.requestLoginText.setVisibility(View.GONE);
        binding.requestLoginButton.setVisibility(View.GONE);
        binding.cartEmptyImage.setVisibility(View.GONE);
        binding.cartEmptyText.setVisibility(View.GONE);
        binding.purchaseNow.setVisibility(View.GONE);

        myCartAdapter = new MyCartAdapter();
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        myCartAdapter.setProducts(myCarts);
        binding.rcvCart.setAdapter(myCartAdapter);

    }


    private void backToHomeFragment() {
        getParentFragmentManager().popBackStack();
    }

}