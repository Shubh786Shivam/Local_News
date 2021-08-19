package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AuthoriseActivity extends AppCompatActivity{
    private RecyclerView recViewAuth;
    private ListArticleAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise);
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");
        createArticleSummaryList();
        buildListRecyclerView();

    }
    public void insertItem(int pos){

    }
    public void removeItem(int pos){

    }
    public void createArticleSummaryList(){
        //Getting Database from Realtime

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<listArticleSummaryItem> list = new ArrayList<>();
                ArrayList<String> paths = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Uploads uploads = dataSnapshot.getValue(Uploads.class);
                    if(uploads.getStatus().equals("new")){
                        paths.add(dataSnapshot.getKey());
                        list.add(new listArticleSummaryItem(uploads.getMultiLineText()));
                    }
                }
                //Setting Recycler View
                recViewAuth = findViewById(R.id.recViewAuth);
                recViewAuth.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(AuthoriseActivity.this);
                adapter = new ListArticleAdapter(AuthoriseActivity.this, list);
                recViewAuth.setLayoutManager(layoutManager);
                recViewAuth.setAdapter(adapter);
                adapter.setOnItemClickListener(new ListArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            Uploads uploads = dataSnapshot.getValue(Uploads.class);
                            if(dataSnapshot.getKey().equals(paths.get(position))){
                                uploads.setStatus("authorised");
                                databaseReference.child(paths.get(position)).setValue(uploads);
                            }

                        }
                        list.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void buildListRecyclerView(){

    }

}