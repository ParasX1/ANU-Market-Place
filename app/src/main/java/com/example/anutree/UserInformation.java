package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class UserInformation extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userEmail = user.getEmail();
    String name = user.getDisplayName();

    private Button upload;
    private ImageView imgProfile;
    private ProgressBar progressBar;
    private Uri imagePath; //path to image

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //On result of picking image
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data!=null && resultCode == RESULT_OK) {
            imagePath = data.getData(); //retrieves data if image is not empty
            getImageInView();
        }
    }

    private void getImageInView() { //Set image to profile by making bitMap
        Bitmap bitmap = null; try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgProfile.setImageBitmap(bitmap); //sets as bitmap
    }

    private void upLoadProfile() { //Uploads Image
        FirebaseStorage.getInstance().getReference("images/" + UUID.randomUUID().toString()).putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if (task.isSuccessful()) {
                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {  // To update Pfp of user, and add it to User
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()) {
                                updateProfilePicture(task.getResult().toString());
                                Intent i = new Intent(getApplicationContext(),MainChat.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(UserInformation.this, "Error Try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(UserInformation.this, "Successful Upload of Image", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserInformation.this, "Unsuccessful :(, Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateProfilePicture(String urlImg) { //Updates oject User in Firebase with pfp Img url
        FirebaseDatabase.getInstance().getReference("User/"+FirebaseAuth.getInstance().getUid()+"/pfp").setValue(urlImg);
    }

    public void on_saved_posts(View view){
        Intent intent = new Intent(getApplicationContext(), savedPosts.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        TextView email = (TextView) findViewById(R.id.user_email);
        TextView user_greeting = (TextView) findViewById(R.id.hello_user);
        Button message = (Button) findViewById(R.id.user_my_messages);
        imgProfile = (ImageView) findViewById(R.id.profile_img);
        upload = (Button) findViewById(R.id.upload_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2); // Initialise the loading bar

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //Code to open gallery
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent,1);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upLoadProfile(); //upload pfp
            }
        });


        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainChat.class);
                startActivity(intent);
            }
        });

        email.setText(userEmail);
        user_greeting.setText(name);


    }

}