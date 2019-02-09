package com.example.depeat.datamodels;

import java.util.ArrayList;

public class Restaurant {
    private int imageId;
    private String nome;
    private String indirizzo;
    private float minimoOrdine;
    private String desc_complete;
    private ArrayList<Food> foods;

    public Restaurant(int imageId, String nome, String indirizzo, float minimoOrdine){
        this.imageId = imageId;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.minimoOrdine = minimoOrdine;
        foods = new ArrayList<>();
    }


    public void setDesc_complete(String desc_complete) {
        this.desc_complete = desc_complete;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setMinimoOrdine(float minimoOrdine) {
        this.minimoOrdine = minimoOrdine;
    }

    public String getDesc_complete() {
        return desc_complete;
    }

    public int getImageId() {
        return imageId;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public float getMinimoOrdine() {
        return minimoOrdine;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
