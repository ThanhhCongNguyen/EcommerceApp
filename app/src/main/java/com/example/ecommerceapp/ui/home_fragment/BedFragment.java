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
import com.example.ecommerceapp.adapter.BedAdapter;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.databinding.FragmentBedBinding;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class BedFragment extends Fragment {
    private FragmentBedBinding binding;
    private HomeViewModel homeViewModel;
    private BedAdapter productAdapter;
    private BedFragmentCallback bedFragmentCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = new BedAdapter();
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.bedRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.bedRecyclerView.setAdapter(productAdapter);

        homeViewModel.getAllBed().observe(requireActivity(), products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });

        productAdapter.setCallback(product -> {
            homeViewModel.setProductMutableLiveData(product);
            bedFragmentCallback.openDetailFragment(product);
        });
    }

    public void setBedFragmentCallback(BedFragmentCallback bedFragmentCallback) {
        this.bedFragmentCallback = bedFragmentCallback;
    }

    public interface BedFragmentCallback {
        void openDetailFragment(Product product);
    }
}