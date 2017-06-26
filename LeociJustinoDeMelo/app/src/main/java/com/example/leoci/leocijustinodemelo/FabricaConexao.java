package com.example.leoci.leocijustinodemelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leoci on 12/04/2017.
 */

public class FabricaConexao extends SQLiteOpenHelper {
    public FabricaConexao(Context context) {
        super(context, "provav1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table faixa (id integer primary key autoincrement, inicio text, fim text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - Procurar o que fazer aqui.

        //db.execSQL("drop table Carro");
        //onCreate(db);
    }
}
