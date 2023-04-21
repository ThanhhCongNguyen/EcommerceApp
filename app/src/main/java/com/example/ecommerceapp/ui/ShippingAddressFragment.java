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
import com.example.ecommerceapp.adapter.MyOrderAdapter;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentMyOrderBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class ShippingAddressFragment extends Fragment implements View.OnClickListener {
    private FragmentShippingAddressBinding binding;
    private HomeViewModel homeViewModel;
    private ShippingAddressAdapter shippingAddressAdapter;
    private Callback callback;

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

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void init() {
        binding.backBtn.setOnClickListener(this::onClick);

        homeViewModel.getAllShippingAddress(homeViewModel.getUserId()).observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null) {
                if (addresses.size() > 0) {
                    if (addresses.size() <= 3) {
                        addressNotEmpty(addresses);
                    } else {
                        binding.floatingActionButton.setVisibility(View.GONE);
                    }
                } else {
                    addressEmpty();
                }
            }
        });


        homeViewModel.getAddressAfterCreate().observe(getViewLifecycleOwner(), address -> {
            if (address != null) {
                Log.d("thanh1", "address: " + address.getStreet());
            }
        });

        binding.floatingActionButton.setOnClickListener(view -> {
            createNewAddress();
        });


    }

    private void createNewAddress() {
        callback.openAddAddressFragment();
    }

    private void addressNotEmpty(ArrayList<Address> addresses) {
        shippingAddressAdapter = new ShippingAddressAdapter();
        binding.rcvShippingAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvShippingAddress.setAdapter(shippingAddressAdapter);
//        shippingAddressAdapter.setProducts();
    }

    private void addressEmpty() {
        binding.rcvShippingAddress.setVisibility(View.GONE);
        binding.icNoItem.setVisibility(View.VISIBLE);
        binding.noItemText.setVisibility(View.VISIBLE);
    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }

    public interface Callback {
        void openAddAddressFragment();
    }
}