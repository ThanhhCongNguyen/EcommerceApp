package com.example.ecommerceapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.Address;
import com.example.ecommerceapp.model.Favorites;
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

    public LiveData<MyCart> getMyCartUpdate() {
        return retrieveDatabase.getCartUpdate();
    }

    public void updateCart(String userId, MyCart myCart, String cartId) {
        retrieveDatabase.updateMyCart(userId, myCart, cartId);
    }

    public MutableLiveData<ArrayList<User>> getAllUser(String email, String password) {
        return retrieveDatabase.getUserByEmailAndPass(email, password);
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return retrieveDatabase.getUserMutableLiveData();
    }

    public MutableLiveData<User> getUserFromShare() {
        return retrieveDatabase.getUserFromShare();
    }

    public void addProductToCart(String userId, MyCart myCart, String cartId) {
        retrieveDatabase.addMyCart(userId, myCart, cartId);
    }

    public LiveData<MyCart> getCartAfterAdd() {
        return retrieveDatabase.getCartAfterAdd();
    }

    public void removeMyCart(String userId, MyCart myCart) {
        retrieveDatabase.removeMyCart(userId, myCart);
    }

    public LiveData<MyCart> getLiveDataAfterDeleted() {
        return retrieveDatabase.getLiveDataAfterDeleted();
    }


    public void addProductToFavorites(String userId, Favorites favorites) {
        retrieveDatabase.addProductToFavorites(userId, favorites);
    }

    public LiveData<ArrayList<Favorites>> getFavoritesLiveDataFromServer(String userId) {
        return retrieveDatabase.getFavoritesLiveDataFromServer(userId);
    }

    public LiveData<Favorites> getFavoritesLiveDataAfterDeleted() {
        return retrieveDatabase.getFavoritesLiveDataAfterDeleted();
    }

    public LiveData<Address> getMyAddressAfterCreate() {
        return retrieveDatabase.getMyAddressAfterCreate();
    }

    public void createNewAddress(String userId, Address address) {
        retrieveDatabase.createShippingAddress(userId, address);
    }

    public void removeMyFavorites(String userId, Favorites favorites) {
        retrieveDatabase.removeMyFavorites(userId, favorites);
    }

    public LiveData<Favorites> getFavoritesLiveData() {
        return retrieveDatabase.getFavoritesLiveData();
    }

    public void createNewUser(User user) {
        retrieveDatabase.createNewUser(user);
    }

    public void signInWithEmailAndPassword(String email, String password) {
        retrieveDatabase.signInWithEmailAndPassword(email, password);
    }

    public LiveData<String> getLiveDataLoginFail() {
        return retrieveDatabase.getLiveDataLoginFail();
    }

    public void deleteFavorite(String userId, String favoriteId) {
        retrieveDatabase.deleteFavorite(userId, favoriteId);
    }

    public LiveData<String> isDeletedFavorite() {
        return retrieveDatabase.isDeletedFavorite();
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

    public void clearUserCache(Application application) {
        localDatabase.clearUserToSharePreferences(application);
    }

    public String getUserId(Application application) {
        return localDatabase.getUserId(application);
    }


    public interface FurnitureRepositoryCallback {

        void getUserFurnitureRepositoryCallback(User user);

    }
}
