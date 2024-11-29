package com.example.minhasreceitas;

/**
 * Created by DANIEL on 09/10/2015.
 */
public class ReceitaDetalhes {

    private int id;
    private String titulo;
    private String ingredientes;
    private String modoDepreparo;

    public ReceitaDetalhes() {
    }

    public ReceitaDetalhes(int id, String titulo, String ingredientes, String modoDepreparo) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.modoDepreparo = modoDepreparo;
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
}
