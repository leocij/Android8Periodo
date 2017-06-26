package com.example.leoci.cadastro1;

/**
 * Created by leoci on 20/02/2017.
 */
public class Contato {
    private String nome;
    private String fone;

    public Contato() {
    }

    public Contato(String nome, String fone) {
        this.nome = nome;
        this.fone = fone;
    }

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
