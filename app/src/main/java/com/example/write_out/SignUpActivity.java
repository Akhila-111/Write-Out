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

public class SignUpActivity extends AppCompatActivity {

    EditText signUpEmail;
    EditText signUpPassword;
    Button LoginHere;
    Button btnRegister;
    FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_file);

        signUpEmail = findViewById(R.id.editTextTextEmailAddress2);
        signUpPassword = findViewById(R.id.editTextTextPassword2);
        LoginHere = findViewById(R.id.button5);
        btnRegister = findViewById(R.id.button4);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            createUser();
        });

        LoginHere.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        });
    }

    private void createUser(){
        String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            signUpEmail.setError("Email cannot be empty");
            signUpEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            signUpPassword.setError("Password cannot be empty");
            signUpPassword.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"User Registered Successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Registration Error" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
                }
            });
        }
    }
}