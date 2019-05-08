package com.example.nodeterministas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerAplicaciones extends RecyclerView.Adapter<viewHolder>{

    List<FuenteAplicacion> ListaObjeto;
    ArrayList<FuenteAplicacion> aplicacionesChequeadas = new ArrayList<>();

    public AdaptadorRecyclerAplicaciones(List<FuenteAplicacion> listaObjeto) {
        ListaObjeto = listaObjeto;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.aplicaciones_cardview, parent, false);
        return new viewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.nombreApp.setText(ListaObjeto.get(position).getNombre());
        holder.imgApp.setImageResource(ListaObjeto.get(position).getImagenAplicacion());

        holder.setOnClickItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Switch sw = (Switch) v;
                //Verificar si el switch está encendido o no
                if(sw.isChecked()){
                    aplicacionesChequeadas.add(ListaObjeto.get(pos));
                }else if(!sw.isChecked()){
                    aplicacionesChequeadas.remove(ListaObjeto.get(pos));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaObjeto.size();
    }
}
