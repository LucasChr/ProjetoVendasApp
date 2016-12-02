package com.example.lucas.projetovendas.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 28/11/16.
 */
public class BancoDados {

    private static final String NOME_BANCO = "comprasMercado";
    private static final int VERSAO_BANCO = 1;

    //Script
    private static final String[] SCRIPT_DATABASE_DELETE = new String[] {"DROP TABLE IF EXISTS comprasMercado;"};

    //Tabela com id sequencial usa-se _id
    private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "create table comprasMercado(_id integer primary key, cp_produto text, cp_preco double, cp_quantidade int, cp_nome_mercado text, cp_valor_gasto double);"
    };

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx) {
        if (db == null) {
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;

    }
}
