package com.example.leoci.cadastrocomlayoutintent;

import java.io.Serializable;

/**
 * Created by leoci on 01/03/2017.
 */
public class Contato implements Serializable{
    private String nome;
    private String fone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                '}';
    }
}
