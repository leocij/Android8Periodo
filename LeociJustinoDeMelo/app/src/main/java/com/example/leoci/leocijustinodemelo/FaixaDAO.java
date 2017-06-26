package com.example.leoci.leocijustinodemelo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 12/04/2017.
 */

class FaixaDAO {
    private SQLiteDatabase db;

    public FaixaDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insert(Faixa faixa){
        String sql = "insert into faixa (inicio, fim) values (?,?)";
        Object bindArgs[] = new Object[]{
           faixa.getInicio(), faixa.getFim()
        };

        db.execSQL(sql, bindArgs);
    }

    public List<Faixa> listAll(){
        List<Faixa> faixas = new ArrayList<Faixa>();
        Cursor cursor = db.query("Faixa", new String[]{"inicio","fim"}, null, null,null,null,"id");
        while(cursor!=null && cursor.moveToNext()){
            Faixa faixa = new Faixa();
            faixa.setInicio(cursor.getString(0));
            faixa.setFim(cursor.getString(1));
            faixas.add(faixa);
        }

        if (cursor!=null && !cursor.isClosed()){
            cursor.close();
        }

        return faixas;
    }
}
