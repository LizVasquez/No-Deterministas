package com.example.nodeterministas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //CREAREMOS UN HANDLER DONDE LE DECIMOS QUE ESPERE UNOS SEGUNDOS Y MUESTRE OTRA PANTALLA
        new Handler().postDelayed(new Runnable() {
            @Override


            public void run() {
                Intent intent =new Intent(MainActivity.this ,Login.class);
                startActivity(intent);

            }
        },
                //TIEMPO EN MILISEGUNDOS
                5000);
    }
    public void doLogin(View view){
        Intent intent = new Intent(this, Bienvenidos.class);
        startActivity(intent);
    }
}
