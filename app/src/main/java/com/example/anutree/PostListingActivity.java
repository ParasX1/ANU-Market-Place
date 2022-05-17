package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
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

        // Set Adapter
        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);



    }

}