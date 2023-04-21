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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapp.adapter.SelectAddressAdapter;
import com.example.ecommerceapp.databinding.DeliveryMethodLayoutBinding;
import com.example.ecommerceapp.databinding.FragmentAddShippingAddressBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;
import com.example.ecommerceapp.databinding.SelectAddressDialogLayoutBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.utils.DeliveryMethod;

import java.util.ArrayList;

public class AddShippingAddressDialog extends Fragment {
    private SelectAddressDialogLayoutBinding binding;
    private Callback callback;
    private ArrayList<Address> addresses;

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SelectAddressDialogLayoutBinding.inflate(inflater, container, false);
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
        SelectAddressAdapter addressAdapter = new SelectAddressAdapter();
        binding.recyclerViewAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewAddress.setAdapter(addressAdapter);
        addressAdapter.setProducts(addresses);

        addressAdapter.setCallback(new SelectAddressAdapter.Callback() {
            @Override
            public void onItemClick(Address address) {
                callback.save(address);
                getParentFragmentManager().popBackStack();
            }
        });
    }

    public interface Callback {
        void save(Address address);
    }
}
