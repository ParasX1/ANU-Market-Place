package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class PostListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);
        List<String> saved = new ArrayList<>();
        List<String> savedAuthor = new ArrayList<>();
        String uid;

        Intent intent = getIntent();
        String PostTitle = intent.getStringExtra("title");
        String PostAuthor = intent.getStringExtra("author_id");

//        Toast.makeText(PostListingActivity.this,"test", Toast.LENGTH_SHORT).show();
        Button Save_post = (Button) findViewById(R.id.save_post);

        Save_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostListingActivity.this,"test", Toast.LENGTH_SHORT).show();
                Notification n = new Notification.Builder(PostListingActivity.this)
                        .setContentTitle("Test")
                        .setContentText("Hello world")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, n);
                savePost();
            }

            private void savePost() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String Current_email = currentUser.getEmail();

                FirebaseDatabase.getInstance().getReference().child("User")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    User user = snapshot.getValue(User.class);
                                    if(user != null && user.getEmail() != null){
                                        if(user.getEmail().equalsIgnoreCase(Current_email)){
                                            for(int i = 0; i < user.savedPosts.size(); i++){
                                                saved.add(user.savedPosts.get(i));
                                            }

                                            for(int i = 0; i < user.savedPostsAuthor.size(); i++){
                                                if(user.savedPostsAuthor.get(i) != null){
                                                    savedAuthor.add(user.savedPostsAuthor.get(i));
                                                }

                                            }
                                            saved.add(PostTitle);
                                            savedAuthor.add(PostAuthor);
                                            break;
                                        }
                                    }


                                }
                                FirebaseDatabase.getInstance().getReference().child("User").child(currentUser.getUid()).child("savedPosts").setValue(saved);
                                FirebaseDatabase.getInstance().getReference().child("User").child(currentUser.getUid()).child("savedPostsAuthor").setValue(savedAuthor);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

            }

        });

        ViewPager viewPager = findViewById(R.id.pager);
        Intent post_data = getIntent();
//        Posts post = post_data.getParcelableExtra("the post");
        // something wrong with parcelable implementation
        String post_title = post_data.getStringExtra("title");
        Float post_price = post_data.getFloatExtra("price",0);
        int post_likes = post_data.getIntExtra("likes",0);
        String post_author = post_data.getStringExtra("author_id");
        String post_desc = post_data.getStringExtra("description");
        Uri post_image_url = post_data.getParcelableExtra("imageUrl");
        String post_uid = post_data.getStringExtra("uid"); // university id of author
        String post_name = post_data.getStringExtra("name");
//        Log.d("uhm",post_price.toString() + "is price");
        Posts this_post = new Posts(post_title,post_price,post_likes,post_desc,post_uid,post_name,post_image_url);

        setTitle(post_title);

        // Set Adapter
        Adapter adapter = new Adapter(this,post_image_url);
        viewPager.setAdapter(adapter);
        TextView title = findViewById(R.id.listingtitle);
        TextView author = findViewById(R.id.author);
        TextView price = findViewById(R.id.price);
        TextView description = findViewById(R.id.desc_content);
        TextView uid_view = findViewById(R.id.uid_view);

        title.setText(post_title + "                " + post_likes + " likes");
        author.setText(post_name);
        uid_view.setText("Uid: u" + post_author); // university id of user
        price.setText(post_price.toString());

        description.setText("Description:  \n" + post_desc);




    }

}