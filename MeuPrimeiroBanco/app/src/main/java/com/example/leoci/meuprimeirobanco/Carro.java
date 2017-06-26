package com.example.leoci.meuprimeirobanco;

/**
 * Created by leoci on 15/03/2017.
 */

class Carro {
    private int nroChassi;
    private String marca;
    private String modelo;

    public Carro() {
    }

    public int getNroChassi() {
        return nroChassi;
    }

    public void setNroChassi(int nroChassi) {
        this.nroChassi = nroChassi;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
