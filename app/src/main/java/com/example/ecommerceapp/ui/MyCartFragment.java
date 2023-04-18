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
import com.example.ecommerceapp.adapter.MyFavoritesAdapter;
import com.example.ecommerceapp.databinding.FragmentLoginBinding;
import com.example.ecommerceapp.databinding.FragmentMyCartBinding;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.utils.Utilities;
import com.example.ecommerceapp.viewmodel.HomeViewModel;

import java.util.ArrayList;

public class MyCartFragment extends Fragment implements View.OnClickListener {
    private FragmentMyCartBinding binding;
    private MyCartAdapter myCartAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn) {
            backToHomeFragment();
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
        binding = FragmentMyCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onPause() {
        super.onPause();
        homeViewModel.setTotalPriceToCheckout(0);
    }

    private void init() {
        binding.backBtn.setOnClickListener(this);

        if (!homeViewModel.isLogin()) {
            binding.rcvCart.setVisibility(View.GONE);
            binding.tv1.setVisibility(View.GONE);
            binding.checkOutBtn.setVisibility(View.GONE);
            binding.totalPriceText.setVisibility(View.GONE);
            binding.icNotLogin.setVisibility(View.VISIBLE);
            binding.requestLoginText.setVisibility(View.VISIBLE);
            binding.requestLoginButton.setVisibility(View.VISIBLE);
            binding.cartEmptyImage.setVisibility(View.GONE);
            binding.cartEmptyText.setVisibility(View.GONE);
            binding.purchaseNow.setVisibility(View.GONE);
        } else {
            Log.d("thanh1", "MyCartFragment: " + homeViewModel.getMyCartObserve().size());
            if (homeViewModel.getMyCartObserve().size() > 0) {
                myCartIsNotEmpty(homeViewModel.getMyCartObserve());
            } else {
                myCartIsEmpty();
            }
//            homeViewModel.getMyCartMutableLiveData().observe(getViewLifecycleOwner(), myCarts -> {
//                if (myCarts != null && myCarts.size() > 0) {
//                    homeViewModel.setMyCarts(myCarts);
//                    myCartIsNotEmpty(myCarts);
//                } else {
//                    myCartIsEmpty();
//                }
//            });
        }
    }

    private void myCartIsEmpty() {
        binding.rcvCart.setVisibility(View.GONE);
        binding.tv1.setVisibility(View.GONE);
        binding.checkOutBtn.setVisibility(View.GONE);
        binding.totalPriceText.setVisibility(View.GONE);
        binding.icNotLogin.setVisibility(View.GONE);
        binding.requestLoginText.setVisibility(View.GONE);
        binding.requestLoginButton.setVisibility(View.GONE);
        binding.cartEmptyImage.setVisibility(View.VISIBLE);
        binding.cartEmptyText.setVisibility(View.VISIBLE);
        binding.purchaseNow.setVisibility(View.VISIBLE);
    }

    private void myCartIsNotEmpty(ArrayList<MyCart> myCarts) {
        binding.view1.setVisibility(View.GONE);
        binding.tv1.setVisibility(View.VISIBLE);
        binding.checkOutBtn.setVisibility(View.VISIBLE);
        binding.totalPriceText.setVisibility(View.VISIBLE);
        binding.rcvCart.setVisibility(View.VISIBLE);
        binding.icNotLogin.setVisibility(View.GONE);
        binding.requestLoginText.setVisibility(View.GONE);
        binding.requestLoginButton.setVisibility(View.GONE);
        binding.cartEmptyImage.setVisibility(View.GONE);
        binding.cartEmptyText.setVisibility(View.GONE);
        binding.purchaseNow.setVisibility(View.GONE);

        myCartAdapter = new MyCartAdapter();
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        myCartAdapter.setProducts(myCarts);
        binding.rcvCart.setAdapter(myCartAdapter);

        setTotalPriceToCheckout(homeViewModel.getMyCartObserve());

        homeViewModel.getCartUpdate().observe(getViewLifecycleOwner(), myCart -> {
            if (myCart != null) {
                Log.d("thanh1", "update: " + homeViewModel.getPositionUpdate());
                setTotalPriceToCheckout(homeViewModel.getMyCartObserve());
            }
        });

        myCartAdapter.setCallback(new MyCartAdapter.Callback() {
            @Override
            public void onItemClick(Product product) {

            }

            @Override
            public void onCartChanged(MyCart myCart, int position) {
                homeViewModel.updateCart(position, myCart);
                homeViewModel.setPositionUpdate(position);
                homeViewModel.updateCart(homeViewModel.getUserId(), myCart, myCart.getCartId());
            }
        });
    }

    private void setTotalPriceToCheckout(ArrayList<MyCart> myCarts) {
        int totalPrice = homeViewModel.getTotalPriceToCheckout();
        for (int i = 0; i < myCarts.size(); i++) {
            totalPrice += myCarts.get(i).getTotalPrice();
        }
        homeViewModel.setTotalPriceToCheckout(totalPrice);
        binding.totalPriceText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getTotalPriceToCheckout())).concat(" VND"));
    }

    private void backToHomeFragment() {
        getParentFragmentManager().popBackStack();
    }

}