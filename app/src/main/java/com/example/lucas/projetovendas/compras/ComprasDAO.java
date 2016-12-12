package com.example.lucas.projetovendas.compras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.projetovendas.sqlite.BancoDados;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucas on 01/12/16.
 */

public class ComprasDAO {


    SQLiteDatabase db;

    public ComprasDAO(Context context){ db = BancoDados.getDB(context);}


    public void salvar(Compras compra) {
        ContentValues values = new ContentValues();
        values.put(Compras.PRODUTO, compra.getProduto());
        values.put(Compras.PRECO, compra.getPreco());
        values.put(Compras.QUANTIDADE, compra.getQuantidade());
        values.put(Compras.FOTO, compra.getFoto());
        values.put(Compras.VALORGASTO, compra.getValor_gasto());
        db.insert(Compras.TABELA, null, values);
    }

    public void alterar(Compras compra) {
        ContentValues values = new ContentValues();
        values.put(Compras.PRODUTO, compra.getProduto());
        values.put(Compras.PRECO, compra.getPreco());
        values.put(Compras.QUANTIDADE, compra.getQuantidade());
        values.put(Compras.FOTO, compra.getFoto());
        values.put(Compras.VALORGASTO, compra.getValor_gasto());

        String id = String.valueOf(compra.getId());
        String[] whereArgs = new String[]{id};

        db.update(Compras.TABELA, values, Compras.ID +" = ?", whereArgs);
    }

    public Compras buscar(String id) {


        String[] colunas = Compras.COLUNAS;
        String[] whereArgs = new String[]{id};

        Cursor c = db.query(Compras.TABELA, colunas,Compras.ID + " = ?", whereArgs, null, null, null);

        c.moveToFirst();

        Compras compras = new Compras();
        compras.setId(c.getLong(c.getColumnIndex(Compras.ID)));
        compras.setProduto(c.getString(c.getColumnIndex(Compras.PRODUTO)));
        compras.setPreco(c.getDouble(c.getColumnIndex(Compras.PRECO)));
        compras.setQuantidade(c.getInt(c.getColumnIndex(Compras.QUANTIDADE)));
        compras.setFoto(c.getString(c.getColumnIndex(Compras.FOTO)));
        compras.setValor_gasto(c.getDouble(c.getColumnIndex(Compras.VALORGASTO)));


        return compras;
    }

    public List<Compras> listar() {

        String[] colunas = Compras.COLUNAS;
        Cursor c = db.query(Compras.TABELA, colunas, null, null, null, null, null);

        List<Compras> comprasList = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Compras compra = new Compras();
                compra.setId(c.getLong(c.getColumnIndex(Compras.ID)));
                compra.setProduto(c.getString(c.getColumnIndex(Compras.PRODUTO)));
                compra.setPreco(c.getDouble(c.getColumnIndex(Compras.PRECO)));
                compra.setQuantidade(c.getInt(c.getColumnIndex(Compras.QUANTIDADE)));
                compra.setFoto(c.getString(c.getColumnIndex(Compras.FOTO)));
                compra.setValor_gasto(c.getDouble(c.getColumnIndex(Compras.VALORGASTO)));

                comprasList.add(compra);

                Log.i("lista", compra.getProduto());
            } while (c.moveToNext());
        }
        return comprasList;
    }


    public void excluir(String id) {
        String[] whereArgs = new String[]{id};
        db.delete(Compras.TABELA,Compras.ID + " = ?", whereArgs);
    }
}
