package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNewsUserLoggedIn extends AppCompatActivity {
    Button addNewsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_user_logged_in);
        addNewsButton = findViewById(R.id.addNewsButton);
    }
    public void addNews(View view){
        Intent intent = new Intent(AddNewsUserLoggedIn.this, AddNewsActivity.class);
        startActivity(intent);
    }
}