package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    private EditText editEmail;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
        editEmail = (EditText) findViewById(R.id.forgotEmail); //Initialise the variables
        resetPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar); // Initialise the loading bar

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswordFunction();
            }
        });


    }

    private void resetPasswordFunction() {
        String email = editEmail.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()) {
            editEmail.setError("Email isn't Valid");
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(forgotPassword.this, "Sent Reset Email!",Toast.LENGTH_LONG).show();
                    //AFter this redirects to homepage
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(forgotPassword.this, "Failed Reset, try again",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}