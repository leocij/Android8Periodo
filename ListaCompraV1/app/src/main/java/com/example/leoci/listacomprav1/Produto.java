package com.example.leoci.listacomprav1;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by leoci on 11/04/2017.
 */

class Produto {
    private Long produtoId;
    private Long listaId;
    private String produto;
    private int quantidade;
    private BigDecimal preco;

    public Produto() {
        setProdutoId(produtoId);
        setListaId(listaId);
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

    public Long getListaId() {
        return listaId;
    }

    public void setListaId(Long listaId) {
        this.listaId = listaId;
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
        return "Produto: " + produto
                + "\nQuantidade: " + quantidade
                + "\nPreco: " + preco.setScale(2, RoundingMode.DOWN);
    }
}
