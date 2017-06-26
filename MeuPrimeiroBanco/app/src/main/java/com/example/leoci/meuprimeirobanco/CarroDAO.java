package com.example.leoci.meuprimeirobanco;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private SQLiteDatabase db;

    public CarroDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(Carro carro) {
        String sql = "insert into Carro (nro_chassi, marca, modelo) values (?, ?, ?)";
        Object bindArgs[] = new Object[]{
                carro.getNroChassi(), carro.getMarca(), carro.getModelo()};
        db.execSQL(sql, bindArgs);
    }

    public void delete(Carro carro) {
        String sql = "delete from Carro where nro_chassi = ?";
        Object bindArgs[] = new Object[]{carro.getNroChassi()};
        db.execSQL(sql, bindArgs);
    }

    public void delete(Integer nroChassi) {
        String sql = "delete from Carro where nro_chassi = ?";
        Object bindArgs[] = new Object[]{nroChassi};
        db.execSQL(sql, bindArgs);
    }

    public void update(Carro carro) {
        String sql = "update Carro set marca = ?, modelo = ? where nro_chassi = ?";
        Object bindArgs[] = new Object[]{
                carro.getMarca(), carro.getModelo(), carro.getNroChassi()};
        db.execSQL(sql, bindArgs);
    }

    public List<Carro> listAll() {
        List<Carro> carros = new ArrayList<Carro>();
        Cursor cursor = db.query("Carro", new String[]{"nro_chassi", "marca", "modelo"},
                null, null, null, null, "nro_chassi");
        while (cursor != null && cursor.moveToNext()) {
            Carro carro = new Carro();
            carro.setNroChassi(cursor.getInt(0));
            carro.setMarca(cursor.getString(1));
            carro.setModelo(cursor.getString(2));
            carros.add(carro);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return carros;
    }

    public Carro getById(Integer nroChassi) {
        Carro carro = null;
        Cursor cursor = db.query("Carro", new String[]{"nro_chassi", "marca", "modelo"},
                "nro_chassi=" + nroChassi, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            carro = new Carro();
            carro.setNroChassi(cursor.getInt(0));
            carro.setMarca(cursor.getString(1));
            carro.setModelo(cursor.getString(2));
        }
        if (cursor != null && !
                cursor.isClosed()) {
            cursor.close();
        }
        return carro;
    }
}