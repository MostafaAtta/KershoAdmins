package com.example.android.loginretrofittest;

import java.io.Serializable;

public class Dish implements Serializable {

    private int dId, price, kitchen, likes, quantity, cartCount;
    private String imageUrl, dishName, dishDisc, location;
    boolean isFav;


    public Dish(int dId, String dishName, String dishDisc, int kitchen, int price, String imageUrl, String location, int likes) {
        this.dId = dId;
        this.dishName = dishName;
        this.dishDisc = dishDisc;
        this.kitchen = kitchen;
        this.price = price;
        this.imageUrl = imageUrl;
        this.location = location;
        this.likes = likes;
    }

    public Dish(int id, int quantity) {
        this.dId = id;
        this.quantity = quantity;
    }

    public Dish(int dId, int price, int kitchen, int likes, int cartCount, String imageUrl, String dishName, String dishDisc, String location, boolean isFav) {
        this.dId = dId;
        this.price = price;
        this.kitchen = kitchen;
        this.likes = likes;
        this.cartCount = cartCount;
        this.imageUrl = imageUrl;
        this.dishName = dishName;
        this.dishDisc = dishDisc;
        this.location = location;
        this.isFav = isFav;
    }

    public int getDishId() {
        return dId;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDishName() {
        return dishName;
    }

    public int getKitchen() {
        return kitchen;
    }

    public String getDishDisc() {
        return dishDisc;
    }

    public String getLocation() {
        return location;
    }

    public int getLikes() {
        return likes;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }
}