package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.BedAdapter;
import com.example.ecommerceapp.databinding.FragmentBedBinding;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;
    private HomeViewModel homeViewModel;

    public DetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel.getProductMutableLiveData().observe(requireActivity(), product -> {
            binding.productName.setText(product.getProductName());
            binding.productPrice.setText("$ ".concat(product.getPrice()));
            Glide.with(requireContext())
                    .load(product.getImage())
                    .into(binding.productImage);
            binding.descriptionText.setText(product.getDescription());

        });

        binding.addToCartButton.setOnClickListener(v -> {
            if(homeViewModel.isLogin()){

            }else {

            }
        });
    }
}