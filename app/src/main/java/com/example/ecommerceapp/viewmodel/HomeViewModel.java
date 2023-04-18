package com.example.ecommerceapp.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerceapp.model.Favorites;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.FurnitureRepository;
import com.example.ecommerceapp.repository.database.remote_database.RetrieveDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Product>> chairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> tableListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> bedListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> armChairListMutableLiveData;
    private MutableLiveData<ArrayList<MyCart>> myCartMutableLiveData;
    private MutableLiveData<ArrayList<User>> userLiveData;
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
    private int positionUpdate;
    private ArrayList<MyCart> myCarts;
    private int totalPriceToCheckout = 0;
    private ArrayList<Integer> idOfFavorites;
    private HashMap<String, Favorites> myFavoritesList;
    private ArrayList<MyCart> myCartObserve;
    private boolean isObserve;
    private boolean isObserveFavorite;

    // New implementation
    private HashMap<String, MyCart> myCartHashMap;
    private boolean isObserveMyCartAdded;
    private boolean isObserveMyCartUpdate;
    private boolean isObserveMyCartDelete;

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

    public MutableLiveData<ArrayList<MyCart>> getMyCartMutableLiveDataFromServer() {
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
        furnitureRepository.addProductToCart(userId, myCart, cartId);
    }

    public LiveData<MyCart> getCartAfterAdd() {
        return furnitureRepository.getCartAfterAdd();
    }

    public void createNewUser(User user) {
        furnitureRepository.createNewUser(user);
    }

    public void signInWithEmailAndPassword(String email, String password) {
        furnitureRepository.signInWithEmailAndPassword(email, password);
    }

    public LiveData<User> getUserLiveData() {
        return furnitureRepository.getUserMutableLiveData();
    }

    public void deleteFavorite(String userId, String favoriteId) {
        furnitureRepository.deleteFavorite(userId, favoriteId);
    }

    public LiveData<String> isDeletedFavorite() {
        return furnitureRepository.isDeletedFavorite();
    }

    public LiveData<User> getUserFromShare() {
        return furnitureRepository.getUserFromShare();
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

    public ArrayList<MyCart> getMyCartObserve() {
        return myCartObserve;
    }

    public void setMyCartObserve(ArrayList<MyCart> myCartObserve) {
        this.myCartObserve = myCartObserve;
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

    public HashMap<String, Favorites> getMyFavoritesList() {
        return myFavoritesList;
    }

    public void setMyFavoritesList(ArrayList<Favorites> myFavoritesList) {
        HashMap<String, Favorites> hashMap = new HashMap<>();
        for (int i = 0; i < myFavoritesList.size(); i++) {
            hashMap.put(myFavoritesList.get(i).getProduct().getProductId(), myFavoritesList.get(i));
        }
        this.myFavoritesList = hashMap;
    }

    public void clearUserToSharePreferences() {
        furnitureRepository.saveUserToSharePreferences(application);
    }

    public int getPositionUpdate() {
        return positionUpdate;
    }

    public void setPositionUpdate(int positionUpdate) {
        this.positionUpdate = positionUpdate;
    }

    public void saveUserIdToSharePreferences(String userId) {
        furnitureRepository.saveUserIdToSharePreferences(application, userId);
    }

    public void clearUserIdToSharePreferences() {
        furnitureRepository.clearUserIdToSharePreferences(application);
    }

    public int getTotalPriceToCheckout() {
        return totalPriceToCheckout;
    }

    public void setTotalPriceToCheckout(int totalPriceToCheckout) {
        this.totalPriceToCheckout = totalPriceToCheckout;
    }

    public boolean isLogin() {
        return furnitureRepository.isLogin(application);
    }

    public void clearUserCache() {
        furnitureRepository.clearUserCache(application);
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<ArrayList<User>> getAllUser(String email, String password) {
        return furnitureRepository.getAllUser(email, password);
    }

    public void setUserMutableLiveData(MutableLiveData<User> userMutableLiveData) {
        this.userMutableLiveData = userMutableLiveData;
    }

    public void updateCart(String userId, MyCart myCart, String cartId) {
        furnitureRepository.updateCart(userId, myCart, cartId);
    }

    public boolean isObserve() {
        return isObserve;
    }

    public void setObserve(boolean observe) {
        isObserve = observe;
    }

    public boolean isObserveFavorite() {
        return isObserveFavorite;
    }

    public void setObserveFavorite(boolean observeFavorite) {
        isObserveFavorite = observeFavorite;
    }

    public ArrayList<Integer> getIdOfFavorites() {
        return idOfFavorites;
    }

    public void setIdOfFavorites(ArrayList<Integer> idOfFavorites) {
        this.idOfFavorites = idOfFavorites;
    }

    public LiveData<MyCart> getCartUpdate() {
        return furnitureRepository.getMyCartUpdate();
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

    public ArrayList<MyCart> getMyCarts() {
        return myCarts;
    }

    public void setMyCarts(ArrayList<MyCart> myCarts) {
        this.myCarts = myCarts;
    }

    public void updateCart(int index, MyCart myCart) {
        myCartObserve.set(index, myCart);
    }

    public void addProductToFavorites(String userId, Favorites favorite) {
        furnitureRepository.addProductToFavorites(userId, favorite);
    }

    public LiveData<Favorites> getFavoritesLiveData() {
        return furnitureRepository.getFavoritesLiveData();
    }

    public LiveData<ArrayList<Favorites>> getFavoritesLiveDataFromServer(String userId) {
        return furnitureRepository.getFavoritesLiveDataFromServer(userId);
    }

    // New implementation

    public void setMyCartHashMap(HashMap<String, MyCart> myCartHashMap) {
        this.myCartHashMap = myCartHashMap;
    }

    public void addMyCartHashMap(MyCart myCart) {
        myCartHashMap.put(myCart.getCartId(), myCart);
    }

    public void removeMyCart(String userId, MyCart myCart) {
        furnitureRepository.removeMyCart(userId, myCart);
    }

    public LiveData<MyCart> getLiveDataAfterDeleted() {
        return furnitureRepository.getLiveDataAfterDeleted();
    }

    public void replaceMyCartHashMap(MyCart myCart) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            myCartHashMap.replace(myCart.getCartId(), myCart);
        }
    }

    public void removeItemInMyCartHashMap(MyCart myCart) {
        myCartHashMap.remove(myCart.getCartId());
    }

    public HashMap<String, MyCart> getMyCartHashMap() {
        return myCartHashMap;
    }

    public boolean isObserveMyCartUpdate() {
        return isObserveMyCartUpdate;
    }

    public void setObserveMyCartUpdate(boolean observeMyCartUpdate) {
        isObserveMyCartUpdate = observeMyCartUpdate;
    }

    public boolean isObserveMyCartAdded() {
        return isObserveMyCartAdded;
    }

    public void setObserveMyCartAdded(boolean observeMyCartAdded) {
        isObserveMyCartAdded = observeMyCartAdded;
    }

    public boolean isObserveMyCartDelete() {
        return isObserveMyCartDelete;
    }

    public void setObserveMyCartDelete(boolean observeMyCartDelete) {
        isObserveMyCartDelete = observeMyCartDelete;
    }

    public interface HomeViewModelCallback {
        void getUserHomeViewModelCallback(User user);
    }

    public interface MyCartCallback {
        void addUserSuccess();

        void getMyCart(ArrayList<MyCart> myCarts);
    }
}
