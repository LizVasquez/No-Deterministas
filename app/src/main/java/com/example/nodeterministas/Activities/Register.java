package com.example.nodeterministas.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nodeterministas.BloquearAplicaciones;
import com.example.nodeterministas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register extends AppCompatActivity {

    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    private EditText userEmail, userPassword, userPAssword2, userName;
    private ProgressBar loadingProgress;
    private Button regBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ini views
        userEmail = findViewById(R.id.reg_mail);
        userPassword = findViewById(R.id.reg_password);
        userPAssword2 = findViewById(R.id.reg_password2);
        userName = findViewById(R.id.reg_name);
        loadingProgress = findViewById(R.id.progress_bar);
        regBtn = findViewById(R.id.reg_btn);
        loadingProgress.setVisibility(View.INVISIBLE);


        mAuth = FirebaseAuth.getInstance();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);

                //final indica que a esa variable solo se le puede asignar
                // un valor u objeto una única vez
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPAssword2.getText().toString();
                final String name = userName.getText().toString();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)) {



                     // algo sale mal: todos los campos deben ser rellenados
                    // necesitamos mostrar un mensaje de error

                    showMessage("Por favor llene todos los campos");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);


                } else {

                    // todo está bien y todos los campos están completos ahora podemos comenzar a crear una cuenta de usuario
                    // El método CreateUserAccount intentará crear el usuario si el correo electrónico es válido
                    CreateUserAccount(email, name, password);
                }


            }
        });

        ImgUserPhoto = findViewById(R.id.regUserPhoto);

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestForPermission();


                }
                else
                {
                    openGallery();
                }





            }
        });


    }


    private void CreateUserAccount(String email, final String name, String password) {

      // este método crea una cuenta de usuario con un correo
       // electrónico y contraseña específicos

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                             // cuenta de usuario creada exitosamente
                            showMessage("Cuenta creada");

                            // después de crear la cuenta de usuario, necesitamos actualizar su foto de perfil y su nombre
                            updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());


                        } else {
                            //la creación de la cuenta falló
                            showMessage("la creación de la cuenta falló" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);

                        }
                    }
                });


    }


// actualizar foto y nombre del usuario

    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // primero tenemos que subir la foto del usuario al almacenamiento firebase y obtener url

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // imagen cargada exitosamente
                // ahora podemos obtener nuestra imagen url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                     // uri contiene la url de la imagen del usuario


                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profleUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            // user info updated successfully
                                            showMessage("Registro completo");
                                            updateUI();
                                        }

                                    }
                                });

                    }
                });


            }
        });


    }

    private void updateUI() {

        Intent Bienvenidos = new Intent(getApplicationContext(), BienvenidosMenu.class);
        startActivity(Bienvenidos);
        finish();


    }

    // metodo para mostrar mensajes
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        //cambiar imagen
        galleryIntent.setType("image/*");
        //estoy llamando a una segunda actividad de la actividad principal
        startActivityForResult(galleryIntent, REQUESCODE);

    }


    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                // los usuarios conceden permisos
                // a la app mientras se ejecutan
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);

            }
        }
        else
            openGallery();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {


             // el usuario ha elegido exitosamente una imagen
            // Necesitamos guardar su referencia a una variable Uri
            pickedImgUri = data.getData();
            //para cargar la imagen que escogimos
            ImgUserPhoto.setImageURI(pickedImgUri);


        }


    }
}
