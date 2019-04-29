package com.example.nodeterministas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Idioma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idioma);
         final Button button_español = (Button) findViewById(R.id.button_español);
         final Button button_ingles = (Button) findViewById(R.id.button_ingles);
         button_español.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view.getId() == button_español.getId()) {
                    Intent intent = new Intent(Idioma.this, Bienvenidos.class);
                    startActivity(intent);
                }
                if (view.getId() ==button_ingles.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);


                }
            }
        });

    }

}
