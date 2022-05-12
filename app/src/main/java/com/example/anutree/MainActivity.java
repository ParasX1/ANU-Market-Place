package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.graphics.text.TextRunShaper;
import android.os.Bundle;
import android.preference.EditTextPreference;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth; // Firebase
    private EditText editEmail;
    private EditText editPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //Signup button initialisation
        TextView signUp = (TextView) findViewById(R.id.Register);
        signUp.setOnClickListener(this);

        TextView forgotPass = (TextView) findViewById(R.id.forgotPassword);
        forgotPass.setOnClickListener(this);

        //Signin button initialisation
        Button signIn = (Button) findViewById(R.id.SignInButton);
        signIn.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar); // Initialise the loading bar
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.Register:
                startActivity(new Intent(this,registerUser.class));
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this,forgotPassword.class));
                break;
            case R.id.SignInButton:
                logInUser();

        }
    }

    private void logInUser() {
        String email = editEmail.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editEmail.setError("Oops you havnt given me a email address!");
            editEmail.requestFocus();
        }else if (pass.isEmpty()) {
            editPassword.setError( "Oops you havnt given me a password!");
            editPassword.requestFocus();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Your email doesnt match to any in out database! REGISTER DOWN BELOW");
            editEmail.requestFocus();
        }else if (pass.length() < 6) {
            editPassword.setError("Password too short");
            editPassword.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    //redirect to user profile
                } else if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Failed Login, Try again!", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(MainActivity.this,"Weird Error, Reload App!", Toast.LENGTH_LONG).show();

                }
            }
        });




    }

}