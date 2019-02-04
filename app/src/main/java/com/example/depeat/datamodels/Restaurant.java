package com.example.depeat.datamodels;

public class Restaurant {
    private int imageId;
    private String nome;
    private String indirizzo;
    private float minimoOrdine;

    public Restaurant(int imageId, String nome, String indirizzo, float minimoOrdine){
        this.imageId = imageId;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.minimoOrdine = minimoOrdine;
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
}
