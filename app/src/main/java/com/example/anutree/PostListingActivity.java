package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PostListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);

        ViewPager viewPager = findViewById(R.id.pager);
        Intent post_data = getIntent();
        Posts post = post_data.getParcelableExtra("the post");
        // something wrong with parcelable implementation
        setTitle(post.title);

        Log.d("uhm",(new Float(2.0).toString()));
        // Set Adapter
        Adapter adapter = new Adapter(this,post.imageURL);
        viewPager.setAdapter(adapter);

        TextView title = findViewById(R.id.listingtitle);
        TextView author = findViewById(R.id.author);
        TextView price = findViewById(R.id.price);
        TextView description = findViewById(R.id.desc_content);

        title.setText(post.title);
        author.setText(post.author);
        price.setText(String.valueOf(post.price));
//        description.setText(post.description);
        description.setText(post.toString());



    }

}