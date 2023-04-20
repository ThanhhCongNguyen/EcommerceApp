package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentFavoritesBinding;
import com.example.ecommerceapp.databinding.FragmentSetPaymentMethodBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class PaymentMethodFragment extends Fragment {
    private FragmentSetPaymentMethodBinding binding;
    private HomeViewModel homeViewModel;

    public PaymentMethodFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSetPaymentMethodBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        if(homeViewModel.isLogin()) {

            binding.paymentOnDeliveryRadio.setChecked(true);
            binding.paymentOnDeliveryRadio.setOnClickListener(view -> {
                binding.paymentOnDeliveryRadio.setChecked(true);
                binding.paymentByATMRadio.setChecked(false);
                binding.paymentByViSaRadio.setChecked(false);
            });

            binding.paymentByATMRadio.setOnClickListener(view -> {
                binding.paymentOnDeliveryRadio.setChecked(false);
                binding.paymentByATMRadio.setChecked(true);
                binding.paymentByViSaRadio.setChecked(false);
            });

            binding.paymentByViSaRadio.setOnClickListener(view -> {
                binding.paymentOnDeliveryRadio.setChecked(false);
                binding.paymentByATMRadio.setChecked(false);
                binding.paymentByViSaRadio.setChecked(true);
            });
        }
    }
}
