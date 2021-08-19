package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AuthoriseArticleUserAdmin extends AppCompatActivity {
    Button authoriseArticlesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise_article_user_admin);

        authoriseArticlesButton = findViewById(R.id.authoriseArticlesButton);
    }
    public void authoriseListSummary(View view){
        Intent intent = new Intent(AuthoriseArticleUserAdmin.this, AuthoriseActivity.class);
        startActivity(intent);
    }
}