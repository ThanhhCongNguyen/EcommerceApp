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
import com.example.ecommerceapp.adapter.NotificationsAdapter;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding binding;
    private NotificationsAdapter notificationsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        notificationsAdapter = new NotificationsAdapter();
        binding.rcvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvNotification.setAdapter(notificationsAdapter);

    }
}