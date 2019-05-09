package com.example.nodeterministas;

public class FuenteAplicacion {

    String nombre;
    int imagenAplicacion;
    String packageName;

    public FuenteAplicacion(String nombre, int imagenAplicacion, String packageName) {
        this.nombre = nombre;
        this.imagenAplicacion = imagenAplicacion;
        this.packageName = packageName;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPackageName() { return packageName; }

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
