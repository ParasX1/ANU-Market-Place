package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

public class savedPosts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_posts);
        getDatabaseData();

    }

    private void getDatabaseData() {
        ProgressBar bar = (ProgressBar) findViewById(R.id.progress_bar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Posts> postList = new ArrayList<>(); // initialise list
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        List<String> saved = new ArrayList<>();
        List<String> savedAuthor = new ArrayList<>();

//        Log.d("uhm", "THIS SHOULD BE DISPLAYED");
        db.collection("posts").whereNotEqualTo("likes", -1).limit(30).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference().child("User")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        User user = snapshot.getValue(User.class);
                                        if(user != null && user.getEmail() != null){
                                            if(user.getEmail().equalsIgnoreCase(currentUser.getEmail())){
                                                for(int i = 0; i < user.savedPosts.size(); i++){
                                                    saved.add(user.savedPosts.get(i));
                                                }
                                                for(int i = 0; i < user.savedPostsAuthor.size(); i++){
                                                    if(user.savedPostsAuthor != null){
                                                        savedAuthor.add(user.savedPostsAuthor.get(i));
                                                    }
                                                }

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
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d("uhm",document.toObject(Posts.class).toString());
//                        Log.d("uhm",document.getData().toString());


                        Map<String, Object> p = document.getData();
//                        Log.d("uhm",p.get("title").toString());

                        Uri p_uri = Uri.parse(((String) p.get("imageURL")));
                        String title = ((String) p.get("title"));
                        String author = ((String) p.get("author_id"));
                        Float price = ((Double) p.get("price")).floatValue();
                        int likes = (int) ((long) p.get("likes"));
                        String uid = ((String) p.get("uid")); // university id of author
                        String desc = ((String) p.get("description"));
                        String name = (String) p.get("name");
                        Posts post = new Posts(title, price, likes, desc, author,name, p_uri);
//                        Log.d("uhm", post.toString());
                        // add post to arraylist


                        postList.add(post);









                    }
                    ArrayList<Posts> saved_Posts = new ArrayList<>();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    bar.setVisibility(View.GONE);
                                    for(Posts post: postList){
                                        if(saved.contains(post.title)){
                                            if(savedAuthor.contains(post.author_id) && post.equals(post)){
                                                saved_Posts.add(post);
                                            }

                                        }
                                    }

                                    GridView gridView = (GridView) findViewById(R.id.item_grid);

                                    ImageAdapter2 adapter = new ImageAdapter2(getApplicationContext(), saved_Posts);
                                    gridView.setAdapter(adapter);

                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                            Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.description)).getText(), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), PostListingActivity.class);

                                            Posts post = (Posts) parent.getItemAtPosition(position);
//                            intent.putExtra("the post",post);
                                            intent.putExtra("title", post.title);
                                            intent.putExtra("price", post.price);
                                            intent.putExtra("likes", post.likes);
                                            intent.putExtra("author_id", post.author_id); // university id
                                            intent.putExtra("description", post.description);
                                            intent.putExtra("imageUrl", post.imageURL);
                                            intent.putExtra("uid", post.uid);
                                            intent.putExtra("name", post.name);
                                            // cant pass in a Posts object as something wrong with parcelable

                                            startActivity(intent);





                                        }
                                    });

                                }
                            });


                        }
                    }, 7000);



                } else {
                    Log.d("uhm", "Not workking", task.getException());

                }

            }
        }); // make it not equals null or something


//        return postList;

    }



}