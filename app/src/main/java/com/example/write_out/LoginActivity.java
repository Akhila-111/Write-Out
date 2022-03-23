package com.example.write_out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail;
    EditText loginPassword;
    Button login;
    Button signUp;

    FirebaseAuth mAuth ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_file);

        loginEmail = findViewById(R.id.editTextTextEmailAddress);
        loginPassword = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);
        signUp = findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            loginUser();
        });
        signUp.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
        });

    }

    private void loginUser(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(LoginActivity.this,"User Logged in successfully",Toast.LENGTH_LONG).show();
                       startActivity(new Intent(LoginActivity.this,MainActivity.class));
                   }
                   else {
                       Toast.makeText(LoginActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
}

































































































































