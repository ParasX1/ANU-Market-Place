package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
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
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView signUp; //Signup button initialisation
    private EditText editEmail,editPassword;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        signUp = (TextView) findViewById(R.id.Register);
        signUp.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.SignInButton);
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
            case R.id.SignInButton:
                startActivity(new Intent(this,registerUser.class));
                loginFunction();
                break;
        }
    }

    private void loginFunction() {
        String email = editEmail.getText().toString().trim(); // Check for valid email
        String password = editPassword.getText().toString().trim(); // Check for Password

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()) {
            editEmail.setError("Email isn't Valid");
            editEmail.requestFocus();
            return;
        }
        if(password.length() < 6) {
            editPassword.setError("Password is invalid");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }else {
                    Toast.makeText(MainActivity.this,"Failed to Login", Toast.LENGTH_LONG) .show();
                }
            }
        });
    }
}