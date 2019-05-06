package com.example.nodeterministas;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AplicacionViewHolder>{

    List<Aplicacion> aplicaciones;

    RVAdapter(List<Aplicacion> aplicaciones){
        this.aplicaciones = aplicaciones;
    }

    @NonNull
    @Override
    public AplicacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.aplicaciones_cardview, parent, false);
        AplicacionViewHolder avh = new AplicacionViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AplicacionViewHolder holder, int position) {
        holder.nombreApp.setText(aplicaciones.get(position).nombreAplicacion);
        holder.imagenApp.setImageResource(aplicaciones.get(position).imagenAplicacion);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return aplicaciones.size();
    }

    public static class AplicacionViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView imagenApp;
        TextView nombreApp;

        AplicacionViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            imagenApp = (ImageView)itemView.findViewById(R.id.imagen_aplicacion);
            nombreApp = (TextView) itemView.findViewById(R.id.nombre_aplicacion);
        }
    }

}