package com.example.ecommerceapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;
import com.example.ecommerceapp.repository.database.remote_database.RetrieveDatabase;
import com.example.ecommerceapp.model.Category;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FurnitureRepository {
    private FirebaseFirestore firebaseFirestore;
    private RetrieveDatabase retrieveDatabase;

    public FurnitureRepository(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        retrieveDatabase = new RetrieveDatabase(firebaseFirestore);
    }

//    private void initRetrieveDatabase(FirebaseFirestore firebaseFirestore) {
//        retrieveDatabase = new RetrieveDatabase(firebaseFirestore);
//    }

    public MutableLiveData<ArrayList<Category>> getCategory() {
        return retrieveDatabase.getCategory();
    }

    public MutableLiveData<ArrayList<Product>> getProduct() {
        return retrieveDatabase.getProduct();
    }

}
