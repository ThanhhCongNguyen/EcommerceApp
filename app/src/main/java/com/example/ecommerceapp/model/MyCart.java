package com.example.ecommerceapp.model;

public class MyCart {
    private String userId;
    private String cartId;
    private int totalPrice;
    private int quantity;
    private Product product;

    public MyCart() {
    }

    public MyCart(String userId, String cartId, int totalPrice, int quantity, Product product) {
        this.userId = userId;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.product = product;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
