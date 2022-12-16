package com.example.ecommerceapp.repository.database.remote_database;

import static com.example.ecommerceapp.utils.Utilities.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.Category;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Shop;
import com.example.ecommerceapp.service.FurnitureService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RetrieveDatabase implements FurnitureService {
    private FirebaseFirestore firebaseFirestore;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    private MutableLiveData<ArrayList<Category>> categoryListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> productListMutableLiveData;

    public RetrieveDatabase(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
        categoryListMutableLiveData = new MutableLiveData<>();
        productListMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<ArrayList<Category>> getCategory() {
        Log.i("TAG", "getCategoryListMutableLiveData: ");
        firebaseFirestore.collection("myShop").document("Category").collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Category category = document.toObject(Category.class);
                                categories.add(category);
                                categories.add(category);
                            }
                            categoryListMutableLiveData.postValue(categories);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return categoryListMutableLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<Category>> getAllChair() {
        return categoryListMutableLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<Product>> getProduct() {
        Log.i("TAG", "getProductListMutableLiveData: ");
        firebaseFirestore.collection("myShop").document("Product").collection("Chair")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Product product = document.toObject(Product.class);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                                products.add(product);
                            }
                            productListMutableLiveData.postValue(products);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return productListMutableLiveData;
    }


    public List<Category> getCategoryList() {
        return categories;
    }
}
