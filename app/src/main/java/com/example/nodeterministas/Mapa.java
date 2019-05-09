package com.example.nodeterministas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

        this.googleMap.addMarker(new MarkerOptions().title("La Paz").position(new LatLng(-16.5, -68.1500015)).snippet("Ciudad"));
        this.googleMap.addMarker(new MarkerOptions().title("Beyond").position(new LatLng(-16.5752060, -68.12)).snippet("Discoteca"));
        this.googleMap.addMarker(new MarkerOptions().title("Legend").position(new LatLng(-16.575195,-68.12)).snippet("Discoteca"));
        this.googleMap.addMarker(new MarkerOptions().title("London").position(new LatLng(-16.575206,-68 )).snippet("Discoteca"));
        this.googleMap.addMarker(new MarkerOptions().title("Gitana").position(new LatLng(-16.5424351,-68.09)).snippet("Discoteca"));
        this.googleMap.addMarker(new MarkerOptions().title("Glam").position(new LatLng(-16.5095712,-68.1308)).snippet("Discoteca"));
        this.googleMap.addMarker(new MarkerOptions().title("Pa Goza").position(new LatLng(-16.575206,-68 )).snippet("Discoteca"));





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
