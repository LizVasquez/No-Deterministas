package com.example.nodeterministas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync (  this);
    }


    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.addMarker(new MarkerOptions().title("Beyond").position(new LatLng(-16.5752060, -68.2256)).snippet("Discoteca, Abierto desde 14:00 pm"));
        this.googleMap.addMarker(new MarkerOptions().title("Legend").position(new LatLng(-16.575195, -68.2073)).snippet("Discoteca, Abierto desde 14:00 pm"));
        this.googleMap.addMarker(new MarkerOptions().title("London").position(new LatLng(-16.575206, -68.1511)).snippet("Discoteca, Abierto desde 16:00 pm"));
        this.googleMap.addMarker(new MarkerOptions().title("Gitana").position(new LatLng(-16.5424351, -68.0980)).snippet("Discoteca, Abierto desde 20:00 pm"));
        this.googleMap.addMarker(new MarkerOptions().title("Glam").position(new LatLng(-16.5095712, -68.1308)).snippet("Discoteca, Abierto desde 21:00 pm"));
        this.googleMap.addMarker(new MarkerOptions().title("Pa Gozar").position(new LatLng(-16.575206, -68.1093)).snippet("Discoteca, Abierto desde 01:00 am "));


        // Estamos enfocando la camara a La Paz
        LatLng cali = new LatLng(-16.4897, -68.1193);
        googleMap.addMarker(new MarkerOptions()
                .position(cali)
                .title("La Paz"));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(cali)
                .zoom(11)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });

        this.googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


            }
        });
    }

}