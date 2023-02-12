package com.example.ecommerceapp.ui.home_fragment;

import android.graphics.PorterDuff;
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
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CategoryAdapter;
import com.example.ecommerceapp.adapter.ProductAdapter;
import com.example.ecommerceapp.adapter.ViewPagerAdapter;
import com.example.ecommerceapp.databinding.FragmentHomeBinding;
import com.example.ecommerceapp.databinding.FragmentSignUpBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private ViewPagerAdapter viewPagerAdapter;

    private CategoryAdapter categoryAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryAdapter = new CategoryAdapter();
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

        viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        binding.viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Chair");
                }else if(position == 1){
                    tab.setText("Table");
                }else if(position == 2){
                    tab.setText("ArmChair");
                }else {
                    tab.setText("Bed");
                }
            }
        }).attach();
    }
}