package com.example.nodeterministas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BloquearAplicaciones extends AppCompatActivity implements View.OnClickListener{

    Button enviarDatosBtn;
    Button otrasAplicacionesBtn;

    AdaptadorRecyclerAplicaciones adaptador;

    StringBuffer sb = null;

    List<String> listaPackageNames;

    List<PackageName> listaPackageNamesChequeados;

    String appBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_aplicaciones);

        listaPackageNamesChequeados = new ArrayList<>();
        cargarDatos();

        enviarDatosBtn = (Button)findViewById(R.id.enviarDatos_btn);
        enviarDatosBtn.setOnClickListener(this);

        otrasAplicacionesBtn = findViewById(R.id.otrasaplicaciones_btn);
        otrasAplicacionesBtn.setOnClickListener(this);

        listaPackageNames = new ArrayList<>();

        //get a list of installed apps.
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> ListaApps= new ArrayList<String>();
        for (ApplicationInfo packageInfo : packages)
        {
            if (packageInfo.sourceDir.startsWith("/data/app/") &&
                    packageInfo.packageName.toLowerCase().contains("widget")==false &&
                    packageInfo.packageName.equals("com.example.nodeterministas")==false)
            {
                ListaApps.add(String.valueOf(pm.getApplicationLabel(packageInfo)));
                listaPackageNames.add(packageInfo.packageName);
            }
        }

        ArrayList<FuenteAplicacion> lista =  new ArrayList<>();
        for(int i=0; i<ListaApps.size(); i++){
            switch (ListaApps.get(i)){
                case "Facebook":
                    lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo, listaPackageNames.get(i)));
                    break;
                case "WhatsApp":
                    lista.add(new FuenteAplicacion("WhatsApp", R.drawable.whatsapp, listaPackageNames.get(i)));
                    break;
                case "Twitter":
                    lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter, listaPackageNames.get(i)));
                    break;
                case "Instagram":
                    lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram, listaPackageNames.get(i)));
                    break;
                default:
                    break;
            }
        }

        RecyclerView contenedor = (RecyclerView) findViewById(R.id.contenedor);
        contenedor.setHasFixedSize(false);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        adaptador = new AdaptadorRecyclerAplicaciones(lista);
        contenedor.setAdapter(adaptador);
        contenedor.setLayoutManager(layout);

    }

    public RecyclerView.ViewHolder sacarViewPosicion(RecyclerView recyclerView, int pos){
        return recyclerView.findViewHolderForAdapterPosition(pos);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.otrasaplicaciones_btn){
            //Guardamos los cambios
            procesoGuardado();

            //Vamos al siguiente Activity
            Intent intent = new Intent(otrasAplicacionesBtn.getContext(), ListaAplicacionesInstaladas.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.enviarDatos_btn){
            //Guardamos los cambios
            procesoGuardado();

            //Iniciamos el siguiente activity para iniciar el temporizador
            Intent intent = new Intent(enviarDatosBtn.getContext(), Temporizador.class);
            startActivity(intent);

        }
    }

    public void guardarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("AplicacionesGuardadas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String aplicacionesGuardadas = gson.toJson(listaPackageNamesChequeados);
        editor.putString("Aplicaciones Guardadas", aplicacionesGuardadas);
        editor.apply();
    }

    public void cargarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("AplicacionesGuardadas", MODE_PRIVATE);
        Gson gson = new Gson();
        String aplicacionesGuardadas = sharedPreferences.getString("Aplicaciones Guardadas", null);
        Type type = new TypeToken<List<PackageName>>() {}.getType();
        listaPackageNamesChequeados = gson.fromJson(aplicacionesGuardadas, type);

        if(listaPackageNamesChequeados == null){
            Toast.makeText(this, "Lista Vacia", Toast.LENGTH_LONG).show();
            listaPackageNamesChequeados = new ArrayList<>();
        }else{
            Toast.makeText(this, "Lista Con Algunos Valores", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("AplicacionesGuardadas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void procesoGuardado(){
        sb = new StringBuffer();

        if(adaptador.aplicacionesChequeadas.size() != listaPackageNamesChequeados.size() && adaptador.aplicacionesChequeadas.size() > 0){
            listaPackageNamesChequeados = new ArrayList<>();
            for(FuenteAplicacion app : adaptador.aplicacionesChequeadas){
                listaPackageNamesChequeados.add(new PackageName(app.getPackageName()));
            }
            guardarDatos();
        }

        for(int i=0; i<listaPackageNamesChequeados.size(); i++){
            sb.append(listaPackageNamesChequeados.get(i).packageName);
            sb.append("\n");

        }



        if(listaPackageNamesChequeados.size() > 0){
            Toast.makeText(this, sb.toString() , Toast.LENGTH_SHORT).show();
        }
    }
}
