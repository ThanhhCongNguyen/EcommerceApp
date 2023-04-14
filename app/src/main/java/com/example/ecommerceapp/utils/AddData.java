package com.example.ecommerceapp.utils;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.model.Review;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class AddData {
    private FirebaseFirestore db;

    public AddData() {
        db = FirebaseFirestore.getInstance();
    }

    public void addProductToFirestore() {
        for (int i = 130; i < 140; i++) {
            String categoryName = "HangingGardens";
            String description = "Chậu treo Hoa dừa cạn còn có tên tiếng Việt là hoa hải đăng, trường xuân hoa, bông dừa, dương giác, nhật nhật thảo, hoa đồng hồ… Hoa dừa cạn có cánh đơn, mỏng. Có nhiều mầu sắc như trắng, tím, hồng, đỏ.";
            String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/cay-hoa-dua-can-chau-treo-6.jpg?alt=media&token=a7cbf634-0dda-450c-9c2f-2d57421af686";
            String price = "229000";
            String productId = String.valueOf(i);
            String productName = "Cây Chậu Treo Dừa Cạn nhiều màu sắc";
            Product product = new Product(productId, productName, price, image, description, categoryName);
            Review revieww = null;

            for (int j = 0; j < 5; j++) {
                String userId = String.valueOf(j + 810);
                String productIdd = productId;
                String time = String.valueOf(Calendar.getInstance().getTime());
                String review = "Very good";
                String rating = "5";
                revieww = new Review(userId, productIdd, time, review, rating);
                db.collection("HangingGardens").document(String.valueOf(i)).collection("reviews").document().set(revieww);
            }
            db.collection("HangingGardens").document(String.valueOf(i)).set(product);
        }
    }
}
