package com.example.write_out;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity {

    private EditText passEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_file);

        passEmail = findViewById(R.id.et_email);
        resetPassword = findViewById(R.id.bt_forget);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = passEmail.getText().toString().trim();

                if(Email.equals("")){
                    Toast.makeText(PasswordActivity.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();

                }else {
                    firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(PasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(PasswordActivity.this,LoginActivity.class));
                            }else{
                                Toast.makeText(PasswordActivity.this, "Error in sending Password reset email", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
}
