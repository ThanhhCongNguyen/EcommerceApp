package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CategoryAdapter;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.databinding.FragmentHomeBinding;
import com.example.ecommerceapp.databinding.FragmentSignUpBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private HomeViewModel homeViewModel;
    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        categoryAdapter = new CategoryAdapter();
        productAdapter = new ProductAdapter();
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
        binding.recyclerViewCategory.setAdapter(categoryAdapter);
        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.recyclerMain.setAdapter(productAdapter);
        binding.recyclerMain.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        homeViewModel.getLiveCategoryData().observe(getActivity(), categories -> {
            if (categories != null) {
                categoryAdapter.setCategoryList(categories);
            }
        });

        homeViewModel.getLiveProductData().observe(getActivity(), products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });

        categoryAdapter.setCallback(new CategoryAdapter.Callback() {
            @Override
            public void onItemClick(int position) {

            }
        });


    }
}