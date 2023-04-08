package com.example.ecommerceapp.ui.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.CategoryAdapter;
import com.example.ecommerceapp.adapter.ViewPagerAdapter;
import com.example.ecommerceapp.databinding.FragmentHomeBinding;
import com.example.ecommerceapp.ui.MyCartFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment implements View.OnClickListener {
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
        initView();
        viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        binding.viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.chair);
            } else if (position == 1) {
                tab.setText(R.string.table);
            } else if (position == 2) {
                tab.setText(R.string.arm_chair);
            } else {
                tab.setText(R.string.bed);
            }
        }).attach();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cartBtn) {
            openMyCartFragment();
        }
    }

    private void initView() {
        binding.cartBtn.setOnClickListener(this::onClick);
    }

    private void openMyCartFragment() {
        MyCartFragment myCartFragment = new MyCartFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout1, myCartFragment)
                .addToBackStack(null)
                .commit();
    }
}