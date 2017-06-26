package com.example.leoci.meuprimeirobancov1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FabricaConexao extends SQLiteOpenHelper {
    public FabricaConexao(Context context) {
        super(context, "banco.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Carro ( nro_chassi int not null, marca text, modelo text, primary key (nro_chassi) )");
//outros create aqui!
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table Carro");
        onCreate(db);
    }
}