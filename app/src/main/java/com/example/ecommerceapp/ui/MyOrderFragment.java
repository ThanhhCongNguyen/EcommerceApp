package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyOrderAdapter;
import com.example.ecommerceapp.databinding.FragmentMyOrderBinding;

public class MyOrderFragment extends Fragment implements View.OnClickListener {
    private FragmentMyOrderBinding binding;
    private MyOrderAdapter myOrderAdapter;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            backToSettingFragment();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        binding.backBtn.setOnClickListener(this::onClick);

    }

    private void initData() {
        myOrderAdapter = new MyOrderAdapter();
        binding.rcvMyOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvMyOrder.setAdapter(myOrderAdapter);
    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }

}