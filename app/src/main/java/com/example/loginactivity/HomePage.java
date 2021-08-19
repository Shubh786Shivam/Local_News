package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    Button login;
    private RecyclerView recView;
    private HomePageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        login = findViewById(R.id.login);
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");
        createHomePageItemList();
        buildRecyclerView();
    }
    public void insertItem(int pos){

    }
    public void removeItem(int pos){

    }
    public void takeToLogin(View view){
        Intent intent  = new Intent(HomePage.this, LoginActivity.class);
        startActivity(intent);
    }
    public void createHomePageItemList(){
        //Getting Database from Realtime
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<homeItem> homeItemArrayList = new ArrayList<>();
                ArrayList<String> paths = new ArrayList<String>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Uploads uploads = dataSnapshot.getValue(Uploads.class);
                    if(uploads.getStatus().equals("authorised")){
                        homeItemArrayList.add(new homeItem(uploads.getImageUrl(), uploads.getTitle(), uploads.getCurrentDate()));
                        paths.add(dataSnapshot.getKey());
                    }
                }
                recView = findViewById(R.id.recView);
                recView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(HomePage.this);
                adapter = new HomePageAdapter(HomePage.this, homeItemArrayList);
                recView.setLayoutManager(layoutManager);
                recView.setAdapter(adapter);
                adapter.setOnItemClickListener(new HomePageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(HomePage.this, NewsDetailsActivity.class);
                        intent.putExtra("Path", paths.get(position));
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void buildRecyclerView(){
        //Setting Recycler View
    }
}