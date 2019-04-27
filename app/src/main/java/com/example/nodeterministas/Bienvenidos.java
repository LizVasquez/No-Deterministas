package com.example.nodeterministas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bienvenidos extends AppCompatActivity implements View.OnClickListener {

    Button eventos, yaEstoyFienta, contactEmergencia, organizaTuFiesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenidos);


        eventos = findViewById(R.id.button_eventos);
        yaEstoyFienta = findViewById(R.id.button_ya_estoy_en_fiesta);
        contactEmergencia = findViewById(R.id.button_contc_de_emergencia);
        organizaTuFiesta = findViewById(R.id.button_organiza_tu_fiesta);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == eventos.getId()) {
            Intent intent = new Intent(this, Busquemos.class);
            startActivity(intent);
        }
        if (view.getId() == yaEstoyFienta.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);
            Toast.makeText(this, "Falta", Toast.LENGTH_LONG).show();

        }
        if (view.getId() == contactEmergencia.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);
            Toast.makeText(this, "Falta", Toast.LENGTH_LONG).show();
        }
        if (view.getId() == yaEstoyFienta.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);
            Toast.makeText(this, "Falta", Toast.LENGTH_LONG).show();

        }
        if (view.getId() == organizaTuFiesta.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);
            Toast.makeText(this, "Falta", Toast.LENGTH_LONG).show();

        }
    }
}
