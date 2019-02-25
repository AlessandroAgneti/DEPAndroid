package com.example.depeat.datamodels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.depeat.services.Converters;

import java.util.ArrayList;

@Entity(tableName = "order")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int orderId;

    @Embedded //L'oggetto ristorante viene incluso nella tabella ordine
    private Restaurant restaurant;

    @ColumnInfo(name = "products")
    @TypeConverters(Converters.class)
    private ArrayList<Food> foods;

    @ColumnInfo(name = "total")
    private float total;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
