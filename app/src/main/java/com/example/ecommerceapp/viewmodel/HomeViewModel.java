package com.example.ecommerceapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.repository.FurnitureRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Product>> chairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> tableListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> bedListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> armChairListMutableLiveData;

    private MutableLiveData<Product> productMutableLiveData;

    private FirebaseFirestore firebaseFirestore;
    private FurnitureRepository furnitureRepository;

    public HomeViewModel() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        furnitureRepository = new FurnitureRepository(firebaseFirestore);
        chairListMutableLiveData = furnitureRepository.getAllChair();
        tableListMutableLiveData = furnitureRepository.getAllTable();
        bedListMutableLiveData = furnitureRepository.getAllBed();
        armChairListMutableLiveData = furnitureRepository.getAllArmChair();
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return productMutableLiveData;
    }

    public void setProductMutableLiveData(MutableLiveData<Product> productMutableLiveData) {
        this.productMutableLiveData = productMutableLiveData;
    }

    public LiveData<ArrayList<Product>> getAllChair() {
        return chairListMutableLiveData;
    }

    public LiveData<ArrayList<Product>> getAllTable() {
        return tableListMutableLiveData;
    }

    public LiveData<ArrayList<Product>> getAllArmChair() {
        return armChairListMutableLiveData;
    }

    public LiveData<ArrayList<Product>> getAllBed() {
        return bedListMutableLiveData;
    }


}
