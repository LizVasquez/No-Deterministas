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
        this.googleMap.addMarker(new MarkerOptions().title("Santa Cruz").position(new LatLng(-17.7862892	, -63.1811714)).snippet("Ciudad"));
        this.googleMap.addMarker(new MarkerOptions().title("Santa Cruz").position(new LatLng(-17.7862892	, -63.1811714)).snippet("Ciudad"));






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
