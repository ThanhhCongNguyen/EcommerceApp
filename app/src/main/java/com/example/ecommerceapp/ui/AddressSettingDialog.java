package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecommerceapp.databinding.PaymentMethodDialogBinding;
import com.example.ecommerceapp.utils.PaymentMethod;

public class AddressSettingDialog extends DialogFragment {
    private PaymentMethodDialogBinding binding;
    private Callback callback;


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PaymentMethodDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setOnClick();
    }

    private void init() {

    }

    private void setOnClick() {
        binding.paymentOnDeliveryRadio.setOnClickListener(view -> {
            callback.selectPayment(PaymentMethod.PAYMENT_ON_DELIVERY);
            dismiss();
        });

        binding.atmPayment.setOnClickListener(view -> {
            callback.selectPayment(PaymentMethod.ATM);
            dismiss();
        });

        binding.visaPayment.setOnClickListener(view -> {
            callback.selectPayment(PaymentMethod.VISA);
            dismiss();
        });

    }

    public interface Callback {
        void selectPayment(PaymentMethod paymentMethod);
    }
}
