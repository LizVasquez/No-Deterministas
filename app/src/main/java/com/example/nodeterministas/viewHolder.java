package com.example.nodeterministas;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    Switch switchAplicacion;
    ImageView imgApp;
    TextView nombreApp;

    public viewHolder(View itemView) {
        super(itemView);
        switchAplicacion = (Switch) itemView.findViewById(R.id.switch_aplicacion);
        imgApp = (ImageView) itemView.findViewById(R.id.imagen_aplicacion);
        nombreApp = (TextView) itemView.findViewById(R.id.nombre_aplicacion);

        switchAplicacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.switch_aplicacion){
            if(switchAplicacion.isChecked())
                Toast.makeText(switchAplicacion.getContext(), nombreApp.getText() + " Activado", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(switchAplicacion.getContext(), nombreApp.getText() + " Desactivado", Toast.LENGTH_SHORT).show();
        }
    }

}
