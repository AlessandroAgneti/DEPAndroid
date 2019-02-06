package com.example.depeat.datamodels;

public class Food {
    private int imageFoodId;
    private String nomeFood;
    private String prezzoFood;
    private String descrizioneFood;

    public Food(int imageFoodId, String nomeFood, String prezzoFood, String descrizioneFood){
        this.imageFoodId = imageFoodId;
        this.descrizioneFood = descrizioneFood;
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

    public String getPrezzoFood() {
        return prezzoFood;
    }

    public void setPrezzoFood(String prezzoFood) {
        this.prezzoFood = prezzoFood;
    }

    public String getDescrizioneFood() {
        return descrizioneFood;
    }

    public void setDescrizioneFood(String descrizioneFood) {
        this.descrizioneFood = descrizioneFood;
    }
}
