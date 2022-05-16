package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class registerUser extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth; //Creating required fields/objects
    private TextView banner;
    private TextView registeredUser;
    private EditText editFullName;
    private EditText editUID;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance(); //Firebase method

        banner = (TextView) findViewById(R.id.registerbanner);
        banner.setOnClickListener(this); //Banner click take back to home page

        registeredUser = (TextView) findViewById(R.id.registerButton);
        registeredUser.setOnClickListener(this); //Register click do something

        editFullName = (EditText) findViewById(R.id.fullName); //Initialise the variables
        editUID = (EditText) findViewById(R.id.uID);
        editEmail = (EditText) findViewById(R.id.registerEmail);
        editPassword = (EditText) findViewById(R.id.registerPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar); // Initialise the loading bar

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
            case R.id.registerButton:
                RegisterUser();
        }
    }

    private void RegisterUser() {
        String email = editEmail.getText().toString().trim(); // Check for valid email
        String uID = editUID.getText().toString().trim(); // Check for ID 
        String password = editPassword.getText().toString().trim(); // Check for Password
        String fullName = editFullName.getText().toString().trim(); // Check for Name

        if(fullName.isEmpty()) {
            editFullName.setError("Oops, forgot to give me a Full Name?");
            editFullName.requestFocus();
        }
        if(!(uID.length() == 7)) {
            editUID.setError("Invalid UID");
            editUID.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()) {
            editEmail.setError("Email isn't Valid");
        }
        if(password.length() < 6 || password.isEmpty()) {
            editPassword.setError("Password is invalid");
        }else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                User user = new User(fullName,uID,email,password,"");
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(registerUser.this, "Successful Signup!",Toast.LENGTH_LONG).show();
                                            //AFter this redirects to homepage
                                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(i);
                                        }else {
                                            Toast.makeText(registerUser.this, "Failed Signup, try again",Toast.LENGTH_LONG).show();
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                            }else {
                                Toast.makeText(registerUser.this, "Failed Signup, try again",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }




    }


}