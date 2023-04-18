package com.example.ecommerceapp.model;

public class Favorites {
    private String favoriteId;
    private Product product;

    public Favorites() {
    }

    public Favorites(String favoriteId, Product product) {
        this.favoriteId = favoriteId;
        this.product = product;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
