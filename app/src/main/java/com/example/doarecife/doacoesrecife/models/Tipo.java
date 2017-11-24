package com.example.doarecife.doacoesrecife.models;

import java.util.List;

/**
 * Created by jose mario on 24/11/2017.
 */

public class Tipo {
    String nome;
    List<ItemDoacao> itens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemDoacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoacao> itens) {
        this.itens = itens;
    }

}
