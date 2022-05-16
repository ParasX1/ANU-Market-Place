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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

public class MessageProfile extends AppCompatActivity {

    private Button upload;
    private ImageView imgProfile;
    private ProgressBar progressBar;
    private Uri imagePath; //path to image


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_profile);

        imgProfile = (ImageView) findViewById(R.id.profile_img);
        upload = (Button) findViewById(R.id.upload_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar); // Initialise the loading bar

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK); //Code to open gallery
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

    }

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

    private void upLoadProfile() {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseStorage.getInstance().getReference("images/" + UUID.randomUUID().toString()).putFile(imagePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {  // To update Pfp of user, and add it to User
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if(task.isSuccessful()) {
                                updateProfilePicture(task.getResult().toString());
                            }
                        }
                    });
                    Toast.makeText(MessageProfile.this, "Successful Upload of Image", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MessageProfile.this, "Unsuccessful :(, Try again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateProfilePicture(String toString) {
    }

}