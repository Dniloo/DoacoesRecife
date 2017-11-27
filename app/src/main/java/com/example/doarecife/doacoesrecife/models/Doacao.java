package com.example.doarecife.doacoesrecife.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jose mario on 24/11/2017.
 */
public class Doacao {
    @SerializedName("doacao")
    List<Categoria> categorias;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
