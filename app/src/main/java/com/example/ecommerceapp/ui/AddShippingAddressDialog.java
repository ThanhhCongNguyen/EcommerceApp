package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ecommerceapp.databinding.DeliveryMethodLayoutBinding;
import com.example.ecommerceapp.databinding.FragmentAddShippingAddressBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.utils.DeliveryMethod;

public class AddShippingAddressDialog extends DialogFragment {
    private FragmentAddShippingAddressBinding binding;
    private Callback callback;


    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddShippingAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setOnClick();
    }

    private void init() {
        String[] country = {"HaNoi", "DaNang"};
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCity.setAdapter(aa);
        binding.spinnerCity.setSelection(0);
        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setOnClick() {

    }

    public interface Callback {
        void save(Address address);
    }
}
