package com.example.ecommerceapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.FurnitureRepository;
import com.example.ecommerceapp.repository.database.remote_database.RetrieveDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Product>> chairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> tableListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> bedListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> armChairListMutableLiveData;
    private MutableLiveData<ArrayList<MyCart>> myCartMutableLiveData;
    private MutableLiveData<User> userMutableLiveData;

    private MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> productCount = new MutableLiveData<>();

    private FirebaseFirestore firebaseFirestore;
    private FurnitureRepository furnitureRepository;

    private Application application;

    private HomeViewModelCallback callback;
    private MyCartCallback myCartCallback;

    private Product product;
    private int finalPrice;
    private int defaultCount = 1;
    private int totalPrice;
    private int finalQuantity;

    private int quantityDefault = 1;

    private String cartId;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        firebaseFirestore = FirebaseFirestore.getInstance();
        furnitureRepository = new FurnitureRepository(firebaseFirestore, new FurnitureRepository.FurnitureRepositoryCallback() {
            @Override
            public void getUserFurnitureRepositoryCallback(User user) {
                if (callback != null) {
                    callback.getUserHomeViewModelCallback(user);
                }
            }
        });
        chairListMutableLiveData = furnitureRepository.getAllChair();
        tableListMutableLiveData = furnitureRepository.getAllTable();
        bedListMutableLiveData = furnitureRepository.getAllBed();
        armChairListMutableLiveData = furnitureRepository.getAllArmChair();
        myCartMutableLiveData = furnitureRepository.getMyCart(furnitureRepository.getUserId(application));
        userMutableLiveData = furnitureRepository.getUser(furnitureRepository.getUserId(application));
    }

    public void setCallback(HomeViewModelCallback callback) {
        this.callback = callback;
    }

    public void setMyCartCallback(MyCartCallback callback) {
        this.myCartCallback = callback;
    }

    public MutableLiveData<Product> getProductMutableLiveData() {
        return productMutableLiveData;
    }

    public MutableLiveData<ArrayList<MyCart>> getMyCartMutableLiveData() {
        return myCartMutableLiveData;
    }

    public int getQuantityDefault() {
        return quantityDefault;
    }

    public void setQuantityDefault(int quantityDefault) {
        this.quantityDefault = quantityDefault;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setProductMutableLiveData(Product product) {
        productMutableLiveData.setValue(product);
    }

    public void addProductToCart(String userId, MyCart myCart, String cartId) {
        furnitureRepository.addProductToCart(userId, Objects.requireNonNull(myCartMutableLiveData.getValue()), myCart, cartId);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

    public void saveUserIdToSharePreferences(String userId) {
        furnitureRepository.saveUserIdToSharePreferences(application, userId);
    }

    public void clearUserIdToSharePreferences() {
        furnitureRepository.clearUserIdToSharePreferences(application);
    }

    public boolean isLogin() {
        return furnitureRepository.isLogin(application);
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void setUserMutableLiveData(MutableLiveData<User> userMutableLiveData) {
        this.userMutableLiveData = userMutableLiveData;
    }

    public int getFinalQuantity() {
        return finalQuantity;
    }

    public void setFinalQuantity(int finalQuantity) {
        this.finalQuantity = finalQuantity;
    }

    public String getUserId() {
        return furnitureRepository.getUserId(application);
    }

    public interface HomeViewModelCallback {
        void getUserHomeViewModelCallback(User user);
    }

    public interface MyCartCallback {
        void addUserSuccess();

        void getMyCart(ArrayList<MyCart> myCarts);
    }
}
