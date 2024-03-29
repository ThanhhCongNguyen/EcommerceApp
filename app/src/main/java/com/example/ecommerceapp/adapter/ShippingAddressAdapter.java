package com.example.ecommerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.model.Product;

import java.util.ArrayList;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.ArmChairViewHolder> {
    private ArrayList<Address> addresses;
    public Callback callback;

    public void setProducts(ArrayList<Address> products) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArmChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shipping_address, parent, false);
        return new ArmChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmChairViewHolder holder, int position) {
//        Product product = products.get(position);
//
//        holder.productName.setText(product.getProductName());
//        holder.productPrice.setText("$ ".concat(product.getPrice()));
//
//        Glide.with(holder.productImage.getContext())
//                .load(product.getImage())
//                .centerCrop()
//                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
//        return products != null ? products.size() : 0;
        return 3;
    }

    public class ArmChairViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, address;

        public ArmChairViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            address = itemView.findViewById(R.id.address);
        }
    }

    public interface Callback {
        void onItemClick(Address address);
    }
}
