package com.example.ecommerceapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.repository.FurnitureRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Product>> chairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> tableListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> bedListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> armChairListMutableLiveData;

    private MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> productCount = new MutableLiveData<>();

    private FirebaseFirestore firebaseFirestore;
    private FurnitureRepository furnitureRepository;

    private Application application;

    private int defaultCount = 1;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
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

    public void setProductMutableLiveData(Product product) {
        productMutableLiveData.setValue(product);
    }

    public void setProductCountMutableLiveData(int count) {
        count++;
        productCount.setValue(count);
    }

    public void setMinusProductCountMutableLiveData(int count) {
        count--;
        productCount.setValue(count);
    }

    public MutableLiveData<Integer> getProductCount() {
        return productCount;
    }

    public int getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
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

    public void saveUserToSharePreferences() {
        furnitureRepository.saveUserToSharePreferences(application);
    }

    public void clearUserToSharePreferences() {
        furnitureRepository.saveUserToSharePreferences(application);
    }

    public boolean isLogin() {
        return furnitureRepository.isLogin(application);
    }


}
