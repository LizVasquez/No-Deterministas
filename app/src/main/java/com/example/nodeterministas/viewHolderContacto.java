package com.example.nodeterministas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class viewHolderContacto extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nombreContacto;
    TextView numeroContacto;
    CheckBox checkBoxContacto;


    public viewHolderContacto(View itemView) {
        super(itemView);

        nombreContacto = itemView.findViewById(R.id.nombre_contacto);
        numeroContacto = itemView.findViewById(R.id.numero_contacto);
        checkBoxContacto = itemView.findViewById(R.id.checkbox_contacto);

        checkBoxContacto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.checkbox_contacto){
            if(checkBoxContacto.isChecked()){
                Toast.makeText(checkBoxContacto.getContext(), "Contacto Añadido", Toast.LENGTH_LONG).show();
            }else if(!checkBoxContacto.isChecked()){
                Toast.makeText(checkBoxContacto.getContext(), "Quitaste el Contacto", Toast.LENGTH_LONG).show();
            }
        }
    }
}
