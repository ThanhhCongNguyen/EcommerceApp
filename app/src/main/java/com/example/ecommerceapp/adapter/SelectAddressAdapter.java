package com.example.ecommerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.Address;

import java.util.ArrayList;

public class SelectAddressAdapter extends RecyclerView.Adapter<SelectAddressAdapter.ArmChairViewHolder> {
    private ArrayList<Address> addresses;
    public Callback callback;

    public void setProducts(ArrayList<Address> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ArmChairViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_address, parent, false);
        return new ArmChairViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArmChairViewHolder holder, int position) {
        Address address = addresses.get(position);

        holder.userName.setText(address.getUserName());

        holder.address.setText(address.getStreet() + ", " + address.getCity() + ", " + address.getCountry());

    }

    @Override
    public int getItemCount() {
        return addresses != null ? addresses.size() : 0;
    }

    public class ArmChairViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, address;


        public ArmChairViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            address = itemView.findViewById(R.id.address);

            itemView.setOnClickListener(view -> {
                callback.onItemClick(addresses.get(getAdapterPosition()));
            });
        }
    }

    public interface Callback {
        void onItemClick(Address address);
    }
}
