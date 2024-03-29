package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentChangeInfoBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;

public class ChangeInfoFragment extends Fragment implements View.OnClickListener {
    private FragmentChangeInfoBinding binding;
    private ShippingAddressAdapter shippingAddressAdapter;

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
        binding = FragmentChangeInfoBinding.inflate(inflater, container, false);
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
    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }
}