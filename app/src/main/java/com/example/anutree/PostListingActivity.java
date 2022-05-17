package com.example.anutree;

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

public class PostListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_listing);

        Toast.makeText(PostListingActivity.this,"test", Toast.LENGTH_SHORT).show();
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
            }
        });

        ViewPager viewPager = findViewById(R.id.pager);
        Intent post_data = getIntent();
//        Posts post = post_data.getParcelableExtra("the post");
        // something wrong with parcelable implementation
        String post_title = post_data.getStringExtra("title");
        Float post_price = post_data.getFloatExtra("price",0);
        int post_likes = post_data.getIntExtra("likes",0);
        String post_author = post_data.getStringExtra("author");
        String post_desc = post_data.getStringExtra("description");
        Uri post_image_url = post_data.getParcelableExtra("imageUrl");
        String post_uid = post_data.getStringExtra("uid");
        Log.d("uhm",post_price.toString() + "is price");
        Posts this_post = new Posts(post_title,post_price,post_likes,post_desc,post_uid,post_image_url);

        setTitle(post_title);

        // Set Adapter
        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);

        TextView title = findViewById(R.id.listingtitle);
        TextView author = findViewById(R.id.author);
        TextView price = findViewById(R.id.price);
        TextView description = findViewById(R.id.desc_content);

        title.setText(post_title);
        author.setText(post_author);
        price.setText(post_price.toString());
        description.setText(post_desc);



    }

}