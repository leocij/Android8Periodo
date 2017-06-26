package com.example.leoci.meuprimeirobancov2;

/**
 * Created by leoci on 16/03/2017.
 */

class Carro {
    private Integer nroChassi;
    private String marca;
    private String modelo;

    public Carro() {
        setNroChassi(nroChassi);
        setMarca(marca);
        setModelo(modelo);
    }

    public Integer getNroChassi() {
        return nroChassi;
    }

    public void setNroChassi(Integer nroChassi) {
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

    @Override
    public String toString() {
        return "nroChassi = " + nroChassi + "\nmarca = " + marca + "\nmodelo = " + modelo;
    }
}
