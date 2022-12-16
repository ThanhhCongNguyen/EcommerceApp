package com.example.ecommerceapp.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private ArrayList<Category> category;
    private ArrayList<Product> product;

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
