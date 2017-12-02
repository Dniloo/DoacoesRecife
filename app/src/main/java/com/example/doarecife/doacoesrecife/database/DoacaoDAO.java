package com.example.doarecife.doacoesrecife.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose mario on 30/11/2017.
 */

public class DoacaoDAO {

    private Context mcontext;

    public DoacaoDAO(Context context) {
        this.mcontext = context;
    }

    public long inserir(Itemdoacao itemdoacao) {

        DoacoesDbHelper dbHelper = new DoacoesDbHelper(mcontext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromDoacao(itemdoacao);


        long id = database.insert(DoacaoContract.TABLE_NAME, null, contentValues);

        itemdoacao.setId(id);
        database.close();

        return id;

    }

    public int Atualizar(Itemdoacao itemdoacao) {

        DoacoesDbHelper dbHelper = new DoacoesDbHelper(mcontext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = valuesFromDoacao(itemdoacao);

        int rowsAffected = database.update(DoacaoContract.TABLE_NAME, contentValues,
                DoacaoContract._ID + " = ?",
                new String[] {String.valueOf(itemdoacao.getId())});

        database.close();

        return rowsAffected;
    }

    public int delete(Itemdoacao itemdoacao){
        DoacoesDbHelper dbHelper = new DoacoesDbHelper(mcontext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        int rowsAffected = database.delete(DoacaoContract.TABLE_NAME,
                    DoacaoContract._ID + " = ?",
                new String[] {String.valueOf(itemdoacao.getId())});

        database.close();
        return rowsAffected;

    }

    public List<Itemdoacao> listar(){
        DoacoesDbHelper dbHelper = new DoacoesDbHelper(mcontext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ DoacaoContract.TABLE_NAME,null);

            List<Itemdoacao> itemdoacaos = new ArrayList<>();
                    while (cursor.moveToNext()){
                        Itemdoacao itemdoacao = new Itemdoacao();
                        itemdoacao.setId(cursor.getLong(cursor.getColumnIndex(DoacaoContract._ID)));
                        itemdoacao.setLocal(cursor.getString(cursor.getColumnIndex(DoacaoContract.LOCAL)));
                        itemdoacao.setTipo(cursor.getString(cursor.getColumnIndex(DoacaoContract.TIPO)));
                        itemdoacao.setQuantidade(cursor.getInt(cursor.getColumnIndex(DoacaoContract.QUANTIDADE)));
                        itemdoacao.setFoto(cursor.getString(cursor.getColumnIndex(DoacaoContract.FOTO)));

                        itemdoacaos.add(itemdoacao);
                    }
                    cursor.close();
                    database.close();
                    return itemdoacaos;
    }

    public boolean isFavorito (Itemdoacao itemdoacao) {
        DoacoesDbHelper dbHelper = new DoacoesDbHelper(mcontext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT count(*) FROM "+ DoacaoContract.TABLE_NAME +
                " WHERE " + DoacaoContract.LOCAL + " =?",
                new String[]{itemdoacao.getLocal()});
        boolean existe = false;
        if (cursor != null) {
            cursor.moveToNext();
            existe = cursor.getInt(0) > 0;
            cursor.close();
        }
        database.close();
        return existe;
    }

    private ContentValues valuesFromDoacao(Itemdoacao itemdoacao){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DoacaoContract.LOCAL, itemdoacao.getLocal());
        contentValues.put(DoacaoContract.TIPO, itemdoacao.getTipo());
        contentValues.put(DoacaoContract.QUANTIDADE, itemdoacao.getQuantidade());
        contentValues.put(DoacaoContract.FOTO, itemdoacao.getFoto());
        return  contentValues;
    }
}