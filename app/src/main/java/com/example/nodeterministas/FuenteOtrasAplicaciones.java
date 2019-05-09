package com.example.nodeterministas;

public class FuenteOtrasAplicaciones {

    String nombreOtraApp;
    String packageName;

    public FuenteOtrasAplicaciones(String nombreOtraApp, String packageName) {
        this.nombreOtraApp = nombreOtraApp;
        this.packageName = packageName;
    }

    public String getNombreOtraApp() {
        return nombreOtraApp;
    }

    public String getPackageName() { return packageName; }

    public void setNombreOtraApp(String nombreOtraApp) {
        this.nombreOtraApp = nombreOtraApp;
    }
}
