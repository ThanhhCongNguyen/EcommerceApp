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
import com.example.ecommerceapp.adapter.MyFavoritesAdapter;
import com.example.ecommerceapp.databinding.FragmentFavoritesBinding;
import com.example.ecommerceapp.databinding.FragmentSettingBinding;

public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    private MyFavoritesAdapter myFavoritesAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
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
        myFavoritesAdapter = new MyFavoritesAdapter();
        binding.rcvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rcvFavorites.setAdapter(myFavoritesAdapter);
    }
}