package com.example.doarecife.doacoesrecife.database;

import android.provider.BaseColumns;

/**
 * Created by jose mario on 30/11/2017.
 */

public interface DoacaoContract extends BaseColumns {

    String TABLE_NAME = "doacoesBD";

    String LOCAL = "local";
    String TIPO = "tipo";
    String QUANTIDADE = "quantidade";
    String FOTO = "foto";

}
