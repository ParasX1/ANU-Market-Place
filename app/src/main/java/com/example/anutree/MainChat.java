package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainChat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UserAdapter userAdapter;
    UserAdapter.OnClickListener onClickListener1;  //Refering to User adapter class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        progressBar = findViewById(R.id.progressBar3);
        users = new ArrayList<>();
        recyclerView =findViewById(R.id.recycler);

        onClickListener1 = new UserAdapter.OnClickListener() {
            @Override
            public void onUserClicked(int position) {
                Toast.makeText(MainChat.this,"Tapped on friend!" +users.get(position).getuID(),Toast.LENGTH_LONG).show();
            }
        };

        Button message = findViewById(R.id.profile_button);  // MESSAGING APP CUZ WE DONT HAVE A BUTTON YET
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MessageProfile.class);
                startActivity(i);
            }
        });
        getUsers();
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile) {
            startActivity(new Intent(MainChat.this,MessageProfile.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    public void onMoreCategory(View view){
        Intent intent = new Intent(getApplicationContext(), MoreCategoriesActivity.class);
        startActivity(intent);
    }

    private void getUsers() { //gets Users
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //gets all users form Firebase
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    users.add(dataSnapshot.getValue(User.class));
                }
                userAdapter = new UserAdapter(users, MainChat.this, onClickListener1);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainChat.this));
                recyclerView.setAdapter(userAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}
