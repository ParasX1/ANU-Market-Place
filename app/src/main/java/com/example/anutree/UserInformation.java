package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserInformation extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();
    String name = user.getDisplayName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        TextView email = (TextView) findViewById(R.id.user_email);
        TextView user_greeting = (TextView) findViewById(R.id.hello_user);
        Button message = (Button) findViewById(R.id.user_my_messages);


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainChat.class);
                startActivity(intent);
            }
        });

        email.setText(userEmail);
        user_greeting.setText(name);


    }
}