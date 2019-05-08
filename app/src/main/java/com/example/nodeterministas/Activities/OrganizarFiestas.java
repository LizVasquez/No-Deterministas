package com.example.nodeterministas.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.nodeterministas.Models.Post;
import com.example.nodeterministas.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class OrganizarFiestas extends AppCompatActivity {


    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Dialog popAddPost;
    ImageView popupUserImage, popupPublicationImage, popupAddBton;
    TextView popupEditTextTitle, popupEditTextFecha, popupEditTextBebidas, popupEditTextMusica, popupEditTextDescription;
    ProgressBar popupClickProgress;
    private Uri pickedImgUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar_fiestas);


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        iniPopup();
        setupPopupImageClick();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.añadir_publicacion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popAddPost.show();
            }
        });
    }

    private void setupPopupImageClick() {


        //  necesitamos abrir la galería
        // antes de abrir la galería, debemos comprobar si nuestra aplicación tiene acceso a los archivos de usuario
        // ncesitamos un permiso
        popupPublicationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAndRequestForPermission();


            }
        });

    }

    private void iniPopup() {

        popAddPost = new Dialog(this);
        popAddPost.setContentView(R.layout.popup_add_post);
        popAddPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddPost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddPost.getWindow().getAttributes().gravity = Gravity.CENTER;


        popupUserImage = popAddPost.findViewById(R.id.popup_user_image);
        popupPublicationImage = popAddPost.findViewById(R.id.popup_img_add);
        popupEditTextTitle = popAddPost.findViewById(R.id.popup_title);
//        popupEditTextFecha = popAddPost.findViewById(R.id.popup_fecha);
//
//        popupEditTextBebidas = popAddPost.findViewById(R.id.popup_bebidas);
//        popupEditTextMusica = popAddPost.findViewById(R.id.popup_musica);
        popupEditTextDescription = popAddPost.findViewById(R.id.popup_description);
        popupAddBton = popAddPost.findViewById(R.id.popup_bton_add);
        popupClickProgress = popAddPost.findViewById(R.id.popup_progressBar);


// cargar foto de perfil de usuario actual

        Glide.with(this).load(currentUser.getPhotoUrl()).into(popupUserImage);


// al presionar el boton añadir debe aparecer el progres bar
        popupAddBton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupAddBton.setVisibility(View.INVISIBLE);
                popupClickProgress.setVisibility(View.VISIBLE);

                // necesitamos probar todos los campos de entrada  y publicar la imagen

                if (!popupEditTextTitle.getText().toString().isEmpty()
                        && !popupEditTextDescription.getText().toString().isEmpty()
                        && pickedImgUri!= null
                ) {
                    // todo esta bien sin valor vacío o nulo
                    // TODO Crear objeto de publicación y agregarlo a la base de datos de firebase
                    // primero tenemos que subir la imagen de la publicación
                    // acceder al almacenamiento firebase
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("imagenes_publicadas");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();

                                    // crear Object

                                    Post post = new Post(popupEditTextTitle.getText().toString(),
                                            popupEditTextDescription.getText().toString(),
                                            imageDownlaodLink,
                                            currentUser.getUid(),
                                            currentUser.getPhotoUrl().toString());
                                    //
                                    // Añadir publicación a la base de datos de fireBase
                                    addPost(post);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // si algo va mal al subir la imagen

                                    showMessage("Ocurrio un error");
                                    popupClickProgress.setVisibility(View.INVISIBLE);
                                    popupAddBton.setVisibility(View.VISIBLE);

                                }
                            });
                        }
                    });

                } else {
                    showMessage("Please verify all input fields and choose Post Image");
                    popupAddBton.setVisibility(View.VISIBLE);
                    popupClickProgress.setVisibility(View.INVISIBLE);

                }


            }
        });


    }

    private void addPost(Post post) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Publicaciones").push();
        // obtener ID única de publicación y upadte clave de publicación

        String key = myRef.getKey();
        post.setPostKey(key);

        // agregar datos de publicación a la base de datos de base de fuego
        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("Exitoso");
                popupClickProgress.setVisibility(View.INVISIBLE);
                popupAddBton.setVisibility(View.VISIBLE);
                popAddPost.dismiss();
            }
        });


    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //El usuario escogio imagen
    void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                // los usuarios conceden permisos
                // a la app mientras se ejecutan
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);

            }
        } else
            // tenemos permiso de acceder a galeria
            openGallery();

    }

    private void openGallery() {


        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        //cambiar imagen
        galleryIntent.setType("image/*");
        //estoy llamando a una segunda actividad de la actividad principal
        startActivityForResult(galleryIntent, REQUESCODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {


            // el usuario ha elegido exitosamente una imagen
            // Necesitamos guardar su referencia a una variable Uri
            pickedImgUri = data.getData();
            popupPublicationImage.setImageURI(pickedImgUri);


        }

    }

}
