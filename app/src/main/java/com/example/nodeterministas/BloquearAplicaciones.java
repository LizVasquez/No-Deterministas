package com.example.nodeterministas;

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
import android.widget.Toast;

import com.example.nodeterministas.Activities.ListaAplicacionesInstaladas;
import com.example.nodeterministas.Activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BloquearAplicaciones extends AppCompatActivity implements View.OnClickListener{

    Button enviarDatosBtn;
    Button otrasAplicacionesBtn;

    AdaptadorRecyclerAplicaciones adaptador;

    StringBuffer sb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_aplicaciones);

        enviarDatosBtn = (Button)findViewById(R.id.enviarDatos_btn);
        enviarDatosBtn.setOnClickListener(this);

        otrasAplicacionesBtn = findViewById(R.id.otrasaplicaciones_btn);
        otrasAplicacionesBtn.setOnClickListener(this);

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

        ArrayList<FuenteAplicacion> lista =  new ArrayList<>();
        for(int i=0; i<ListaApps.size(); i++){
            switch (ListaApps.get(i)){
                case "Facebook":
                    lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo));
                    break;
                case "WhatsApp":
                    lista.add(new FuenteAplicacion("WhatsApp", R.drawable.whatsapp));
                    break;
                case "Twitter":
                    lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter));
                    break;
                case "Instagram":
                    lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram));
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
            //Intent intent = new Intent(enviarDatosBtn.getContext(), Temporizador.class);
            Intent intent = new Intent(otrasAplicacionesBtn.getContext(), ListaAplicacionesInstaladas.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.enviarDatos_btn){
            //Iniciamos el siguiente activity para controlar el temporizador
            /*Intent intent = new Intent(enviarDatosBtn.getContext(), Temporizador.class);
            startActivity(intent);*/
            sb = new StringBuffer();

            for(FuenteAplicacion app : adaptador.aplicacionesChequeadas){
                sb.append(app.getNombre());
                sb.append("\n");
            }

            if(adaptador.aplicacionesChequeadas.size() > 0){
                Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
            }
            if(adaptador.aplicacionesChequeadas.size() == 0){
                Toast.makeText(this, "Por favor selecciona una opcion para continuar", Toast.LENGTH_SHORT);
            }
        }
    }
}
