package com.example.depeat.datamodels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Restaurant {
    @ColumnInfo(name = "image_url")
    private String imageId;

    @ColumnInfo(name = "name")
    private String nome;

    @ColumnInfo(name = "address")
    private String indirizzo;

    @ColumnInfo(name = "minimum_order")
    private float minimoOrdine;

    private String desc_complete;

    @Ignore
    private ArrayList<Food> foods;

    @ColumnInfo(name = "restaurant_id")
    private String id;

    public static final String ENDPOINT = "restaurants/";

    public Restaurant(String nome, String indirizzo, float minimoOrdine){
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.minimoOrdine = minimoOrdine;
        foods = new ArrayList<>();
    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        //Facciamo il costruttore per utilizzare il JSON e per farne il parsing
        nome = jsonRestaurant.getString("name");   //getString perché il valore associato alla chiave nome nel JSON è una Stringa
        indirizzo = jsonRestaurant.getString("address");
        minimoOrdine = Float.valueOf(jsonRestaurant.getString("min_order"));
        imageId = jsonRestaurant.getString("image_url");
        id = jsonRestaurant.getString("id");

    }

    public void setDesc_complete(String desc_complete) {
        this.desc_complete = desc_complete;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageId() {
        return imageId;
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

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
