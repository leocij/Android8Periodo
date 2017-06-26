package com.example.leoci.provav1;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by leoci on 06/04/2017.
 */

class Produto {
    private Long produtoId;
    private String produto;
    private int quantidade;
    private BigDecimal preco;

    public Produto() {
        setProdutoId(produtoId);
        setProduto(produto);
        setQuantidade(quantidade);
        setPreco(preco);
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ID: " + produtoId +
                "\nProduto: " + produto +
                "\nQuantidade: " + quantidade +
                "\nPreço: " + currencyFormat(preco);
    }

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }
}


