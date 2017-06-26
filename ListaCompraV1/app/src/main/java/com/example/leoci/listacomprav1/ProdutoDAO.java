package com.example.leoci.listacomprav1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 11/04/2017.
 */

class ProdutoDAO {
    private SQLiteDatabase db;

    public ProdutoDAO(SQLiteDatabase db){
        this.db = db;
    }

    public void insert(Produto produto){
        String sql = "insert into produto (listaid, produto, quantidade, preco) values (?, ?, ?, ?)";
        Object bindArgs[] = new Object[]{
                produto.getListaId(),produto.getProduto(), produto.getQuantidade(), produto.getPreco()
        };
        db.execSQL(sql, bindArgs);
    }

    public void delete(Produto produto){
        String sql = "delete from produto where produtoid = ?";
        Object bindArgs[] = new Object[]{produto.getProdutoId()};
        db.execSQL(sql, bindArgs);
    }

    public void deleteById(Long produtoId){
        String sql = "delete from produto where produtoid = ?";
        Object bindArgs[] = new Object[]{produtoId};
        db.execSQL(sql, bindArgs);
    }

    public void update(Produto produto){
        String sql = "update produto set listaid = ?, produto = ?, quantidade = ?, preco = ? where produtoid = ?";
        Object bindArgs[] = new Object[]{
                produto.getListaId(), produto.getProduto(), produto.getQuantidade(), produto.getPreco(), produto.getProdutoId()
        };
        db.execSQL(sql, bindArgs);
    }

    public List<Produto> listAll(Long id){
        List<Produto> produtos = new ArrayList<Produto>();
        Cursor cursor = db.query("Produto", new String[]{"produtoid","listaid","produto","quantidade","preco"},"listaid = "+id,null,null,null,"produtoid");
        while (cursor!=null && cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setProdutoId(cursor.getLong(0));
            produto.setListaId(cursor.getLong(1));
            produto.setProduto(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));
            produto.setPreco(new BigDecimal(cursor.getDouble(4)));
            produtos.add(produto);
        }

        if (cursor!=null && !cursor.isClosed()){
            cursor.close();
        }
        return produtos;
    }

    public Produto getById(Long produtoId){
        Produto produto = null;
        Cursor cursor = db.query("Produto", new String[]{"produtoid","listaid","produto","quantidade","preco"},"produtoid"+produtoId,null,null,null,null);
        if(cursor!=null && cursor.moveToNext()){
            produto = new Produto();
            produto.setProdutoId(cursor.getLong(0));
            produto.setListaId(cursor.getLong(1));
            produto.setProduto(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));
            produto.setPreco(new BigDecimal(cursor.getDouble(4)));
        }
        if (cursor!=null && !cursor.isClosed()){
            cursor.close();
        }
        return produto;
    }
}
