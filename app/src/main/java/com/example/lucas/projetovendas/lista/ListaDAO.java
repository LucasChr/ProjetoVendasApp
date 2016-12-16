package com.example.lucas.projetovendas.lista;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.projetovendas.sqlite.BancoDadosLista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13/12/16.
 */

public class ListaDAO {

    SQLiteDatabase db;

    public ListaDAO(Context context) {
        db = BancoDadosLista.getDB(context);
    }


    public void salvar(Lista lista) {
        ContentValues values = new ContentValues();
        values.put(Lista.NOME, lista.getNome());
        values.put(Lista.MERCADO, lista.getMercado());
        values.put(Lista.DATA, lista.getData());
        values.put(Lista.TOTALLISTA, lista.getTotal());
        db.insert(Lista.TABELA, null, values);
    }

    public void alterar(Lista lista) {
        ContentValues values = new ContentValues();
        values.put(Lista.NOME, lista.getNome());
        values.put(Lista.MERCADO, lista.getMercado());
        values.put(Lista.DATA, lista.getData());
        values.put(Lista.TOTALLISTA, lista.getTotal());
        db.insert(Lista.TABELA, null, values);

        String id = String.valueOf(lista.getId());
        String[] whereArgs = new String[]{id};

        db.update(Lista.TABELA, values, Lista.ID + " = ?", whereArgs);
    }

    public Lista buscar(String id) {


        String[] colunas = Lista.COLUNAS;
        String[] whereArgs = new String[]{id};

        Cursor c = db.query(Lista.TABELA, colunas, Lista.ID + " = ?", whereArgs, null, null, null);

        c.moveToFirst();

        Lista lista = new Lista();
        lista.setId(c.getLong(c.getColumnIndex(Lista.ID)));
        lista.setNome(c.getString(c.getColumnIndex(Lista.NOME)));
        lista.setMercado(c.getString(c.getColumnIndex(Lista.MERCADO)));
        lista.setData(c.getString(c.getColumnIndex(Lista.DATA)));
        lista.setTotal(c.getDouble(c.getColumnIndex(Lista.TOTALLISTA)));

        return lista;
    }

    public List<Lista> listar() {

        String[] colunas = Lista.COLUNAS;
        Cursor c = db.query(Lista.TABELA, colunas, null, null, null, null, null);

        List<Lista> listaList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Lista lista = new Lista();
                lista.setId(c.getLong(c.getColumnIndex(Lista.ID)));
                lista.setNome(c.getString(c.getColumnIndex(Lista.NOME)));
                lista.setMercado(c.getString(c.getColumnIndex(Lista.MERCADO)));
                lista.setData(c.getString(c.getColumnIndex(Lista.DATA)));
                lista.setTotal(c.getDouble(c.getColumnIndex(Lista.TOTALLISTA)));

                listaList.add(lista);

                Log.i("lista", lista.getNome());
            } while (c.moveToNext());
        }
        return listaList;
    }


    public void excluir(String id) {
        String[] whereArgs = new String[]{id};
        db.delete(Lista.TABELA, Lista.ID + " = ?", whereArgs);
    }
}
