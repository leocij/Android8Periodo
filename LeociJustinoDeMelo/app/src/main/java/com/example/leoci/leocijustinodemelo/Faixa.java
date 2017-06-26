package com.example.leoci.leocijustinodemelo;

/**
 * Created by leoci on 12/04/2017.
 */

class Faixa {
    String inicio;
    String fim;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    @Override
    public String toString() {
        return "Faixa{" +
                "inicio='" + inicio + '\'' +
                ", fim='" + fim + '\'' +
                '}';
    }
}
