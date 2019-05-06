package com.example.nodeterministas.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nodeterministas.R;

public class Idioma extends AppCompatActivity implements View.OnClickListener {

    private Button button_español, button_ingles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idioma);

        button_español =  findViewById(R.id.button_español);
        button_ingles = (Button) findViewById(R.id.button_ingles);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == button_español.getId()) {
            Intent intent = new Intent(Idioma.this, BienvenidosMenu.class);
            startActivity(intent);
        }
        if (view.getId() == button_ingles.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);


        }
    }
}

