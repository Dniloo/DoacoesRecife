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

    public ItemDoacao(String local, String tipo) {
        this.local = local;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return local + " - " + tipo;
    }
}