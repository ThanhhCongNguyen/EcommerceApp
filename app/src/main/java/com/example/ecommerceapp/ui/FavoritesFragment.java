package com.example.ecommerceapp.ui;

import android.content.Context;
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
import com.example.ecommerceapp.adapter.MyFavoritesAdapter;
import com.example.ecommerceapp.databinding.FragmentFavoritesBinding;
import com.example.ecommerceapp.model.Favorites;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    private MyFavoritesAdapter myFavoritesAdapter;
    private HomeViewModel homeViewModel;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
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
        init();
    }

    private void init() {
        if (!homeViewModel.isLogin()) {
            binding.rcvFavorites.setVisibility(View.GONE);
            binding.addAllBtn.setVisibility(View.GONE);
            binding.icNotLogin.setVisibility(View.VISIBLE);
            binding.requestLoginText.setVisibility(View.VISIBLE);
            binding.requestLoginButton.setVisibility(View.VISIBLE);
        } else {
            binding.view1.setVisibility(View.GONE);
            binding.addAllBtn.setVisibility(View.VISIBLE);
            binding.rcvFavorites.setVisibility(View.VISIBLE);
            binding.icNotLogin.setVisibility(View.GONE);
            binding.requestLoginText.setVisibility(View.GONE);
            binding.requestLoginButton.setVisibility(View.GONE);

            myFavoritesAdapter = new MyFavoritesAdapter();
            binding.rcvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rcvFavorites.setAdapter(myFavoritesAdapter);

            homeViewModel.getFavoritesLiveDataFromServer(homeViewModel.getUserId()).observe(getViewLifecycleOwner(), favorites -> {
                if (favorites != null) {
                    myFavoritesAdapter.setProducts(favorites);
                    myFavoritesAdapter.setCallback(new MyFavoritesAdapter.Callback() {
                        @Override
                        public void onOrderProduct(Favorites favorites) {
                            Log.d("thanh1", "order");
                        }

                        @Override
                        public void onDeleteItem(Favorites favorites) {
                            String userId = homeViewModel.getUserId();
                            String favoriteId = favorites.getProduct().getProductId();
                            homeViewModel.deleteFavorite(userId, favoriteId);
                        }
                    });
                }
            });

            homeViewModel.isDeletedFavorite().observe(getViewLifecycleOwner(), favoriteId -> {
                if (favoriteId != null) {
                    Log.d("thanh1", "is deleted: " + favoriteId);
                }
            });

        }
    }

}