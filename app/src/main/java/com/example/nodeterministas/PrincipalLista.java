package com.example.nodeterministas;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class PrincipalLista extends AppCompatActivity {

    ListView lista;

    String[][] datos = {
            {"Facebook"},
            {"WhatsApp"},
            {"Instagram"},
            {"Messenger"}

    };

/*    int[] datosImag = {R.drawable.beer,R.drawable.beer,R.drawable.beer,R.drawable.beer};
    @Override

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_header);

       lista = (ListView) findViewById((R.id.lvLi));
        lista.setAdapter(new AdapatadorLista(this, datos, datosImag));
    }*/
}

