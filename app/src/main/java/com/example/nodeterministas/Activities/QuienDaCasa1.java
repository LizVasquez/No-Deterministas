package com.example.nodeterministas.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nodeterministas.Adapters.PostAdapter;
import com.example.nodeterministas.Models.Post;
import com.example.nodeterministas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuienDaCasa1 extends AppCompatActivity {

    RecyclerView postRecyclerView;
    PostAdapter postAdapter;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
    List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quien_da_casa1);

//         Obtengo la referencia de mi RecyclerView del Layout
//
        postRecyclerView = findViewById(R.id.post_recycler_view1);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Publicaciones");

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Obtener listas de publicaciones de la base de datos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postList = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    Post post = postsnap.getValue(Post.class);
                    postList.add(post) ;



                }

                postAdapter = new PostAdapter(QuienDaCasa1.this,postList);
                postRecyclerView.setAdapter(postAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}
