package com.example.nodeterministas;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

public class Busquemos extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_busquemos);
            Button google_maps = (Button) findViewById(R.id.google_maps);
            google_maps.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){


                    // Creamos un intent para ir a otro activity
                    Intent intent = new Intent(Busquemos.this,Mapa.class);
                    startActivity(intent);
                }
            });

// Request Permissions
     /*       String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};

            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, 123, perms)
                            .setPositiveButtonText("ACEPTAR")
                            .setNegativeButtonText("CANCELAR")
                            .build());

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
       */ }

    }


