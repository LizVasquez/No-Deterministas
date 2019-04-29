package com.example.nodeterministas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class BienvenidosMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button eventos, yaEstoyFienta, contactEmergencia, organizaTuFiesta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenidos_menu);


        eventos = findViewById(R.id.button_eventos);
        yaEstoyFienta = findViewById(R.id.button_ya_estoy_en_fiesta);
        contactEmergencia = findViewById(R.id.button_contc_de_emergencia);
        organizaTuFiesta = findViewById(R.id.button_organiza_tu_fiesta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == eventos.getId()) {
            Intent intent = new Intent(this, Busquemos.class);
            startActivity(intent);
        }
        if (view.getId() == yaEstoyFienta.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);


        }
        if (view.getId() == contactEmergencia.getId()) {
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);

        }
        if (view.getId() == yaEstoyFienta.getId()) {
            Intent intent = new Intent(this,Temporizador.class);
            startActivity(intent);

        }
        if (view.getId() == organizaTuFiesta.getId()) {
//            Intent intent = new Intent(this, ____.class);
//            startActivity(intent);

        }}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            HacerIntent(Idioma.class);
        } else if (id == R.id.nav_gallery) {
           HacerIntent(Login.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void HacerIntent(Class clase){
       Intent intent =new Intent(BienvenidosMenu.this,clase);
        startActivity(intent);
    }


//    private void CargarFragmento(Fragment fragment){
//        FragmentManager manager=getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.contenedor_fragmanto,fragment).commit();
//    }


}
