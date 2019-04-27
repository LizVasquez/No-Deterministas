package com.example.nodeterministas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Busquemos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busquemos);
        Button google_maps = (Button) findViewById(R.id.google_maps);
        google_maps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){

                Toast.makeText(Busquemos.this, "Acabas de hacer login!!", Toast.LENGTH_LONG).show();
                // Creamos un intent para ir a otro activity
                Intent intent = new Intent(Busquemos.this,Mapa.class);
                startActivity(intent);
            }
        });

    }
}
