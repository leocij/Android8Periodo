package com.example.leoci.listacomprav1;

import java.sql.Date;

/**
 * Created by leoci on 10/04/2017.
 */

class Lista {
    Long id;
    String supermercado;
    Date data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Lista: " + id
                + "\nSupermercado: " + supermercado
                + "\nData: " + data;
    }
}
