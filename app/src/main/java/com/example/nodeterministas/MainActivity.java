package com.example.nodeterministas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void doLogin(View view){
        Toast.makeText(this, "Acabas de hacer login!!", Toast.LENGTH_LONG).show();
        // Creamos un intent para ir a otro activity
        Intent intent = new Intent(this, menuPrincipalActivity.class);
        startActivity(intent);
    }
    //no aparece el checkbox

}
