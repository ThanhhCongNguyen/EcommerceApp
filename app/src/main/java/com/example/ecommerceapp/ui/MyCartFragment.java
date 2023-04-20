package com.example.ecommerceapp.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.ecommerceapp.utils.SwipeToDeleteCallback;
import com.example.ecommerceapp.utils.Utilities;
import com.example.ecommerceapp.viewmodel.HomeViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCartFragment extends Fragment implements View.OnClickListener {
    private FragmentMyCartBinding binding;
    private MyCartAdapter myCartAdapter;
    private HomeViewModel homeViewModel;
    private MyCartCallback callback;

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
        enableSwipeToDelete();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeViewModel.setTotalPriceToCheckout(0);
        homeViewModel.setObserveMyCartUpdate(false);
        homeViewModel.setObserveMyCartDelete(false);
    }

    public void setMyCartFragmentCallback(MyCartCallback myCartFragmentCallback) {
        this.callback = myCartFragmentCallback;
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
        } else {
            myCartAdapter = new MyCartAdapter();
            homeViewModel.getMyCartMutableLiveDataFromServer().observe(getViewLifecycleOwner(), myCarts -> {
                if (myCarts != null) {
                    if (myCarts.size() > 0) {
                        HashMap<String, MyCart> hashMap = new HashMap<>();
                        for (int i = 0; i < myCarts.size(); i++) {
                            hashMap.put(myCarts.get(i).getCartId(), myCarts.get(i));
                        }
                        homeViewModel.setMyCartHashMap(hashMap);
                        ArrayList<MyCart> list = new ArrayList<>(homeViewModel.getMyCartHashMap().values());
                        myCartIsNotEmpty(list);
                    } else {
                        myCartIsEmpty();
                    }
                }
            });

            homeViewModel.getCartAfterAdd().observe(getViewLifecycleOwner(), myCart -> {
                if (myCart != null) {
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

                    homeViewModel.addMyCartHashMap(myCart);
                    myCartAdapter.setProducts(new ArrayList<>(homeViewModel.getMyCartHashMap().values()));
                }
            });

            homeViewModel.getLiveDataAfterDeleted().observe(getViewLifecycleOwner(), myCart -> {
                if (myCart != null) {
                    if (homeViewModel.getMyCartHashMap().size() > 0) {
                        setTotalPriceToCheckout(new ArrayList<>(homeViewModel.getMyCartHashMap().values()));
                    } else {
                        myCartIsEmpty();
                    }
                }
            });
        }

        binding.checkOutBtn.setOnClickListener(view -> {
            callback.openCheckOutFragment();
        });
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

        binding.rcvCart.setLayoutManager(new LinearLayoutManager(getContext()));
        myCartAdapter.setProducts(myCarts);
        binding.rcvCart.setAdapter(myCartAdapter);

        setTotalPriceToCheckout(new ArrayList<>(homeViewModel.getMyCartHashMap().values()));

        myCartAdapter.setCallback(new MyCartAdapter.Callback() {
            @Override
            public void onItemClick(Product product) {

            }

            @Override
            public void onCartChanged(MyCart myCart, int position) {
                homeViewModel.setObserveMyCartUpdate(true);
                updateMyCartHashMap(myCart);
            }
        });
    }

    private void enableSwipeToDelete() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final MyCart item = myCartAdapter.getData().get(position);
                myCartAdapter.removeItem(position);
                homeViewModel.setObserveMyCartDelete(true);
                homeViewModel.removeMyCart(homeViewModel.getUserId(), item);
            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(binding.rcvCart);
    }

    private void updateMyCartHashMap(MyCart myCart) {
        homeViewModel.replaceMyCartHashMap(myCart);
        setTotalPriceToCheckout(new ArrayList<>(homeViewModel.getMyCartHashMap().values()));
    }

    private void setTotalPriceToCheckout(ArrayList<MyCart> myCarts) {
        int totalPrice = homeViewModel.getTotalPriceToCheckout();
        for (int i = 0; i < myCarts.size(); i++) {
            totalPrice += myCarts.get(i).getTotalPrice();
        }
        homeViewModel.setTotalPriceToCheckout(totalPrice);
        homeViewModel.setTotalPriceToCheckoutV2(totalPrice);
        binding.totalPriceText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getTotalPriceToCheckout())).concat(" VND"));
        homeViewModel.setTotalPriceToCheckout(0);
    }

    private void setTotalPriceToCheckoutAfterUpdate(MyCart myCart) {
        int totalPrice = homeViewModel.getTotalPriceToCheckout();

        homeViewModel.setTotalPriceToCheckout(totalPrice);
        binding.totalPriceText.setText(Utilities.convertCurrency(String.valueOf(homeViewModel.getTotalPriceToCheckout())).concat(" VND"));
    }

    private void backToHomeFragment() {
        getParentFragmentManager().popBackStack();
    }

    public interface MyCartCallback {
        void openCheckOutFragment();
    }

}