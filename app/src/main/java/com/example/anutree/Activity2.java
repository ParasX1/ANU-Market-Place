package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Activity2 extends AppCompatActivity {

//    private String[] item_description = {"belt", "car", "pc", "dog"};
//        private final ArrayList<String> item_description = new ArrayList<String>(Arrays.asList("belt", "car", "pc", "hat", "jeans", "puffer", "mac", "monitor"));
//    private String[] item_description = {"belt"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button appearance = findViewById(R.id.trans_button_activity2);
        appearance.setClickable(false);


//        final ArrayList<Posts> item_description = getDatabaseData(); // get database entries

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

        Log.d("uhm", "THIS SHOUDL BE DESIPLASDYD");
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
                        Posts post = new Posts(title, price, likes, desc, uid, p_uri, author);
                        Log.d("uhm", post.toString());
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

                            Object please = parent.getAdapter().getItem(position);

                            Log.d("uhm", Integer.toString(position));
//                Log.d("uhm", please.toString());
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



}
