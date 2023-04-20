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

import com.example.ecommerceapp.databinding.FragmentCheckOutBinding;
import com.example.ecommerceapp.utils.DeliveryMethod;
import com.example.ecommerceapp.utils.Utilities;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class CheckOutFragment extends Fragment {
    private FragmentCheckOutBinding binding;
    private HomeViewModel homeViewModel;

    public CheckOutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckOutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        onClickButton();
    }

    private void init() {
        binding.costText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getTotalPriceToCheckoutV2())).concat(" VND"));
        binding.deliveryName.setText("Fast Delivery");
        binding.deliveryCostText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getDeliveryCost())).concat(" VND"));

        int totalCost = homeViewModel.getTotalPriceToCheckoutV2() + homeViewModel.getDeliveryCost();
        binding.costTotalText.setText(Utilities.convertCurrency(String.valueOf(totalCost)).concat(" VND"));
    }

    private void onClickButton() {
        binding.backBtn.setOnClickListener(view -> {
            getParentFragmentManager().popBackStack();
        });

        binding.editDeliveryBtn.setOnClickListener(view -> {
            SelectDeliveryMethodDialog selectDeliveryMethodDialog;
            selectDeliveryMethodDialog = new SelectDeliveryMethodDialog();
            selectDeliveryMethodDialog.show(getParentFragmentManager(), "My  Fragment");
            selectDeliveryMethodDialog.setCallback(deliveryMethod -> updateDelivery(deliveryMethod));
        });
    }

    private void updateDelivery(DeliveryMethod deliveryMethod) {
        if (deliveryMethod == DeliveryMethod.FAST_DELIVERY) {
            binding.deliveryName.setText("Fast Delivery");
            homeViewModel.setDeliveryCost(15000);
        } else if (deliveryMethod == DeliveryMethod.VIETTEL_POST) {
            binding.deliveryName.setText("Viettel Post");
            homeViewModel.setDeliveryCost(16000);
        } else if (deliveryMethod == DeliveryMethod.VIETNAM_POST) {
            binding.deliveryName.setText("VietNam Post");
            homeViewModel.setDeliveryCost(17000);
        } else {
            binding.deliveryName.setText("Ninja Van");
            homeViewModel.setDeliveryCost(18000);
        }
        int totalCost = homeViewModel.getTotalPriceToCheckoutV2() + homeViewModel.getDeliveryCost();
        binding.deliveryCostText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getDeliveryCost())).concat(" VND"));
        binding.costTotalText.setText(Utilities.convertCurrency(String.valueOf(totalCost)).concat(" VND"));

    }
}