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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.ShippingAddressAdapter;
import com.example.ecommerceapp.databinding.FragmentAddShippingAddressBinding;
import com.example.ecommerceapp.databinding.FragmentShippingAddressBinding;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

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

        String[] country = { "Hà Nội", "Tp.Hồ Chí Minh", "Đà Nẵng", "Nam Định", "Hải Dương"};
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCity.setAdapter(aa);

        binding.spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                address.setPhoneNumber(country[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void backToSettingFragment() {
        getParentFragmentManager().popBackStack();
    }

}