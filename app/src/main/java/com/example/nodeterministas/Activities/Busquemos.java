package com.example.nodeterministas.Activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nodeterministas.Mapa;
import com.example.nodeterministas.R;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class Busquemos extends AppCompatActivity implements View.OnClickListener{
    Button GoogleMaps, QuienDaCasa1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busquemos);
            GoogleMaps = findViewById(R.id.button_google_maps);
            QuienDaCasa1=findViewById(R.id.button_quiendacasa);




// Request Permissions
            String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};

            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, 123, perms)
                            .setPositiveButtonText("ACEPTAR")
                            .setNegativeButtonText("CANCELAR")
                            .build());

        }

    @Override
    public void onClick(View view) {
        if (view.getId() ==GoogleMaps.getId()) {
            Intent(Mapa.class);
        }if (view.getId() ==QuienDaCasa1.getId()) {

            Intent intent =new Intent(this,QuienDaCasa1.class);
            startActivity(intent);
        }
    }

    private void Intent(Class clase){
        Intent intent =new Intent(this,clase);
        startActivity(intent);
    }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        }

    }


