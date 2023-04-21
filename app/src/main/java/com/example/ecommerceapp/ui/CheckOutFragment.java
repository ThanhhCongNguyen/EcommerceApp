package com.example.ecommerceapp.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentCheckOutBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.utils.DeliveryMethod;
import com.example.ecommerceapp.utils.PaymentMethod;
import com.example.ecommerceapp.utils.Utilities;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.math.BigInteger;

public class CheckOutFragment extends Fragment {
    private FragmentCheckOutBinding binding;
    private HomeViewModel homeViewModel;
    private boolean hasAddress;

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

        binding.creditImage.setImageResource(R.drawable.cash_payment);
        binding.creditNumber.setText("Payment on delivery");

        int totalCost = homeViewModel.getTotalPriceToCheckoutV2() + homeViewModel.getDeliveryCost();
        binding.costTotalText.setText(Utilities.convertCurrency(String.valueOf(totalCost)).concat(" VND"));

        homeViewModel.getAllShippingAddress(homeViewModel.getUserId()).observe(getViewLifecycleOwner(), addresses -> {
            if (addresses != null) {
                if (addresses.size() > 0) {
                    hasAddress = true;
                    Address address = addresses.get(0);
                    binding.userName.setText(address.getUserName());
                    binding.userAddress.setText(address.getStreet() + ", " + address.getCity() + ", " + address.getCountry());
                } else {
                    hasAddress = false;
                    binding.userName.setText("Bạn chưa đặt địa chỉ nhận hàng mặc định");
                    binding.userName.setTextSize(14f);
                    binding.userName.setTypeface(Typeface.DEFAULT);
                }
            }
        });
    }

    private void onClickButton() {
        binding.backBtn.setOnClickListener(view -> {
            getParentFragmentManager().popBackStack();
        });

        binding.editDeliveryBtn.setOnClickListener(view -> {
            SelectDeliveryMethodDialog selectDeliveryMethodDialog = new SelectDeliveryMethodDialog();
            selectDeliveryMethodDialog.show(getParentFragmentManager(), "MyFragment");
            selectDeliveryMethodDialog.setCallback(deliveryMethod -> updateDelivery(deliveryMethod));
        });

        binding.editPaymentBtn.setOnClickListener(view -> {
            PaymentMethodDialog paymentMethodDialog = new PaymentMethodDialog();
            paymentMethodDialog.show(getParentFragmentManager(), "MyFragment");
            paymentMethodDialog.setCallback(paymentMethod -> updatePaymentMethod(paymentMethod));
        });

        binding.editAddressBtn.setOnClickListener(view -> {
            if (!hasAddress) {
                AddShippingAddressFragment fragment = new AddShippingAddressFragment();
                FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.frameLayout1, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {

            }
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

    private void updatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.PAYMENT_ON_DELIVERY) {
            binding.creditImage.setImageResource(R.drawable.cash_payment);
            binding.creditNumber.setText("Payment on delivery");
        } else if (paymentMethod == PaymentMethod.ATM) {
            binding.creditImage.setImageResource(R.drawable.vietcombank);
            binding.creditNumber.setText("Vietcombank (**** **** *998)");
        } else {
            binding.creditImage.setImageResource(R.drawable.images);
            binding.creditNumber.setText("Visa (**** **** *766)");
        }
    }
}