package com.example.ecommerceapp.model;

import java.util.ArrayList;

public class MyOrders {
    private String orderId;
    private String quantity;
    private String totalAmount;
    private ArrayList<Product> products;
    private OrderState state;

    public MyOrders() {
    }

    public MyOrders(String orderId, String quantity, String totalAmount, ArrayList<Product> products, OrderState state) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.products = products;
        this.state = state;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
