package com.example.ecommerceapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.MyCartAdapter;
import com.example.ecommerceapp.adapter.NotificationsAdapter;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.databinding.FragmentNotificationBinding;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding binding;
    private NotificationsAdapter notificationsAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {

        if (!homeViewModel.isLogin()) {
            binding.rcvNotification.setVisibility(View.GONE);
            binding.icNotLogin.setVisibility(View.VISIBLE);
            binding.requestLoginText.setVisibility(View.VISIBLE);
            binding.requestLoginButton.setVisibility(View.VISIBLE);
        } else {
            binding.view1.setVisibility(View.GONE);
            binding.rcvNotification.setVisibility(View.VISIBLE);
            binding.icNotLogin.setVisibility(View.GONE);
            binding.requestLoginText.setVisibility(View.GONE);
            binding.requestLoginButton.setVisibility(View.GONE);

            notificationsAdapter = new NotificationsAdapter();
            binding.rcvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcvNotification.setAdapter(notificationsAdapter);
        }
    }
}