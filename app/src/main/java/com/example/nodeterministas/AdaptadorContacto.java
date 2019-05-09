package com.example.nodeterministas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AdaptadorContacto extends RecyclerView.Adapter<viewHolderContacto> {

    List<FuenteContactos> listaContactos;

    public AdaptadorContacto(List<FuenteContactos> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override


    public viewHolderContacto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_cardview, parent, false);
        return new viewHolderContacto(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderContacto holder, int position) {
        holder.nombreContacto.setText(listaContactos.get(position).getNombreContacto());
        holder.numeroContacto.setText(listaContactos.get(position).getNumeroContacto());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }
}
