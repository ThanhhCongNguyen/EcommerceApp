package com.example.ecommerceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.model.Category;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;
import com.example.ecommerceapp.repository.FurnitureRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Category>> categoryListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> productListMutableLiveData;

    private FirebaseFirestore firebaseFirestore;
    private FurnitureRepository furnitureRepository;

    public HomeViewModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        furnitureRepository = new FurnitureRepository(firebaseFirestore);
        categoryListMutableLiveData = furnitureRepository.getCategory();
        productListMutableLiveData = furnitureRepository.getProduct();

    }

    public LiveData<ArrayList<Category>> getLiveCategoryData() {
        return categoryListMutableLiveData;
    }

    public LiveData<ArrayList<Product>> getLiveProductData() {
        return productListMutableLiveData;
    }



}
