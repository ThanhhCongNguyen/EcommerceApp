package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class DetailFragment extends Fragment implements View.OnClickListener{
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
        initView();
        initData();

        binding.addToCartButton.setOnClickListener(v -> {
            if (homeViewModel.isLogin()) {

            } else {
                CustomDialogClass dialogFragment = new CustomDialogClass();
                dialogFragment.show(getParentFragmentManager(), "dialogFragment");
            }
        });

//        binding.plusButton.setOnClickListener(v -> {
//            int count = homeViewModel.getDefaultCount();
//            count++;
//            homeViewModel.setProductCountMutableLiveData(count);
//            binding.countText.setText(String.valueOf(count));
//        });
//
//        binding.minusButton.setOnClickListener(v -> {
//            int count = homeViewModel.getDefaultCount();
//            count--;
//            homeViewModel.setProductCountMutableLiveData(count);
//            binding.countText.setText(String.valueOf(count));
//        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.plusButton) {
            plusCount(view);
        } else if(view.getId() == R.id.minusButton) {
            minusCount(view);
        }
    }

    private void initView() {
        binding.plusButton.setOnClickListener(this::onClick);
        binding.minusButton.setOnClickListener(this::onClick);
    }

    private void initData() {
        binding.countText.setText(String.valueOf(homeViewModel.getDefaultCount()));

        homeViewModel.getProductMutableLiveData().observe(requireActivity(), product -> {
            binding.productName.setText(product.getProductName());
            binding.productPrice.setText("$ ".concat(product.getPrice()));
            Glide.with(requireContext())
                    .load(product.getImage())
                    .into(binding.productImage);
            binding.descriptionText.setText(product.getDescription());

        });

    }

    private void plusCount(View view) {
        int count = homeViewModel.getDefaultCount();
        Log.d("thanh1", "count 1: " + count);
//        count++;
        homeViewModel.setProductCountMutableLiveData(count);
//        homeViewModel.setProductCountMutableLiveData(count);
        binding.countText.setText(String.valueOf(count));
    }

    private void minusCount(View view) {
        int count = homeViewModel.getDefaultCount();
        Log.d("thanh1", "count 2: " + count);
        if(count > 1) {
//            count--;
            homeViewModel.setMinusProductCountMutableLiveData(count);
            binding.countText.setText(String.valueOf(count));
            binding.minusButton.setEnabled(true);
        }else {
            binding.minusButton.setEnabled(false);
        }
    }

}