package com.example.lucas.projetovendas.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 08/12/16.
 */

public class BancoDadosMercados {


    private static final String NOME_BANCO = "nomeMercado";
    private static final int VERSAO_BANCO = 1;

    private static final String[] SCRIPT_DATABASE_DELETE = new String[]{"DROP TABLE IF EXISTS nomeMercado;"};


    private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "create table nomeMercado(_id integer primary key, m_mercado text, m_telefone text, m_latitude text, m_longitude text, m_foto text)"};

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx) {
        if (db == null) {
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }

}
