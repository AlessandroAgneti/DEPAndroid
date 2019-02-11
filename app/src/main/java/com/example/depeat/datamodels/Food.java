package com.example.depeat.datamodels;

public class Food {
    private int imageFoodId;
    private String nomeFood;
    private float prezzoFood;
    private int quantity = 0;
    private String descrizioneFood;

    public Food(int imageFoodId, String nomeFood, float prezzoFood){
        this.imageFoodId = imageFoodId;
        this.nomeFood = nomeFood;
        this.prezzoFood = prezzoFood;
    }


    public int getImageFoodId() {
        return imageFoodId;
    }

    public void setImageFoodId(int imageFoodId) {
        this.imageFoodId = imageFoodId;
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
