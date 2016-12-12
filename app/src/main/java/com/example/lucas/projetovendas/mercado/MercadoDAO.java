package com.example.lucas.projetovendas.mercado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.projetovendas.sqlite.BancoDadosMercados;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 08/12/16.
 */

public class MercadoDAO {

    SQLiteDatabase db;

    public MercadoDAO(Context context){ db = BancoDadosMercados.getDB(context);}


    public void salvar(Mercado mercado) {
        ContentValues values = new ContentValues();
        values.put(Mercado.NOMEMERCADO, mercado.getNome_mercado());
        values.put(Mercado.TELEFONE,mercado.getTelefone());
        values.put(Mercado.LATITUDE,mercado.getLatitude());
        values.put(Mercado.LONGITUDE,mercado.getLongitude());
        //values.put(Mercado.FOTO, mercado.getFoto());
        db.insert(Mercado.TABELA, null, values);
    }

    public void alterar(Mercado mercado) {
        ContentValues values = new ContentValues();
        values.put(Mercado.NOMEMERCADO, mercado.getNome_mercado());
        values.put(Mercado.TELEFONE,mercado.getTelefone());
        values.put(Mercado.LATITUDE,mercado.getLatitude());
        values.put(Mercado.LONGITUDE,mercado.getLongitude());

        String id = String.valueOf(mercado.getId());
        String[] whereArgs = new String[]{id};

        db.update(Mercado.TABELA, values, Mercado.ID +" = ?", whereArgs);
    }

    public Mercado buscar(String id) {


        String[] colunas = Mercado.COLUNAS;
        String[] whereArgs = new String[]{id};

        Cursor c = db.query(Mercado.TABELA, colunas,Mercado.ID + " = ?", whereArgs, null, null, null);

        c.moveToFirst();

        Mercado mercado = new Mercado();
        mercado.setId(c.getLong(c.getColumnIndex(Mercado.ID)));
        mercado.setNome_mercado(c.getString(c.getColumnIndex(Mercado.NOMEMERCADO)));
        mercado.setTelefone(c.getString(c.getColumnIndex(Mercado.TELEFONE)));
        mercado.setLatitude(c.getString(c.getColumnIndex(Mercado.LATITUDE)));
        mercado.setLongitude(c.getString(c.getColumnIndex(Mercado.LONGITUDE)));

        return mercado;
    }

    public List<Mercado> listar() {

        String[] colunas = Mercado.COLUNAS;
        Cursor c = db.query(Mercado.TABELA, colunas, null, null, null, null, null);

        List<Mercado> mercadoList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Mercado mercado = new Mercado();
                mercado.setId(c.getLong(c.getColumnIndex(Mercado.ID)));
                mercado.setNome_mercado(c.getString(c.getColumnIndex(Mercado.NOMEMERCADO)));
                mercado.setTelefone(c.getString(c.getColumnIndex(Mercado.TELEFONE)));
                mercado.setLatitude(c.getString(c.getColumnIndex(Mercado.LATITUDE)));
                mercado.setLongitude(c.getString(c.getColumnIndex(Mercado.LONGITUDE)));


                mercadoList.add(mercado);

                Log.i("lista", mercado.getNome_mercado());
            } while (c.moveToNext());
        }
        return mercadoList;
    }


    public void excluir(String id) {
        String[] whereArgs = new String[]{id};
        db.delete(Mercado.TABELA,Mercado.ID + " = ?", whereArgs);
    }



}
