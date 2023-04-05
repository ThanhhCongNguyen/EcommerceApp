package com.example.ecommerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.Product;

import java.util.ArrayList;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.BedViewHolder> {
    private ArrayList<Product> products;
    public Callback callback;

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public BedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new BedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BedViewHolder holder, int position) {
        Product product = products.get(position);

        holder.productName.setText(product.getProductName());
        holder.productPrice.setText("$ ".concat(product.getPrice()));

        Glide.with(holder.productImage.getContext())
                .load(product.getImage())
                .centerCrop()
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public class BedViewHolder extends RecyclerView.ViewHolder {
        public final TextView productName, productPrice;
        public final ImageView productImage;

        public BedViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);

            itemView.setOnClickListener(view -> {
                callback.onItemClick(products.get(getAdapterPosition()));
            });
        }
    }

    public interface Callback {
        void onItemClick(Product product);
    }
}
