package com.example.ecommerceapp.service;

import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.Category;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public interface FurnitureService {

    MutableLiveData<ArrayList<Product>> getAllChair();

    MutableLiveData<ArrayList<Product>> getAllTable();

    MutableLiveData<ArrayList<Product>> getAllArmChair();

    MutableLiveData<ArrayList<Product>> getAllBed();

}
