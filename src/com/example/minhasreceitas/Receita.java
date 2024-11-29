package com.example.minhasreceitas;

public class Receita {
    private int id;
    private String titulo;
    private String ingredientes;
    private String modoDepreparo;
    byte[] _image;

    public Receita() {
    }

    public Receita(int id, String titulo, String ingredientes, String modoDepreparo, byte[] image) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.modoDepreparo = modoDepreparo;
        this._image = image;
    }

    public Receita(byte[] image) {
        this._image = image;

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getIngredientes() {

        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {

        this.ingredientes = ingredientes;
    }

    public String getModoDepreparo() {

        return modoDepreparo;
    }

    public void setModoDepreparo(String modoDepreparo) {
        this.modoDepreparo = modoDepreparo;
    }

    @Override
    public String toString() {

        return titulo;


    }

    // getting phone number
    public byte[] getImage() {
        return this._image;
    }

    // setting phone number
    public void setImage(byte[] image) {
        this._image = image;
    }


}
