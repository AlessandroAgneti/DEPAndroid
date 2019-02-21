package com.example.depeat.datamodels;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class Food {
    private String nomeFood;
    private float prezzoFood;
    private int quantity = 0;
    private String id;

    public Food(String nomeFood, float prezzoFood){
        this.nomeFood = nomeFood;
        this.prezzoFood = prezzoFood;
    }


    public Food(String nomeFood, float prezzoFood, int quantity){

        this.nomeFood = nomeFood;
        this.prezzoFood = prezzoFood;
        this.quantity = quantity;
    }

    public Food(JSONObject jsonFood) throws JSONException{
        nomeFood = jsonFood.getString("name");
        prezzoFood = (float) jsonFood.getDouble("price");
        id = jsonFood.getString("id");
    }

    public String getNomeFood() {
        return nomeFood;
    }

    public void setNomeFood(String nomeFood) {
        this.nomeFood = nomeFood;
    }

    public float getPrezzoFood() {
        return prezzoFood;
    }

    public void setPrezzoFood(float prezzoFood) {
        this.prezzoFood = prezzoFood;
    }

    public int getQuantity(){
        return quantity;
    }

    public void increaseQuantitaty(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        if(quantity == 0)
            return;
        this.quantity--;
    }

    public float getSubtotal(){
        return quantity*prezzoFood;
    }
}
