package com.example.nodeterministas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nodeterministas.BloquearAplicaciones;
import com.example.nodeterministas.Busquemos;
import com.example.nodeterministas.Main;
import com.example.nodeterministas.OrganizarFiesta;
import com.example.nodeterministas.R;
import com.example.nodeterministas.Temporizador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BienvenidosMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Button eventos, yaEstoyFienta, contactEmergencia, organizaTuFiesta;

    FirebaseAuth mAuth;
    FirebaseUser currentUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenidos_menu);


        eventos = findViewById(R.id.button_eventos);
        yaEstoyFienta = findViewById(R.id.button_ya_estoy_en_fiesta);
        contactEmergencia = findViewById(R.id.button_contc_de_emergencia);
        organizaTuFiesta = findViewById(R.id.button_organiza_tu_fiesta);


        mAuth = FirebaseAuth.getInstance();
        //puedes usar la propiedad currentUser para obtener el usuario que accedió
        // si no accedió ningún usuario, el valor de currentUser es null:
        currentUser = mAuth.getCurrentUser();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();
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
            Intent intent = new Intent(this, Temporizador.class);
            startActivity(intent);
        }
        if (view.getId() == organizaTuFiesta.getId()) {
            Intent intent = new Intent(this, OrganizarFiesta.class);
            startActivity(intent);

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

        if (id == R.id.nav_idioma) {
            HacerIntent(Idioma.class);
        } else if (id == R.id.nav_cerrar_sesion) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(),Login.class);
            startActivity(loginActivity);
            finish();
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



    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());

        // ahora usaremos Glide para cargar la imagen del usuario
        // Primero necesitamos importar la biblioteca

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);




    }
}
