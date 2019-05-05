package com.example.nodeterministas.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
                Intent intent =new Intent(MainActivity.this , Login.class);
                startActivity(intent);

            }
        },
                //TIEMPO EN MILISEGUNDOS
                DURACION_SPLASH);
    }
}
