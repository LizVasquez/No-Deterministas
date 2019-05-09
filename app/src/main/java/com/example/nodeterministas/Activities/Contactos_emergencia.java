package com.example.nodeterministas.Activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nodeterministas.R;

public class Contactos_emergencia extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos_emergencia);

        Button abrirCont = (Button) findViewById(R.id.abrirCont);
        final Intent iContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        abrirCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(iContact);

            }
        });
    }
}
