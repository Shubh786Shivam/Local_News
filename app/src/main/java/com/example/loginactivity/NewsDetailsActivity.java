package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    TextView titleTextView, author, multilineArticle, dateAdded;
    ImageView thumbnailImage;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        titleTextView = findViewById(R.id.titleTextView);
        author = findViewById(R.id.author);
        multilineArticle = findViewById(R.id.multilineArticle);
        dateAdded = findViewById(R.id.dateAdded);

        thumbnailImage = findViewById(R.id.thumbnailImage);
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        Intent intent = getIntent();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                   if(dataSnapshot.getKey().equals(intent.getStringExtra("Path"))){
                       Uploads uploads = dataSnapshot.getValue(Uploads.class);
                       titleTextView.setText(uploads.getTitle());
                       author.setText(uploads.getAuthor());
                       multilineArticle.setText(uploads.getMultiLineText());
                       dateAdded.setText(uploads.getCurrentDate());
                       Picasso.with(NewsDetailsActivity.this).load(uploads.getImageUrl()).into(thumbnailImage);
                   }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NewsDetailsActivity.this, "Error While Fetching!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}