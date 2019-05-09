package com.example.nodeterministas.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nodeterministas.AdaptadorContacto;
import com.example.nodeterministas.FuenteContactos;
import com.example.nodeterministas.R;

import java.util.ArrayList;
import java.util.List;

public class MisContactos extends AppCompatActivity {

    RecyclerView contenedorContacto;
    List<FuenteContactos> listaDeContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_contactos);

        contenedorContacto = findViewById(R.id.contenedor_contactos);
        contenedorContacto.setLayoutManager(new LinearLayoutManager(this));

        listaDeContactos = new ArrayList<>();
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));
        listaDeContactos.add(new FuenteContactos("Alexia Mollo", "7654321"));
        listaDeContactos.add(new FuenteContactos("Juan Perez", "12345678"));
        listaDeContactos.add(new FuenteContactos("Persona 1", "45312"));
        listaDeContactos.add(new FuenteContactos("Persona 2", "1234"));


        AdaptadorContacto adaptador = new AdaptadorContacto(listaDeContactos);
        contenedorContacto.setAdapter(adaptador);


    }
}
