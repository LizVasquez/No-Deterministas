package com.example.nodeterministas.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nodeterministas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button registrate, facebook, google, iniciarSesion;
    private EditText mail, password;
    private ProgressBar loginProgress;
    private FirebaseAuth fbAuth;
    //contraseña
    String x = "1";
    String y = "1";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //edit text
        mail = findViewById(R.id.editText_usuaario);
        password = findViewById(R.id.editText_password);
        //botones
        registrate = findViewById(R.id.bottom_registrate);
        iniciarSesion = findViewById(R.id.bottom_iniciar_sesion);
        facebook = findViewById(R.id.button_facebook);
        google = findViewById(R.id.button_google);
        loginProgress = findViewById(R.id.progress_bar2);

        //En el método onCreate de tu actividad de acceso, obtén la instancia compartida del objeto FirebaseAuth
        fbAuth = FirebaseAuth.getInstance();
        loginProgress.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == iniciarSesion.getId()) {
            Verificarmail();

        }
        if (view.getId() == registrate.getId()) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }

    public void Verificarmail() {
        final String stringmail = mail.getText().toString();
        final String stringPassword = password.getText().toString();
        mostrarProgressBar();

        if (stringmail.length() == 0) {
            showMessage("Debes ingresar un nombre");
            ocultarProgressBar();


        }
        if (stringPassword.length() == 0) {
            showMessage("Debes ingresar una contraseña");
            ocultarProgressBar();

        }
       else {
               signIn(stringmail,stringPassword);
            }
        }



    //Cuando un usuario acceda a la app, pasa la dirección de correo
    // electrónico y la contraseña a signInWithEmailAndPassword:
    private void signIn(final String mail, String password) {

        fbAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    showMessage("INGRESASTE");
                    mostrarProgressBar();
                    updateUI();

                }
                else {
                    showMessage("Los Datos No son Correctos");
                    ocultarProgressBar();
                }


            }
        });



    }



    private void updateUI() {

        Intent Bienvenidos = new Intent(getApplicationContext(), BienvenidosMenu.class);
        startActivity(Bienvenidos);
        finish();
    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }




    //Cuando inicies la actividad, verifica que el usuario haya accedido:
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = fbAuth.getCurrentUser();

        if (user != null) {
            // el usuario ya está conectado, así que debemos redirigirlo a la página de inicio
            updateUI();

        }


    }
    public void mostrarProgressBar(){
        loginProgress.setVisibility(View.VISIBLE);
        registrate.setVisibility(View.INVISIBLE);
        google.setVisibility(View.INVISIBLE);
        facebook.setVisibility(View.INVISIBLE);
        iniciarSesion.setVisibility(View.INVISIBLE);
    }
    public void ocultarProgressBar(){
        loginProgress.setVisibility(View.INVISIBLE);
        registrate.setVisibility(View.VISIBLE);
        google.setVisibility(View.VISIBLE);
        facebook.setVisibility(View.VISIBLE);
        iniciarSesion.setVisibility(View.VISIBLE);
    }

    }
