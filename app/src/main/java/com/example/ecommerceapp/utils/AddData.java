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
        for (int i = 100; i < 110; i++) {
            String categoryName = "armChair";
            String description = "This is CS50x , Harvard University's introduction to the intellectual enterprises of computer science and the art of programming for majors and non-majors alike, with or without prior programming experience. An entry-level course taught by David J. Malan, CS50x teaches students how to think algorithmically and solve problems efficiently. Topics include abstraction, algorithms, data structures, encapsulation, resource management, security, software engineering, and web development. Languages include C, Python, SQL, and JavaScript plus CSS and HTML. Problem sets inspired by real-world domains of biology, cryptography, finance, forensics, and gaming. The on-campus version of CS50x , CS50, is Harvard's largest course. ";
            String height = "120";
            String image = "https://firebasestorage.googleapis.com/v0/b/ecommerceapp-30308.appspot.com/o/1-1589382726.jpg?alt=media&token=f988d3d1-ef01-45ca-b2e3-fb8989738061";
            String price = "124";
            String productId = String.valueOf(i);
            String productName = "Sunny ".concat(String.valueOf(i));
            String width = "100";
            Product product = new Product(productId, productName, price, image, width, height, description, categoryName);
            Review revieww = null;

            for (int j = 0; j < 5; j++) {
                String userId = String.valueOf(j + 810);
                String productIdd = productId;
                String time = String.valueOf(Calendar.getInstance().getTime());
                String review = "Very good";
                String rating = "5";
                revieww = new Review(userId, productIdd, time, review, rating);
                db.collection("product").document(String.valueOf(i)).collection("reviews").document().set(revieww);
            }

            db.collection("product").document(String.valueOf(i)).set(product);
        }
    }
}
