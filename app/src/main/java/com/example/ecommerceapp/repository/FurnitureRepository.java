package com.example.ecommerceapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.database.local_database.LocalDatabase;
import com.example.ecommerceapp.repository.database.remote_database.RetrieveDatabase;
import com.example.ecommerceapp.model.Category;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FurnitureRepository {
    private FirebaseFirestore firebaseFirestore;
    private RetrieveDatabase retrieveDatabase;
    private LocalDatabase localDatabase;
    private FurnitureRepositoryCallback callback;

    public FurnitureRepository(FirebaseFirestore firebaseFirestore, FurnitureRepositoryCallback callback) {
        this.firebaseFirestore = firebaseFirestore;
        this.callback = callback;
        localDatabase = new LocalDatabase();

        retrieveDatabase = new RetrieveDatabase(firebaseFirestore, new RetrieveDatabase.Callback() {

            @Override
            public void getUserCallback(User user) {
                callback.getUserFurnitureRepositoryCallback(user);
            }

        });
    }

    public MutableLiveData<ArrayList<Product>> getAllChair() {
        return retrieveDatabase.getAllChair();
    }

    public MutableLiveData<ArrayList<Product>> getAllTable() {
        return retrieveDatabase.getAllTable();
    }

    public MutableLiveData<ArrayList<Product>> getAllArmChair() {
        return retrieveDatabase.getAllArmChair();
    }

    public MutableLiveData<ArrayList<Product>> getAllBed() {
        return retrieveDatabase.getAllBed();
    }

    public MutableLiveData<User> getUser(String userId) {
        return retrieveDatabase.getUser(userId);
    }

    public MutableLiveData<ArrayList<MyCart>> getMyCart(String userId) {
        return retrieveDatabase.getMyCart(userId);
    }

    public void addProductToCart(String userId, ArrayList<MyCart> currentCart, MyCart myCart, String cartId) {
        retrieveDatabase.addMyCart(userId, currentCart, myCart, cartId);
    }

    public void saveUserToSharePreferences(Application application) {
        localDatabase.saveUserToSharePreferences(application);
    }

    public void clearUserToSharePreferences(Application application) {
        localDatabase.saveUserToSharePreferences(application);
    }

    public void saveUserIdToSharePreferences(Application application, String userId) {
        localDatabase.saveUserIdToSharePreferences(application, userId);
    }

    public void clearUserIdToSharePreferences(Application application) {
        localDatabase.clearUserIdToSharePreferences(application);
    }

    public boolean isLogin(Application application) {
        return localDatabase.isLogin(application);
    }

    public String getUserId(Application application) {
        return localDatabase.getUserId(application);
    }


    public interface FurnitureRepositoryCallback {

        void getUserFurnitureRepositoryCallback(User user);

    }
}
