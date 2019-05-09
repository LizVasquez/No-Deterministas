package com.example.nodeterministas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class viewHolderAplicaciones extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nombreOtraApp;
    Switch switchOtraAplicacion;

    ItemClickListener itemClickListener;

    public viewHolderAplicaciones(View itemView) {
        super(itemView);
        nombreOtraApp = (TextView)itemView.findViewById(R.id.texto_otraAplicacion);
        switchOtraAplicacion = (Switch)itemView.findViewById(R.id.otraAplicacion_switch);

        switchOtraAplicacion.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());
    }
}
