package com.example.depeat.datamodels;

public class Food {
    private String nomeFood;
    private float prezzoFood;
    private int quantity = 0;
    private String descrizioneFood;

    public Food(String nomeFood, float prezzoFood){
        this.nomeFood = nomeFood;
        this.prezzoFood = prezzoFood;
    }


    public Food(String nomeFood, float prezzoFood, int quantity){

        this.nomeFood = nomeFood;
        this.prezzoFood = prezzoFood;
        this.quantity = quantity;
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

    public String getDescrizioneFood() {
        return descrizioneFood;
    }

    public void setDescrizioneFood(String descrizioneFood) {
        this.descrizioneFood = descrizioneFood;
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
