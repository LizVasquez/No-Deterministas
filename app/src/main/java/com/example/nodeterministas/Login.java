package com.example.nodeterministas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button registrate,facebook,google;
    private EditText usuario,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrate = findViewById(R.id.bottom_registrate);
       facebook=findViewById(R.id.button_facebook);
       google=findViewById(R.id.button_google);
       usuario= (EditText)findViewById(R.id.editText_usuaario);
       password=(EditText)findViewById(R.id.editText_password);





    }

    @Override
    public void onClick(View view) {
        if(view.getId()==registrate.getId()){
             VerificarUsuario();

        }
    }

    public void VerificarUsuario (){
        String x = "hola";
        String y = "123";
        String stringUsuario = usuario.getText().toString();
        String stringPassword  = password.getText().toString();


        if (stringUsuario.length()== 0){
            Toast.makeText(Login.this,"Debes ingresar un nombre",Toast.LENGTH_LONG).show();

        }
        if(stringPassword.length()==0) {
            Toast.makeText(Login.this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
        }
        if (stringUsuario.length()!=0 && stringPassword.length() !=0){
           if(stringUsuario.equals(x)==true &&  stringPassword.equals(y)==true){
               Toast.makeText(Login.this,"Bienvenido " + stringUsuario,Toast.LENGTH_LONG).show();

               // Creamos un intent para ir a otro activity
               Intent intent = new Intent(this, Idioma.class);
               startActivity(intent);

           }else {
               Toast.makeText(Login.this,"Datos Incorrectos",Toast.LENGTH_LONG).show();
            }

        }
    }
}