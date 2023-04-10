package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyCartAdapter;
import com.example.ecommerceapp.databinding.FragmentLoginBinding;
import com.example.ecommerceapp.databinding.FragmentMyCartBinding;

public class MyCartFragment extends Fragment implements View.OnClickListener {
    private FragmentMyCartBinding binding;
    private MyCartAdapter myCartAdapter;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            backToHomeFragment();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        binding.backBtn.setOnClickListener(this);

    }

    private void initData() {
        myCartAdapter = new MyCartAdapter();
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvCart.setAdapter(myCartAdapter);
    }

    private void backToHomeFragment() {
        getParentFragmentManager().popBackStack();
    }

}