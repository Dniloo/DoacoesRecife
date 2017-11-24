package com.example.doarecife.doacoesrecife.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jose mario on 22/11/2017.
 */
@org.parceler.Parcel
public class ItemDoacao {

    String local;
    String tipo;
    int quantidade;
    String foto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public ItemDoacao() {
    }

    public ItemDoacao(String local, String tipo) {
        this.local = local;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return local + " - " + tipo;
    }
}