package com.example.nodeterministas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BloquearAplicaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_aplicaciones);

        ArrayList<FuenteAplicacion> lista =  new ArrayList<>();
        lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo));
        lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram));
        lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter));
        lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo));
        lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram));
        lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter));
        lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo));
        lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram));
        lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter));


        RecyclerView contenedor = (RecyclerView) findViewById(R.id.contenedor);
        contenedor.setHasFixedSize(false);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        contenedor.setAdapter(new AdaptadorRecyclerAplicaciones(lista));
        contenedor.setLayoutManager(layout);
    }

}
