package com.example.ecommerceapp.model;

public class Review {
    private String userId;
    private String productId;
    private String time;
    private String review;
    private String rating;

    public Review() {
    }

    public Review(String userId, String productId, String time, String review, String rating) {
        this.userId = userId;
        this.productId = productId;
        this.time = time;
        this.review = review;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
