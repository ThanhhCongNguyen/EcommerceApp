package com.example.ecommerceapp.ui.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ArmChairAdapter;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.databinding.FragmentArmchairBinding;
import com.example.ecommerceapp.databinding.FragmentTableBinding;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.ui.DetailFragment;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class ArmChairFragment extends Fragment {
    private FragmentArmchairBinding binding;
    private HomeViewModel homeViewModel;
    private ArmChairAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = new ArmChairAdapter();
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArmchairBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.armChairRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.armChairRecyclerView.setAdapter(productAdapter);

        productAdapter.setCallback(product -> {
            homeViewModel.setProductMutableLiveData(product);
            FragmentManager fm = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new DetailFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        homeViewModel.getAllArmChair().observe(requireActivity(), products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });
    }
}