package com.example.lucas.projetovendas.mercado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.lucas.projetovendas.sqlite.BancoDadosMercados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lucas on 08/12/16.
 */

public class MercadoDAO {

    SQLiteDatabase db;

    public MercadoDAO(Context context) {
        db = BancoDadosMercados.getDB(context);
    }


    public void salvar(Mercado mercado) {
        ContentValues values = new ContentValues();
        values.put(Mercado.NOMEMERCADO, mercado.getNome_mercado());
        values.put(Mercado.TELEFONE, mercado.getTelefone());
        values.put(Mercado.LATITUDE, mercado.getLatitude());
        values.put(Mercado.LONGITUDE, mercado.getLongitude());
        values.put(Mercado.FOTO, mercado.getFoto());
        db.insert(Mercado.TABELA, null, values);
    }

    public void alterar(Mercado mercado) {
        ContentValues values = new ContentValues();
        values.put(Mercado.NOMEMERCADO, mercado.getNome_mercado());
        values.put(Mercado.TELEFONE, mercado.getTelefone());
        values.put(Mercado.LATITUDE, mercado.getLatitude());
        values.put(Mercado.LONGITUDE, mercado.getLongitude());
        values.put(Mercado.FOTO, mercado.getFoto());

        String id = String.valueOf(mercado.getId());
        String[] whereArgs = new String[]{id};

        db.update(Mercado.TABELA, values, Mercado.ID + " = ?", whereArgs);
    }

    public Mercado buscarNome(String nome) {


        String[] colunas = Mercado.COLUNAS;
        String[] whereArgs = new String[]{nome};

        Cursor c = db.query(Mercado.TABELA, colunas, Mercado.NOMEMERCADO + " = ?", whereArgs, null, null, null);

        c.moveToFirst();

        Mercado mercado = new Mercado();
        mercado.setId(c.getLong(c.getColumnIndex(Mercado.ID)));
        mercado.setNome_mercado(c.getString(c.getColumnIndex(Mercado.NOMEMERCADO)));
        mercado.setTelefone(c.getString(c.getColumnIndex(Mercado.TELEFONE)));
        mercado.setLatitude(c.getString(c.getColumnIndex(Mercado.LATITUDE)));
        mercado.setLongitude(c.getString(c.getColumnIndex(Mercado.LONGITUDE)));
        mercado.setFoto(c.getString(c.getColumnIndex(Mercado.FOTO)));

        return mercado;
    }


    public Mercado buscar(String id) {


        String[] colunas = Mercado.COLUNAS;
        String[] whereArgs = new String[]{id};

        Cursor c = db.query(Mercado.TABELA, colunas, Mercado.ID + " = ?", whereArgs, null, null, null);

        c.moveToFirst();

        Mercado mercado = new Mercado();
        mercado.setId(c.getLong(c.getColumnIndex(Mercado.ID)));
        mercado.setNome_mercado(c.getString(c.getColumnIndex(Mercado.NOMEMERCADO)));
        mercado.setTelefone(c.getString(c.getColumnIndex(Mercado.TELEFONE)));
        mercado.setLatitude(c.getString(c.getColumnIndex(Mercado.LATITUDE)));
        mercado.setLongitude(c.getString(c.getColumnIndex(Mercado.LONGITUDE)));
        mercado.setFoto(c.getString(c.getColumnIndex(Mercado.FOTO)));

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
                mercado.setFoto(c.getString(c.getColumnIndex(Mercado.FOTO)));

                mercadoList.add(mercado);

                Log.i("lista", mercado.getNome_mercado());
            } while (c.moveToNext());
        }
        return mercadoList;
    }

    public ArrayAdapter<String> listarMercado() {
        ArrayAdapter<String> nomes = null;

        List<Mercado> lista = listar();
        for (Iterator iterator = lista.iterator(); iterator.hasNext(); ) {
            Mercado mercado1 = (Mercado) iterator.next();
            String add = mercado1.getNome_mercado().toString();
            nomes.add(add);
        }
        return nomes;
    }

    public void excluir(String id) {
        String[] whereArgs = new String[]{id};
        db.delete(Mercado.TABELA, Mercado.ID + " = ?", whereArgs);
    }


}
