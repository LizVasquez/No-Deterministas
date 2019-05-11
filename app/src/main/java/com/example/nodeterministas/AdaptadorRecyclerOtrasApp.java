package com.example.nodeterministas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.nodeterministas.Models.FuenteOtrasAplicaciones;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerOtrasApp extends RecyclerView.Adapter<viewHolderAplicaciones> {

    List<FuenteOtrasAplicaciones> ListaOtrasApp;
    ArrayList<FuenteOtrasAplicaciones> checkedApps = new ArrayList<>();

    public AdaptadorRecyclerOtrasApp(List<FuenteOtrasAplicaciones> listaOtrasApp) {
        ListaOtrasApp = listaOtrasApp;
    }

    @NonNull
    @Override
    public viewHolderAplicaciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.aplicacionesinstaladas_cardview, parent, false);
        return new viewHolderAplicaciones(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderAplicaciones holder, int position) {
        holder.nombreOtraApp.setText(ListaOtrasApp.get(position).getNombreOtraApp());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Switch sw = (Switch)v;
                //Revisar si el switch esta activado
                if(sw.isChecked()){
                    checkedApps.add(ListaOtrasApp.get(pos));
                }else if(!sw.isChecked()){
                    checkedApps.remove(ListaOtrasApp.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaOtrasApp.size();
    }
}
