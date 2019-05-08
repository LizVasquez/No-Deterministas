package com.example.nodeterministas.Activities;

import android.content.Intent;
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

import com.example.nodeterministas.AdaptadorRecyclerAplicaciones;
import com.example.nodeterministas.AdaptadorRecyclerOtrasApp;
import com.example.nodeterministas.BloquearAplicaciones;
import com.example.nodeterministas.FuenteOtrasAplicaciones;
import com.example.nodeterministas.R;

import java.util.ArrayList;
import java.util.List;

public class ListaAplicacionesInstaladas extends AppCompatActivity implements View.OnClickListener{

    ListView listaAplicaciones;
    Button confirmarCambios_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aplicaciones_instaladas);

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
            }
        }

        List<FuenteOtrasAplicaciones> listaOtrasApp = new ArrayList<>();
        for(int i=0; i<ListaApps.size(); i++){
            listaOtrasApp.add(new FuenteOtrasAplicaciones(ListaApps.get(i)));
        }

        RecyclerView otrasAppRv = (RecyclerView) findViewById(R.id.contenedor_aplicaciones);
        otrasAppRv.setHasFixedSize(false);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        otrasAppRv.setAdapter(new AdaptadorRecyclerOtrasApp(listaOtrasApp));
        otrasAppRv.setLayoutManager(layout);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.confirmarcambios_btn){
            Intent intent = new Intent(confirmarCambios_btn.getContext(), BloquearAplicaciones.class);
            startActivity(intent);
        }
    }
}
