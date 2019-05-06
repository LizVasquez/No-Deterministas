package com.example.nodeterministas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BloquearAplicaciones extends AppCompatActivity {

    RecyclerView rv;
    public List<Aplicacion> listaAplicaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_aplicaciones);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        // Con este metodo inicializamos el arreglo de lista de aplicaciones para introducirlos en nuestro recyclerview
        RVAdapter adapter = new RVAdapter(listaAplicaciones);
        rv.setAdapter(adapter);
    }

    public void inicializarDatos(){
        listaAplicaciones = new ArrayList<>();
        listaAplicaciones.add(new Aplicacion("Facebook", R.drawable.facebook_logo));
        listaAplicaciones.add(new Aplicacion("Instagram", R.drawable.instagram));
    }
}
