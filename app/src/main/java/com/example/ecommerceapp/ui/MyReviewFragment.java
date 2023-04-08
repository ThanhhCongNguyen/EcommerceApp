package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapp.adapter.MyReviewAdapter;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentMyReviewBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;

public class MyReviewFragment extends Fragment {
    private FragmentMyReviewBinding binding;
    private MyReviewAdapter myReviewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyReviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        myReviewAdapter = new MyReviewAdapter();
        binding.rcvMyReviews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMyReviews.setAdapter(myReviewAdapter);
    }
}