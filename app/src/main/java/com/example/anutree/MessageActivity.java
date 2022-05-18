package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerViewl;
    private EditText editMessage;
    private ImageView pfp;
    private ImageView sendButton;
    private TextView txtFriendUser;
    private ProgressBar progressBar;

    private ArrayList<Message> listOfMessages;

    String usernameOfFriend,emailOfFriend,chatRoomId; // for storing past chats and retriving them later on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        usernameOfFriend = getIntent().getStringExtra("Username_of_friend");
        emailOfFriend = getIntent().getStringExtra("Email_of_friend");


        recyclerViewl = findViewById(R.id.recyclerChat);
        editMessage = findViewById(R.id.editTxtChat);
        txtFriendUser = findViewById(R.id.txtChattingName);
        progressBar = findViewById(R.id.progressBar4);
        pfp = findViewById(R.id.img_toolbar);
        sendButton = findViewById(R.id.send_button);

        txtFriendUser.setText(usernameOfFriend); //Takes name of chatroom friend and sets it as the name to be displayed


        listOfMessages = new ArrayList<>();



        //Create Msg Object and store it/retrive it


    }
}