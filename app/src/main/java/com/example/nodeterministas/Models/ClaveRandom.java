package com.example.nodeterministas.Models;

import java.util.Random;

public class ClaveRandom {
      private Random random=new Random();
    private int clave = random.nextInt((9999-1000+1)+1000);

    private String numeroClave = Integer.toString(clave);

    public ClaveRandom() {
    }

    public String getNumeroClave() {
        return numeroClave;
    }

    public void setNumeroClave(String numeroClave) {
        this.numeroClave = numeroClave;
    }
}
