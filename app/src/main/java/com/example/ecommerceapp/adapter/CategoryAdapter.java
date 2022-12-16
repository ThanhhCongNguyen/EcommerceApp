package com.example.ecommerceapp.adapter;

import static com.example.ecommerceapp.utils.Utilities.TAG;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.Category;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<Category> categoryList;
    private Callback callback;
    private int oldPosition = 0;


    public CategoryAdapter() {
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onItemClick(int position);
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getCategoryName());

        Glide.with(holder.categoryImage.getContext())
                .load(category.getCategoryImage())
                .centerCrop()
                .placeholder(R.drawable.ic_chair)
                .into(holder.categoryImage);
        if (oldPosition == 0) {
            holder.linearLayout.setBackground(holder.linearLayout.getContext().getDrawable(R.drawable.bg_category_selected));
        }
        holder.itemView.setOnClickListener(view -> {

            notifyItemChanged(oldPosition);
            oldPosition = holder.getAdapterPosition();
            holder.linearLayout.setBackground(holder.linearLayout.getContext().getDrawable(R.drawable.bg_category_selected));

            callback.onItemClick(holder.getAdapterPosition());
        });

        if (oldPosition != position) {
            holder.linearLayout.setBackground(holder.linearLayout.getContext().getDrawable(R.drawable.bg_category));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        public final TextView categoryName;
        public final ImageView categoryImage;
        public final LinearLayout linearLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.categoryName);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            linearLayout = itemView.findViewById(R.id.llCategory);
        }
    }

}
