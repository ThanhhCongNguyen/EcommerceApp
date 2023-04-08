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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.adapter.TableAdapter;
import com.example.ecommerceapp.databinding.FragmentHomeBinding;
import com.example.ecommerceapp.databinding.FragmentTableBinding;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class TableFragment extends Fragment {
    private FragmentTableBinding binding;
    private HomeViewModel homeViewModel;
    private TableAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = new TableAdapter();
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tableRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.tableRecyclerView.setAdapter(productAdapter);
        homeViewModel.getAllTable().observe(requireActivity(), products -> {
            if(products != null){
                productAdapter.setProducts(products);
            }
        });

        productAdapter.setCallback(new TableAdapter.Callback() {
            @Override
            public void onItemClick(Product product) {

            }
        });
    }
}