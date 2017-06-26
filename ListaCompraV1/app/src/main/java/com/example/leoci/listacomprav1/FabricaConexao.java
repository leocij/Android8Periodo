package com.example.leoci.listacomprav1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leoci on 09/04/2017.
 */

public class FabricaConexao extends SQLiteOpenHelper {
    public FabricaConexao(Context context) {
        super(context, "listav1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table lista (id integer primary key autoincrement, supermercado text, data date)");
        db.execSQL("create table produto (produtoid integer primary key autoincrement, listaid integer, produto text, quantidade int, preco real, foreign key(listaid) references lista(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - Procurar o que fazer aqui.

        //db.execSQL("drop table Carro");
        //onCreate(db);
    }
}