package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerViewl;
    private EditText editMessage;
    private ImageView imgToolbar;
    private ImageView sendButton;
    private TextView txtFriendUser;
    private ProgressBar progressBar;
    private String uIDOfFriend;

    private MessageAdapter messageAdapter;

    private ArrayList<Message> listOfMessages;

    String usernameOfFriend,emailOfFriend,chatRoomId; // for storing past chats and retrieving them later on


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        usernameOfFriend = getIntent().getStringExtra("Username_of_friend"); //get data passed from MainChat
        emailOfFriend = getIntent().getStringExtra("Email_of_friend");
        uIDOfFriend = getIntent().getStringExtra("UID_of_friend");


        recyclerViewl = findViewById(R.id.recyclerChat);
        editMessage = findViewById(R.id.editTxtChat);
        txtFriendUser = findViewById(R.id.txtChattingName);
        progressBar = findViewById(R.id.progressBar4);
        imgToolbar = findViewById(R.id.img_toolbar);
        sendButton = findViewById(R.id.send_button);

        txtFriendUser.setText(usernameOfFriend); //Takes name of chatroom friend and sets it as the name to be displayed

        listOfMessages = new ArrayList<>();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("messages/" +chatRoomId).push()
                        .setValue(new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),emailOfFriend,editMessage.getText().toString()));
                editMessage.setText(""); //empty text again so send new msg
            }
        });


        messageAdapter = new MessageAdapter(listOfMessages, getIntent().getStringExtra("My_pfp"),getIntent().getStringExtra("Pfp_of_friend"),MessageActivity.this);//passes info to the MSg adapter to make msgs
        recyclerViewl.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewl.setAdapter(messageAdapter);
        Glide.with(MessageActivity.this).load(getIntent().getStringExtra("Pfp_of_friend")).placeholder(R.drawable.account_image).error(R.drawable.account_image);
        //Create Msg Object and store it/retrive it
        setUpChatRoomID();
    }

    private void setUpChatRoomID() {
        FirebaseDatabase.getInstance().getReference("User/"+ FirebaseAuth.getInstance()
                .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String myUID = snapshot.getValue(User.class).getFullName();
                if (usernameOfFriend.compareTo(myUID)>0) {  //comparing UIDs to create key
                    chatRoomId = myUID + uIDOfFriend;
                }else if (usernameOfFriend.compareTo(myUID)==0) {
                    chatRoomId = myUID + uIDOfFriend;
                }else {
                    chatRoomId = usernameOfFriend +myUID;
                }
                attachMessageListener(chatRoomId); //adds msgs
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void attachMessageListener(String chatRoomId) { //listener to update msgs to database
        FirebaseDatabase.getInstance().getReference("messages/"+chatRoomId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOfMessages.clear(); //Wipes all msgs to re write it with update
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) { //loops through and adds messages to array
                   listOfMessages.add(dataSnapshot.getValue(Message.class));
                }
                messageAdapter.notifyDataSetChanged(); //to re run/notify
                recyclerViewl.scrollToPosition(listOfMessages.size()-1); //Auto scrolls to bottom of page
                recyclerViewl.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}