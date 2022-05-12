package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ViewFlipper;

public class PostListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);

        ViewPager viewPager = findViewById(R.id.pager);

        // Set Adapter
        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);

    }

}