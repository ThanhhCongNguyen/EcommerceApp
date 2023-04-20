package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecommerceapp.databinding.DeliveryMethodLayoutBinding;
import com.example.ecommerceapp.databinding.RequestLoginLayoutBinding;
import com.example.ecommerceapp.utils.DeliveryMethod;

public class RequestLoginDialog extends DialogFragment {
    private RequestLoginLayoutBinding binding;
    private Callback callback;


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RequestLoginLayoutBinding.inflate(inflater, container, false);
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
        binding.loginButton.setOnClickListener(view -> {
            callback.accept();
        });

        binding.cancelButton.setOnClickListener(view -> {
            dismiss();
        });

    }

    public interface Callback {
        void accept();
    }
}
