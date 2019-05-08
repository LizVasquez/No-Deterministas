package com.example.nodeterministas.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.nodeterministas.R;

public class MainActivity extends Activity {

    private final int DURACION_SPLASH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREAREMOS UN HANDLER DONDE LE DECIMOS QUE ESPERE UNOS SEGUNDOS Y MUESTRE OTRA PANTALLA
        new Handler().postDelayed(new Runnable() {
            @Override


            public void run() {

                    Intent Login = new Intent(getApplicationContext(), Login.class);
                    startActivity(Login);
                    finish();


            }
        },
                //TIEMPO EN MILISEGUNDOS
                DURACION_SPLASH);
    }
}
