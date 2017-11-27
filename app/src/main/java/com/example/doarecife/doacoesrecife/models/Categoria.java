package com.example.doarecife.doacoesrecife.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jose mario on 24/11/2017.
 */

public class Categoria {

    String nome;
    @SerializedName("itemdoacao")
    List<Itemdoacao> itens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Itemdoacao> getItens() {
        return itens;
    }

    public void setItens(List<Itemdoacao> itens) {
        this.itens = itens;
    }

}
