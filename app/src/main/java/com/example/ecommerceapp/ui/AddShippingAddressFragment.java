package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class AddShippingAddressFragment extends Fragment implements View.OnClickListener {
    private FragmentShippingAddressBinding binding;
    private HomeViewModel homeViewModel;
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
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShippingAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        binding.backBtn.setOnClickListener(this::onClick);

        shippingAddressAdapter = new ShippingAddressAdapter();
        binding.rcvShippingAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvShippingAddress.setAdapter(shippingAddressAdapter);

        homeViewModel.getAddressAfterCreate().observe(getViewLifecycleOwner(), address -> {
            if (address != null) {
                Log.d("thanh1", "address: " + address.getStreet());
            }
        });


    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }
}