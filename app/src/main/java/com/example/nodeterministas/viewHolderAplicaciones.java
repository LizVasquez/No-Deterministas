package com.example.nodeterministas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class viewHolderAplicaciones extends RecyclerView.ViewHolder {

    TextView nombreOtraApp;
    Switch switchOtraAplicacion;

    public viewHolderAplicaciones(View itemView) {
        super(itemView);
        nombreOtraApp = (TextView)itemView.findViewById(R.id.texto_otraAplicacion);
        switchOtraAplicacion = (Switch)itemView.findViewById(R.id.otraAplicacion_switch);
    }
}
