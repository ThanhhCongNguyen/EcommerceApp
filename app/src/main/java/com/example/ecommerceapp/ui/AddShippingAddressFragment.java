package com.example.ecommerceapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentAddShippingAddressBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.UUID;

public class AddShippingAddressFragment extends Fragment implements View.OnClickListener {
    private FragmentAddShippingAddressBinding binding;
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
        binding = FragmentAddShippingAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        binding.backBtn.setOnClickListener(this::onClick);
        String city = "";
        Address address = new Address();

        String[] country = {"Hà Nội", "Tp.Hồ Chí Minh", "Đà Nẵng", "Nam Định", "Hải Dương"};
        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCity.setAdapter(aa);
        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                address.setCity(country[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.signUpButton.setOnClickListener(view -> {
            saveAddress(address);
        });


    }

    private void saveAddress(Address address) {
        String userName = binding.nameEdittext.getText().toString().trim();
        String street = binding.streetEdittext.getText().toString().trim();
        String phone = binding.cityEdittext.getText().toString().trim();

        address.setAddressId(UUID.randomUUID().toString());
        address.setUserId(homeViewModel.getUserId());
        address.setUserName(userName);
        address.setPhoneNumber(phone);
        address.setStreet(street);
        address.setCountry("Vietnam");

        homeViewModel.createShippingAddress(homeViewModel.getUserId(), address);
    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }

}