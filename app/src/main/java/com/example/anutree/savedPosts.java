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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class savedPosts extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button appearance = findViewById(R.id.trans_button_activity2);
        appearance.setClickable(false);

        getDatabaseData();


        Button signOut = findViewById(R.id.signOut_button);  //SignOut implementations using Firebase .signout method
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // This is where the parser and tokenizer stuff is goes i guess???
                if (s.equals("cat")){ // just some random test
                    Intent intent = new Intent(getApplicationContext(), MainChat.class);
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        };

        SearchView search = (SearchView) findViewById(R.id.Search_bar);
        search.setOnQueryTextListener(queryTextListener);


        Button message = findViewById(R.id.button_car);  // MESSAGING APP CUZ WE DONT HAVE A BUTTON YET
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent i = new Intent(getApplicationContext(), MainChat.class);
                startActivity(i);
            }
        });


    }



    public void onMoreCategory(View view) {
        Intent intent = new Intent(getApplicationContext(), MoreCategoriesActivity.class);
        startActivity(intent);

    }


    private void getDatabaseData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Posts> postList = new ArrayList<>(); // initialise list

//        Log.d("uhm", "THIS SHOULD BE DISPLAYED");
        db.collection("posts").whereNotEqualTo("likes", -1).limit(30).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d("uhm",document.toObject(Posts.class).toString());
//                        Log.d("uhm",document.getData().toString());
                        Map<String, Object> p = document.getData();
//                        Log.d("uhm",p.get("title").toString());

                        Uri p_uri = Uri.parse(((String) p.get("imageURL")));
                        String title = ((String) p.get("title"));
                        String author = ((String) p.get("author"));
                        Float price = ((Double) p.get("price")).floatValue();
                        int likes = (int) ((long) p.get("likes"));
                        String uid = ((String) p.get("uid"));
                        String desc = ((String) p.get("description"));
                        String name = (String) p.get("name");
                        Posts post = new Posts(title, price, likes, desc, uid, name, p_uri);
//                        Log.d("uhm", post.toString());
                        // add post to arraylist
                        postList.add(post);

                    }
                    // all this stuff was originally inside the oncreate method
                    GridView gridView = (GridView) findViewById(R.id.item_grid);

                    ImageAdapter adapter = new ImageAdapter(getApplicationContext(), postList);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.description)).getText(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), PostListingActivity.class);

                            Posts post = (Posts) parent.getItemAtPosition(position);
                            intent.putExtra("title", post.title);
                            intent.putExtra("price", post.price);
                            intent.putExtra("likes", post.likes);
                            intent.putExtra("author", post.author_id);
                            intent.putExtra("description", post.description);
                            intent.putExtra("imageUrl", post.imageURL);
                            intent.putExtra("uid", post.uid);
                            intent.putExtra("name",post.name);
                            // cant pass in a Posts object as something wrong with parcelable

                            startActivity(intent);
                        }
                    });

                    Button post_ad = findViewById(R.id.create_ad_button);
                    post_ad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent goto_create_post = new Intent(getApplicationContext(), CreatePostActivity.class);
                            startActivity(goto_create_post);
                            gridView.invalidateViews();

                        }
                    });
                } else {
                    Log.d("uhm", "Not workking", task.getException());
                }

            }
        }); // make it not equals null or something

//        return postList;
    }


    public void on_more_info(View view) {
        Intent to_user_info = new Intent(getApplicationContext(), UserInformation.class);
        // perhaps transfer information in this intent if needed
        startActivity(to_user_info);
    }
}