package com.example.anutree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class CreatePostActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST_CODE = 99;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    ImageView image_preview;
    Uri post_image;
    public static boolean has_selected_image = false;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            Uri selected_image = data.getData();
            post_image = selected_image; // saving it for later
            ImageView image_preview = findViewById(R.id.image_preview);
            image_preview.setImageURI(selected_image);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        setTitle("Create New Post");
//        boolean has_selected_image = false;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
//        mAuth = FirebaseAuth.getInstance(); //Firebase
//        FirebaseStorage storage = FirebaseStorage.getInstance();

        TextView title_input = findViewById(R.id.post_title_input);
        TextView price_input = findViewById(R.id.post_price_input);
        Button image_button = findViewById(R.id.image_button);
        Button create_post_button = findViewById(R.id.create_post_button);
        TextView description_input = findViewById(R.id.post_description_input);
//        ImageView image_preview = findViewById(R.id.image_preview);

        Intent current_intent = getIntent();
        User current_user = current_intent.getParcelableExtra("current_user"); // get User object from intent


//      photo picker
        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST_CODE);
                has_selected_image = true;
                Log.d("image yo", "SHOULD BE TRUE");
            }
        });


//      TESTING - this prints User data to the textview for testing -- it works
        description_input.setText(current_user.fullName + "\n" + current_user.uID + "\n" + current_user.email);

//        need to save to database
//        need to deal with images

        create_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                check if all entries valid
                if (title_input.getText().toString().equals("") || price_input.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
                }
                else if (!has_selected_image){
                    Toast.makeText(getApplicationContext(), "Missing Image", Toast.LENGTH_SHORT).show();
                }
                else { // if we're here then all inputs are valid (description can be empty for now)
//                    uploading to database
                    final String random_key = UUID.randomUUID().toString(); // random key to "name" the file and to handle duplicates in the database
                    StorageReference reference = storageReference.child("images/" + random_key);

                    reference.putFile(post_image)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Toast.makeText(getApplicationContext(),"works apparently",Toast.LENGTH_SHORT);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    Toast.makeText(getApplicationContext(),"Something went wrong uploading image",Toast.LENGTH_SHORT);
                                }
                            });

                }

            }
        });


    }
}