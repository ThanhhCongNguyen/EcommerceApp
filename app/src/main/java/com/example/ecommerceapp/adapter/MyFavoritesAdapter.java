package com.example.ecommerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.Favorites;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.utils.Utilities;

import java.util.ArrayList;

public class MyFavoritesAdapter extends RecyclerView.Adapter<MyFavoritesAdapter.ArmChairViewHolder> {
    private ArrayList<Favorites> favorites;
    public Callback callback;

    public void setProducts(ArrayList<Favorites> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArmChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        return new ArmChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmChairViewHolder holder, int position) {
        Favorites favorite = favorites.get(position);

        holder.itemName.setText(favorite.getProduct().getProductName());
        holder.itemPrice.setText(Utilities.convertCurrency(favorite.getProduct().getPrice()).concat(" VND"));

        Glide.with(holder.imageView.getContext())
                .load(favorite.getProduct().getImage())
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    public class ArmChairViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice;
        ImageView imageView, deleteItem;
        LinearLayout orderButton;

        public ArmChairViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            imageView = itemView.findViewById(R.id.imageView);
            orderButton = itemView.findViewById(R.id.orderButton);

            orderButton.setOnClickListener(view -> {
                callback.onOrderProduct(favorites.get(getAdapterPosition()));
            });

        }
    }

    public void removeItem(int position) {
        favorites.remove(position);
        notifyItemRemoved(position);
    }

    public ArrayList<Favorites> getData() {
        return favorites;
    }

    public interface Callback {
        void onOrderProduct(Favorites favorites);

        void onDeleteItem(Favorites favorites);
    }
}
