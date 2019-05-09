package com.example.nodeterministas;

public class FuenteContactos {

    String nombreContacto;
    String numeroContacto;

    public FuenteContactos(String nombreContacto, String numeroContacto) {
        this.nombreContacto = nombreContacto;
        this.numeroContacto = numeroContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }
}
