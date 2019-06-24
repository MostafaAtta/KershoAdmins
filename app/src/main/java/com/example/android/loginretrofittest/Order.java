package com.example.android.loginretrofittest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Order implements Serializable {


    private Map<String, String> dishes, count ;


    @SerializedName("dishesList")
    private ArrayList<Dish> dishesList;


    private int orderId, subtotal, delivery, total, discount, userId, addressId, status;
    private String location, schedule, orderTime, creationTime, phone, kitchen;


    @SerializedName("fullAddress")
    private Address fullAddress;


    public Order(int orderId, ArrayList<Dish> dishesList, int status, int subtotal, int delivery, int total, int discount, int userId, String location,
                 int addressId, String phone, String schedule, String orderTime, String creationTime, Address fullAddress, String kitchen) {
        this.dishesList = dishesList;
        this.orderId = orderId;
        this.status = status;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.total = total;
        this.discount = discount;
        this.userId = userId;
        this.location = location;
        this.addressId = addressId;
        this.schedule = schedule;
        this.phone = phone;
        this.orderTime = orderTime;
        this.creationTime = creationTime;
        this.fullAddress = fullAddress;
        this.kitchen = kitchen;
    }

    public Order(Map<String, String> dishes, Map<String, String> count, int subtotal, int delivery, int total, int discount, int userId, String location,
                 int addressId, String phone, String schedule, String orderTime, String creationTime) {
        this.dishes = dishes;
        this.count = count;
        this.subtotal = subtotal;
        this.delivery = delivery;
        this.total = total;
        this.discount = discount;
        this.userId = userId;
        this.location = location;
        this.addressId = addressId;
        this.schedule = schedule;
        this.phone = phone;
        this.orderTime = orderTime;
        this.creationTime = creationTime;
    }

    public String getSchedule() {


        return schedule;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public Map<String, String> getDishes() {

        return dishes;
    }

    public Map<String, String> getCount() {
        return count;
    }

    public int getSubtotalPrice() {
        return subtotal;
    }

    public int getDelivery() {
        return delivery;
    }

    public int getTotalPrice() {
        return total;
    }

    public int getDiscount() {
        return discount;
    }

    public String getLocation() {
        return location;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {

        return userId;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setCreationTime(String creationTime) {
        creationTime = creationTime;
    }

    public String getCreationTime() {

        return creationTime;
    }

    public String getMobile() {
        return phone;
    }

    public ArrayList<Dish> getDishesList() {
        return dishesList;
    }

    public Address getFullAddress() {
        return fullAddress;
    }


    public int getId() {
        return orderId;
    }

    public String getKitchen() {
        return kitchen;
    }

    public int getStatus() {
        return status;
    }
}


