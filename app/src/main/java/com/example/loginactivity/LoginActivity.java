package com.example.loginactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText EMailLogin;
    EditText passwordLogin;

    Button logIn;
    TextView newUser;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth  = FirebaseAuth.getInstance();
        EMailLogin = findViewById(R.id.EMailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        logIn = findViewById(R.id.logIn);
        newUser = findViewById(R.id.newUser);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EMailLogin.getText().toString().trim();
                String pass = passwordLogin.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    EMailLogin.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    passwordLogin.setError("Password is required!");
                    return;
                }

                if(pass.length() < 6){
                    passwordLogin.setError("Pasword should be of atleast length 6!");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            if(email.equals("shikhar@gmail.com")){
                                startActivity(new Intent(LoginActivity.this, AuthoriseArticleUserAdmin.class));
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this, AddNewsUserLoggedIn.class));
                            }

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}