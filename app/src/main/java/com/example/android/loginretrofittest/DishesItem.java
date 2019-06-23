package com.example.android.loginretrofittest;

import java.util.ArrayList;

public class DishesItem {

   private ArrayList<Dish> dishes;

    public DishesItem(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }
}
