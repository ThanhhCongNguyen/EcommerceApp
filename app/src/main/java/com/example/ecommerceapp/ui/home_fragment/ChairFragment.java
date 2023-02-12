package com.example.ecommerceapp.ui.home_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ChairAdapter;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.databinding.FragmentBedBinding;
import com.example.ecommerceapp.databinding.FragmentChairBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class ChairFragment extends Fragment {
    private FragmentChairBinding binding;
    private HomeViewModel homeViewModel;
    private ChairAdapter productAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = new ChairAdapter();
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChairBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.chairRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.chairRecyclerView.setAdapter(productAdapter);

        homeViewModel.getAllChair().observe(requireActivity(), products -> {
            if(products != null){
                productAdapter.setProducts(products);
            }
        });
    }
}