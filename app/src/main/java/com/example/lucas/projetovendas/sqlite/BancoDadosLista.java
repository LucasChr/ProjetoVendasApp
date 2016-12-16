package com.example.lucas.projetovendas.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 12/12/16.
 */

public class BancoDadosLista {

    private static final String NOME_BANCO = "nomeLista";
    private static final int VERSAO_BANCO = 2;

    private static final String[] SCRIPT_DATABASE_DELETE = new String[]{"DROP TABLE IF EXISTS nomeLista;"};


    private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "create table nomeLista(_id integer primary key, l_nome text, l_mercado text, l_data text, l_total double)"};

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx) {
        if (db == null) {
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }
}
