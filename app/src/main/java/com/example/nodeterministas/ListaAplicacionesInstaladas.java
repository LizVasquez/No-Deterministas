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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nodeterministas.AdaptadorRecyclerAplicaciones;
import com.example.nodeterministas.AdaptadorRecyclerOtrasApp;
import com.example.nodeterministas.BloquearAplicaciones;
import com.example.nodeterministas.FuenteOtrasAplicaciones;
import com.example.nodeterministas.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListaAplicacionesInstaladas extends AppCompatActivity implements View.OnClickListener{

    ListView listaAplicaciones;
    Button confirmarCambios_btn;

    StringBuffer sb = null;

    AdaptadorRecyclerOtrasApp adaptadorOtrasApp;
    List<PackageName> listaPackageNamesChequeados;

    List<String> packageNamesString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aplicaciones_instaladas);

        listaPackageNamesChequeados = new ArrayList<>();
        cargarDatos();
        //Creacion de la lista packageNamesString
        packageNamesString = new ArrayList<>();

        //Inicializacion de relacion de componentes
        confirmarCambios_btn = findViewById(R.id.confirmarcambios_btn);
        confirmarCambios_btn.setOnClickListener(this);

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
                packageNamesString.add(packageInfo.packageName);
            }
        }

        List<FuenteOtrasAplicaciones> listaOtrasApp = new ArrayList<>();
        for(int i=0; i<ListaApps.size(); i++){
            listaOtrasApp.add(new FuenteOtrasAplicaciones(ListaApps.get(i), packageNamesString.get(i)));
        }

        adaptadorOtrasApp = new AdaptadorRecyclerOtrasApp(listaOtrasApp);

        RecyclerView otrasAppRv = (RecyclerView) findViewById(R.id.contenedor_aplicaciones);
        otrasAppRv.setHasFixedSize(false);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        otrasAppRv.setLayoutManager(layout);
        otrasAppRv.setAdapter(adaptadorOtrasApp);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.confirmarcambios_btn){
            /*Intent intent = new Intent(confirmarCambios_btn.getContext(), BloquearAplicaciones.class);
            startActivity(intent);*/

            sb = new StringBuffer();

            if(adaptadorOtrasApp.checkedApps.size() != listaPackageNamesChequeados.size() && adaptadorOtrasApp.checkedApps.size() > 0){
                listaPackageNamesChequeados = new ArrayList<>();
                for(FuenteOtrasAplicaciones app : adaptadorOtrasApp.checkedApps){
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

    public void guardarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("OtrasAplicacionesGuardadas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String aplicacionesGuardadas = gson.toJson(listaPackageNamesChequeados);
        editor.putString("Otras Aplicaciones Guardadas", aplicacionesGuardadas);
        editor.apply();
    }

    public void cargarDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences("OtrasAplicacionesGuardadas", MODE_PRIVATE);
        Gson gson = new Gson();
        String aplicacionesGuardadas = sharedPreferences.getString("Otras Aplicaciones Guardadas", null);
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
        SharedPreferences sharedPreferences = getSharedPreferences("OtrasAplicacionesGuardadas", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
