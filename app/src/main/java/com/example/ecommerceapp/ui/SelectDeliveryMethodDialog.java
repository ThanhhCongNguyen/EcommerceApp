package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecommerceapp.databinding.DeliveryMethodLayoutBinding;
import com.example.ecommerceapp.utils.DeliveryMethod;

public class SelectDeliveryMethodDialog extends DialogFragment {
    private DeliveryMethodLayoutBinding binding;
    private Callback callback;


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DeliveryMethodLayoutBinding.inflate(inflater, container, false);
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
        binding.fastDelivery.setOnClickListener(view -> {
            binding.fastDelivery.setChecked(true);
            binding.viettelPost.setChecked(false);
            binding.vietnamPost.setChecked(false);
            binding.ninjaVan.setChecked(false);
            callback.selectDelivery(DeliveryMethod.FAST_DELIVERY);
            dismiss();
        });

        binding.viettelPost.setOnClickListener(view -> {
            binding.fastDelivery.setChecked(false);
            binding.viettelPost.setChecked(true);
            binding.vietnamPost.setChecked(false);
            binding.ninjaVan.setChecked(false);
            callback.selectDelivery(DeliveryMethod.VIETTEL_POST);
            dismiss();
        });

        binding.vietnamPost.setOnClickListener(view -> {
            binding.fastDelivery.setChecked(false);
            binding.viettelPost.setChecked(false);
            binding.vietnamPost.setChecked(true);
            binding.ninjaVan.setChecked(false);
            callback.selectDelivery(DeliveryMethod.VIETNAM_POST);
            dismiss();
        });

        binding.ninjaVan.setOnClickListener(view -> {
            binding.fastDelivery.setChecked(false);
            binding.viettelPost.setChecked(false);
            binding.vietnamPost.setChecked(false);
            binding.ninjaVan.setChecked(true);
            callback.selectDelivery(DeliveryMethod.NINJA_VAN);
            dismiss();
        });
    }

    public interface Callback {
        void selectDelivery(DeliveryMethod deliveryMethod);
    }
}
