package com.example.nodeterministas;/*package coml.example.raisasilva.listas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class AdapatadorLista extends BaseAdapter {

    private static LayoutInflater inflater = null; //para instanciar el archico xml que creamos activity_listheader
    Context contexto;
    String[][] datos;
    int[] datosImg;
    private int i;
    public Adapter(Context contexto, String[][] datos, int[] imagenes) {

        this.contexto  = contexto;
        this.datos = datos;
        this.datosImg = imagenes;

        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;

        final View vista = inflater.inflate(R.layout.activity_aplicaiones_lista, null);
        TextView aplicacion =(TextView) vista.findViewById(R.id.nomApp);
        ImageView imagen =(ImageView) vista.findViewById(R.id.ivImagen));
        //Switch elswitch =(switch)  vista.findViewById(R.id.elswitch)); //revisar

        aplicacion.setText(datos[i][0]);
        imagen.setImageResource(datosImg[i]);

       //////// /* imagen.setTag(i);
        imagen.setOnClickListener(new View.OnClickListener();

            @Override
            public void onClick(View view) {
                intent visorImagen = new Intent(contexto, MediaRecorder.VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer) view.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });

       return vista;*///////

   /*  }

    @Override
    public int getCount() {

       // return datosImg;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}*/
