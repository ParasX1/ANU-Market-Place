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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Map;
import java.util.UUID;


public class CreatePostActivity extends AppCompatActivity {

    public static final int PICK_IMAGE_REQUEST_CODE = 99;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    ImageView image_preview;
    Uri post_image;
    private ProgressBar progressBar;
    public static boolean has_selected_image = false;
    Uri image_database_uri;



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

        // we are using multiple firebase databases
        progressBar = findViewById(R.id.progressBar);
        storage = FirebaseStorage.getInstance(); // firebase storage
        storageReference = storage.getReference();
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // firestore database
//        mAuth = FirebaseAuth.getInstance(); //Firebase
//        FirebaseStorage storage = FirebaseStorage.getInstance();

        TextView title_input = findViewById(R.id.post_title_input);
        TextView price_input = findViewById(R.id.post_price_input);
        Button image_button = findViewById(R.id.image_button);
        Button create_post_button = findViewById(R.id.create_post_button);
        TextView description_input = findViewById(R.id.post_description_input);
//        ImageView image_preview = findViewById(R.id.image_preview);

//        Intent current_intent = getIntent();
//        User current_user = current_intent.getParcelableExtra("current_user"); // get User object from intent


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
//                Log.d("image yo", "SHOULD BE TRUE");
            }
        });


//      TESTING - this prints User data to the textview for testing -- it works
//        description_input.setText(current_user.fullName + "\n" + current_user.uID + "\n" + current_user.email);

//        need to save to database
//        need to deal with images

        create_post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
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

                    // uploads image
                    reference.putFile(post_image)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    Toast.makeText(getApplicationContext(), "Post Created", Toast.LENGTH_SHORT).show();
                                    Task<Uri> image_url = reference.getDownloadUrl();
                                    image_url.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                        image_database_uri = uri;
//                                        the following stores a post object to the firestore database
//                                        (the image is saved to the "storage" database)
                                            // get current user (firebase)
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            assert user != null; // android studio told me to add this
                                            String userId = user.getUid(); // this is firebase's uid NOT the user's uid
                                            Log.d("uhm",userId);
                                            // access realtime database for user info
                                            FirebaseDatabase RTdb = FirebaseDatabase.getInstance();
                                            DatabaseReference RTref = RTdb.getReference("User");
                                            RTref.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                    if (task.isSuccessful()){

                                                        // upload post data
                                                        String title = title_input.getText().toString();
                                                        float price = Float.parseFloat(price_input.getText().toString());
                                                        String description = description_input.getText().toString();
                                                        // the result should be a document (json type thing)
                                                        DataSnapshot userDoc = task.getResult();
                                                        String userName = (String) userDoc.child("fullName").getValue();
                                                        String userUID = (String) userDoc.child("uID").getValue(); // university id of author

                                                        Log.d("uhm",userName.toString());
                                                        Log.d("uhm","user id" + userUID);

                                                        Posts post = new Posts(title,price,0,description,userUID,userName,image_database_uri); // post object

                                                        Intent go_back = new Intent(getApplicationContext(),Activity2.class);
                                                        startActivity(go_back); // this is done so the previous activity is restarted and "refreshed"

                                                        db.collection("posts").add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                // by here everything is saved and done
                                                                progressBar.setVisibility(View.GONE);
                                                                Toast.makeText(getApplicationContext(), "Post Created", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });

                                                    }
                                                    else {
                                                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                                                    }
                                                }
                                            });


                                        }
                                    });

                                }
                            });

                }

            }
        });


    }
}