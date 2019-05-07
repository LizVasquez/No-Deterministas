package com.example.nodeterministas;

public class FuenteAplicacion {

    String nombre;
    int imagenAplicacion;

    public FuenteAplicacion(String nombre, int imagenAplicacion) {
        this.nombre = nombre;
        this.imagenAplicacion = imagenAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagenAplicacion() {
        return imagenAplicacion;
    }

    public void setImagenAplicacion(int imagenAplicacion) {
        this.imagenAplicacion = imagenAplicacion;
    }
}
