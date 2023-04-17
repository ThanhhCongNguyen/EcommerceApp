package com.example.ecommerceapp.repository.database.remote_database;

import static com.example.ecommerceapp.utils.Utilities.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ecommerceapp.model.Category;
import com.example.ecommerceapp.model.MyCart;
import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Review;
import com.example.ecommerceapp.model.Shop;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.service.FurnitureService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RetrieveDatabase implements FurnitureService {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> chairs = new ArrayList<>();
    private ArrayList<Product> beds = new ArrayList<>();
    private ArrayList<Product> armChairs = new ArrayList<>();
    private ArrayList<Product> tables = new ArrayList<>();

    private MutableLiveData<ArrayList<Category>> categoryListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> chairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> armChairListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> bedListMutableLiveData;
    private MutableLiveData<ArrayList<Product>> tableListMutableLiveData;
    private MutableLiveData<User> userMutableLiveData;
    private MutableLiveData<User> userMutableLiveDataLogin;
    private MutableLiveData<ArrayList<MyCart>> myCartMutableLiveData;
    private MutableLiveData<ArrayList<User>> usersLiveData;

    private Callback callback;

    public RetrieveDatabase(FirebaseFirestore firebaseFirestore, Callback callback) {
        this.firebaseFirestore = firebaseFirestore;
        this.mAuth = FirebaseAuth.getInstance();
        this.callback = callback;
        categoryListMutableLiveData = new MutableLiveData<>();
        chairListMutableLiveData = new MutableLiveData<>();
        armChairListMutableLiveData = new MutableLiveData<>();
        tableListMutableLiveData = new MutableLiveData<>();
        bedListMutableLiveData = new MutableLiveData<>();
        userMutableLiveData = new MutableLiveData<>();
        myCartMutableLiveData = new MutableLiveData<>();
        usersLiveData = new MutableLiveData<>();
        userMutableLiveDataLogin = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<ArrayList<Product>> getAllChair() {
        firebaseFirestore.collection("OfficePlants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Product product = document.toObject(Product.class);

                                firebaseFirestore.collection("OfficePlants")
                                        .document(document.getId())
                                        .collection("reviews")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    ArrayList<Review> reviews = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        Review review = document.toObject(Review.class);
                                                        reviews.add(review);
                                                    }
                                                    product.setReviews(reviews);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                chairs.add(product);
                            }
                            chairListMutableLiveData.postValue(chairs);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return chairListMutableLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<Product>> getAllTable() {
        firebaseFirestore.collection("DesktopPlants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Product product = document.toObject(Product.class);

                                firebaseFirestore.collection("DesktopPlants")
                                        .document(document.getId())
                                        .collection("reviews")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    ArrayList<Review> reviews = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        Review review = document.toObject(Review.class);
                                                        reviews.add(review);
                                                    }
                                                    product.setReviews(reviews);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                tables.add(product);
                            }
                            tableListMutableLiveData.postValue(tables);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return tableListMutableLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<Product>> getAllArmChair() {
        firebaseFirestore.collection("WaterPlants")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Product product = document.toObject(Product.class);

                                firebaseFirestore.collection("WaterPlants")
                                        .document(document.getId())
                                        .collection("reviews")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    ArrayList<Review> reviews = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        Review review = document.toObject(Review.class);
                                                        reviews.add(review);
                                                    }
                                                    product.setReviews(reviews);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                armChairs.add(product);
                            }
                            armChairListMutableLiveData.postValue(armChairs);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return armChairListMutableLiveData;
    }

    @Override
    public MutableLiveData<ArrayList<Product>> getAllBed() {
        firebaseFirestore.collection("HangingGardens")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Product product = document.toObject(Product.class);

                                firebaseFirestore.collection("HangingGardens")
                                        .document(document.getId())
                                        .collection("reviews")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    ArrayList<Review> reviews = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                                        Review review = document.toObject(Review.class);
                                                        reviews.add(review);
                                                    }
                                                    product.setReviews(reviews);
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                                beds.add(product);
                            }
                            bedListMutableLiveData.postValue(beds);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return bedListMutableLiveData;
    }

    public MutableLiveData<User> getUser(String userId) {
        firebaseFirestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = documentSnapshot.toObject(User.class);
                    userMutableLiveData.postValue(user);
                    callback.getUserCallback(user);
                });
        return userMutableLiveData;
    }

    public void getUserAfterLogin(String userId) {
        firebaseFirestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = documentSnapshot.toObject(User.class);
                    userMutableLiveDataLogin.postValue(user);
                });
    }


    public void createNewUser(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = task.getResult().getUser().getUid();
                        addUserToDatabase(userId, user);
                    } else {
                        Log.d(TAG, "createUserWithEmail:failure", task.getException());
                    }
                });

    }

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            getUserAfterLogin(user.getUid());
                        } else {

                        }
                    }
                });

    }

    private void addUserToDatabase(String userId, User user) {
        user.setUserId(userId);
        firebaseFirestore.collection("users").document(user.getUserId())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        userMutableLiveDataLogin.postValue(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error writing document", e);
                    }
                });

    }

    public void addMyCart(String userId, ArrayList<MyCart> currentCart, MyCart myCart, String cartId) {
        firebaseFirestore.collection("users")
                .document(userId)
                .collection("MyCarts")
                .document(cartId)
                .set(myCart)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        currentCart.add(myCart);
                        myCartMutableLiveData.postValue(currentCart);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Error writing document", e);
                    }
                });
    }

    public MutableLiveData<ArrayList<MyCart>> getMyCart(String userId) {
        firebaseFirestore.collection("users").document(userId)
                .collection("MyCarts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<MyCart> myCarts = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                MyCart object = document.toObject(MyCart.class);
                                myCarts.add(object);
                            }
                            myCartMutableLiveData.postValue(myCarts);
                        }
                    }
                });
        return myCartMutableLiveData;
    }

    public MutableLiveData<ArrayList<User>> getUserByEmailAndPass(String email, String password) {
        firebaseFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<User> users = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User object = document.toObject(User.class);
                                users.add(object);
                            }
                            usersLiveData.postValue(users);
                        }
                    }
                });
        return usersLiveData;
    }

    public MutableLiveData<User> getUserFromShare() {
        return userMutableLiveData;
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveDataLogin;
    }

    public interface Callback {
        void getUserCallback(User user);


    }
}
