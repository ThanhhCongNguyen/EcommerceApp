package com.example.ecommerceapp.ui;

import android.content.Context;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.databinding.FragmentDetailBinding;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

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
    public void onClick(View view) {
        if (view.getId() == R.id.plusButton) {
            plusCount();
        } else if (view.getId() == R.id.minusButton) {
            minusCount();
        } else if (view.getId() == R.id.addToCart) {
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

        homeViewModel.getProductMutableLiveData().observe(requireActivity(), product -> {
            homeViewModel.setProduct(product);

            homeViewModel.setFinalQuantity(1);
            homeViewModel.setFinalPrice(Integer.parseInt(product.getPrice()));

            binding.productName.setText(product.getProductName());

            String s1 = product.getPrice().substring(0, 3);
            String s2 = product.getPrice().substring(3);
            binding.totalPriceText.setText(s1.concat(",").concat(s2).concat(" VND"));

            homeViewModel.setTotalPrice(Integer.parseInt(product.getPrice()));

            if (getActivity() != null) {
                Glide.with(getActivity())
                        .load(product.getImage())
                        .into(binding.expandedImage);
            }
            binding.descriptionText.setText(product.getDescription());

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

        String s1 = String.valueOf(totalPrice).substring(0, 3);
        String s2 = String.valueOf(totalPrice).substring(3);
        binding.totalPriceText.setText(s1.concat(",").concat(s2).concat(" VND"));

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

            String s1 = String.valueOf(totalPrice).substring(0, 3);
            String s2 = String.valueOf(totalPrice).substring(3);
            binding.totalPriceText.setText(s1.concat(",").concat(s2).concat(" VND"));

        } else {
            binding.minusButton.setEnabled(false);
        }
    }

    private void addProductToCart() {
        String userId = homeViewModel.getUserId();
        MyCart myCart = new MyCart();
        myCart.setProduct(homeViewModel.getProduct());
        myCart.setUserId(userId);
        myCart.setCartId(homeViewModel.getCartId());
        myCart.setTotalPrice(homeViewModel.getFinalPrice());
        myCart.setQuantity(homeViewModel.getFinalQuantity());

        homeViewModel.addProductToCart(homeViewModel.getUserId(), myCart, homeViewModel.getCartId());

        Toast toast = Toast.makeText(getContext(), "Added to your cart", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
        toast.show();
    }

}