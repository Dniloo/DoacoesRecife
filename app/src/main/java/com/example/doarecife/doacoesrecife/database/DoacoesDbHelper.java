package com.example.doarecife.doacoesrecife.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jose mario on 30/11/2017.
 */

public class DoacoesDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME= "doacoes_db";

    public DoacoesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DoacaoContract.TABLE_NAME +"(" +
                DoacaoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DoacaoContract.LOCAL + " TEXT NOT NULL, " +
                DoacaoContract.TIPO + " TEXT NOT NULL, " +
                DoacaoContract.QUANTIDADE + " INTEGER NOT NULL, " +
                DoacaoContract.FOTO + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
