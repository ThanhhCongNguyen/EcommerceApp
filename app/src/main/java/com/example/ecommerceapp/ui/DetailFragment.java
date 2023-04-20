package com.example.ecommerceapp.ui;

import static com.example.ecommerceapp.utils.Utilities.convertCurrency;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.model.Favorites;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class DetailFragment extends Fragment implements View.OnClickListener {
    private FragmentDetailBinding binding;
    private HomeViewModel homeViewModel;

    public DetailFragment() {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
        homeViewModel.setObserve(false);
        homeViewModel.setObserveFavorite(false);
        homeViewModel.setQuantityDefault(1);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.plusButton) {
            plusCount();
        } else if (view.getId() == R.id.minusButton) {
            minusCount();
        } else if (view.getId() == R.id.saveProduct) {
            addProductToCart();
        }
    }

    private void initView() {
        binding.plusButton.setOnClickListener(this);
        binding.minusButton.setOnClickListener(this);
        binding.addToCart.setOnClickListener(this);
        binding.saveProduct.setOnClickListener(this);
    }

    private void initData() {
        homeViewModel.setCartId(UUID.randomUUID().toString());
        binding.countText.setText(String.valueOf(homeViewModel.getDefaultCount()));

        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back);
        binding.appBarLayout.addOnOffsetChangedListener((AppBarLayout.BaseOnOffsetChangedListener) (appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) - appBarLayout.getTotalScrollRange() == 0) {
                binding.toolbar.setTitle(R.string.detail);
                binding.CollapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
                binding.toolbar.setBackgroundColor(Color.WHITE);
            } else {
                binding.toolbar.setTitle(null);
                binding.CollapsingToolbarLayout.setExpandedTitleGravity(View.GONE);
                binding.CollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
                binding.toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
            }
        });

        homeViewModel.getProductMutableLiveData().observe(requireActivity(), product -> {
            homeViewModel.setProduct(product);
            homeViewModel.setFinalQuantity(1);
            homeViewModel.setFinalPrice(Integer.parseInt(product.getPrice()));
            binding.productName.setText(product.getProductName());
            homeViewModel.setTotalPrice(Integer.parseInt(product.getPrice()));

            binding.totalPriceText.setText(convertCurrency(product.getPrice()).concat(" VND"));

            if (getActivity() != null) {
                Glide.with(getActivity())
                        .load(product.getImage())
                        .into(binding.expandedImage);
            }
            binding.descriptionText.setText(product.getDescription());

        });


        homeViewModel.getCartAfterAdd().observe(getViewLifecycleOwner(), myCart -> {
            if (myCart != null) {
                if (homeViewModel.isObserve()) {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), R.string.added_to_your_cart, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void plusCount() {
        int count = homeViewModel.getQuantityDefault();
        count++;
        homeViewModel.setQuantityDefault(count);
        if (!binding.minusButton.isEnabled()) {
            binding.minusButton.setEnabled(true);
        }
        binding.countText.setText(String.valueOf(count));

        int totalPrice = homeViewModel.getTotalPrice() * count;
        homeViewModel.setFinalPrice(totalPrice);

        homeViewModel.setFinalQuantity(count);

        binding.totalPriceText.setText(convertCurrency(String.valueOf(totalPrice)).concat(" VND"));
    }

    private void minusCount() {
        int count = homeViewModel.getQuantityDefault();
        if (count > 1) {
            count--;
            homeViewModel.setQuantityDefault(count);
            binding.countText.setText(String.valueOf(count));
            binding.minusButton.setEnabled(true);

            int totalPrice = homeViewModel.getTotalPrice() * count;
            homeViewModel.setFinalPrice(totalPrice);

            homeViewModel.setFinalQuantity(count);

            binding.totalPriceText.setText(convertCurrency(String.valueOf(totalPrice)).concat(" VND"));

        } else {
            binding.minusButton.setEnabled(false);
        }
    }

    private void addProductToCart() {
        homeViewModel.setObserve(true);
        binding.progressBar.setVisibility(View.VISIBLE);
        String userId = homeViewModel.getUserId();
        MyCart myCart = new MyCart();
        myCart.setProduct(homeViewModel.getProduct());
        myCart.setUserId(userId);
        myCart.setCartId(homeViewModel.getCartId());
        myCart.setTotalPrice(homeViewModel.getFinalPrice());
        myCart.setQuantity(homeViewModel.getFinalQuantity());

        homeViewModel.addProductToCart(homeViewModel.getUserId(), myCart, homeViewModel.getCartId());
    }

}