package com.example.leoci.listacomprav1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 10/04/2017.
 */

class ListaDAO {
    private SQLiteDatabase db;

    public ListaDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insert(Lista lista){
        String sql = "insert into lista (supermercado, data) values (?, ?)";
        Object bindArgs[] = new Object[]{
                lista.getSupermercado(), lista.getData()
        };

        db.execSQL(sql, bindArgs);
    }

    public void delete(Lista lista){
        String sql = "delete from lista where id = ?";
        Object bindArgs[] = new Object[]{
                lista.getId()
        };

        db.execSQL(sql, bindArgs);
    }

    public void deleteById(Long id){
        String sql = "delete from lista where id = ?";
        Object bindArgs[] = new Object[]{
                id
        };

        db.execSQL(sql, bindArgs);
    }

    public void update(Lista lista){
        String sql = "update lista set supermercado = ?, data = ? where id = ?";
        Object bindArgs[] = new Object[]{
                lista.getSupermercado(), lista.getData(), lista.getId()
        };

        db.execSQL(sql, bindArgs);
    }

    public List<Lista> listAll(){
        List<Lista> listas = new ArrayList<Lista>();
        Cursor cursor = db.query("Lista", new String[]{"id","supermercado","data"}, null,null,null,null,"id");
        while(cursor!=null && cursor.moveToNext()){
            Lista lista = new Lista();
            lista.setId(cursor.getLong(0));
            lista.setSupermercado(cursor.getString(1));
            lista.setData(Date.valueOf(cursor.getString(2)));
            listas.add(lista);
        }

        if (cursor!=null && !cursor.isClosed()){
            cursor.close();
        }

        return listas;
    }

    public Lista getById(Long id){
        Lista lista = null;
        Cursor cursor = db.query("Lista", new String[]{"id","supermercado","data"}, "id"+id,null,null,null,null);
        while(cursor!=null && cursor.moveToNext()){
            lista = new Lista();
            lista.setId(cursor.getLong(0));
            lista.setSupermercado(cursor.getString(1));
            lista.setData(Date.valueOf(cursor.getString(2)));
        }

        if (cursor!=null && !cursor.isClosed()){
            cursor.close();
        }

        return lista;
    }
}
