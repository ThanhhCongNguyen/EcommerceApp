package com.example.ecommerceapp.adapter;

import static com.example.ecommerceapp.utils.Utilities.convertCurrency;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ArmChairViewHolder> {
    private ArrayList<MyCart> myCarts;
    public Callback callback;
    public int currentQuantity;

    public void setProducts(ArrayList<MyCart> myCarts) {
        this.myCarts = myCarts;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArmChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ArmChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmChairViewHolder holder, int position) {
        MyCart myCart = myCarts.get(position);
        holder.itemName.setText(myCart.getProduct().getProductName());
        holder.itemPrice.setText(convertCurrency(String.valueOf(myCart.getTotalPrice())).concat(" VND"));
        holder.quantityText.setText(String.valueOf(myCart.getQuantity()));

        Glide.with(holder.imageView.getContext())
                .load(myCart.getProduct().getImage())
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return myCarts != null ? myCarts.size() : 0;
    }

    public class ArmChairViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, quantityText;
        ImageView imageView, plusButton, minusButton;

        public ArmChairViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            quantityText = itemView.findViewById(R.id.quantityText);
            imageView = itemView.findViewById(R.id.imageView);
            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusBtn);

            plusButton.setOnClickListener(view -> {
                int currentQuantity = myCarts.get(getAdapterPosition()).getQuantity();
                currentQuantity++;
                myCarts.get(getAdapterPosition()).setQuantity(currentQuantity);
                quantityText.setText(String.valueOf(currentQuantity));
                int originPrice = Integer.parseInt(myCarts.get(getAdapterPosition()).getProduct().getPrice());
                int price = originPrice * currentQuantity;
                myCarts.get(getAdapterPosition()).setTotalPrice(price);
                Log.d("thanh1", "price: " + price);
                itemPrice.setText(convertCurrency(String.valueOf(price)).concat(" VND"));
                callback.onCartChanged(myCarts.get(getAdapterPosition()), getAdapterPosition());
                if (!minusButton.isEnabled()) {
                    minusButton.setEnabled(true);
                }
            });

            minusButton.setOnClickListener(view -> {
                int currentQuantity = myCarts.get(getAdapterPosition()).getQuantity();
                if (currentQuantity < 1) {
                    minusButton.setEnabled(false);
                } else {
                    currentQuantity--;
                    myCarts.get(getAdapterPosition()).setQuantity(currentQuantity);
                    quantityText.setText(String.valueOf(currentQuantity));
                    int originPrice = Integer.parseInt(myCarts.get(getAdapterPosition()).getProduct().getPrice());
                    int price = originPrice * currentQuantity;
                    myCarts.get(getAdapterPosition()).setTotalPrice(price);
                    Log.d("thanh1", "price: " + price);
                    itemPrice.setText(convertCurrency(String.valueOf(price)).concat(" VND"));
                    callback.onCartChanged(myCarts.get(getAdapterPosition()), getAdapterPosition());

                }
            });
        }
    }


    public interface Callback {
        void onItemClick(Product product);

        void onCartChanged(MyCart myCart, int position);
    }
}
