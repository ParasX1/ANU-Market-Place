package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity2 extends AppCompatActivity {

    private String[] item_description = {"belt", "car", "pc", "dog"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button appearance = findViewById(R.id.trans_button_activity2);
        appearance.setClickable(false);


        GridView gridView = (GridView) findViewById(R.id.item_grid);

        gridView.setAdapter(new ImageAdapter(this, item_description));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.description)).getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PostListingActivity.class);
                // somehow pass post data
//                Object please = parent.getItemAtPosition(position);
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
                Intent goto_create_post = new Intent(getApplicationContext(),CreatePostActivity.class);
                startActivity(goto_create_post);
            }
        });


    }


    public void onMoreCategory(View view){
        Intent intent = new Intent(getApplicationContext(), MoreCategoriesActivity.class);
        startActivity(intent);
    }
}
