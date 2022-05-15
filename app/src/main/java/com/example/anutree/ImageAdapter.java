package com.example.anutree;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    String why = getDatabaseData();

    private final String[] item_description = {"belt", "car", "pc", "hat", "jeans", "puffer", "mac", "monitor"};
    private final String[] item_price = {"$50", "$15000", "$2300", "$100", "$50", "$150", "$1200", "$300"};
    private final int[] image = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_10};


    // make all of above lists
    // need to initialise arraylists
    // get from database
    // list of objects
    // send post uid through intent???
    Uri please = Uri.parse("gs://genuine-plating-301723.appspot.com/images/29899b71-c037-42a4-a789-af186b7dc35e");
    Posts hi = new Posts("test",Float.valueOf(22),0,"this is a test to pass data","1234567", please);

    public ImageAdapter(Context context, String[] item_description) {
        this.context = context;
    }

    private String getDatabaseData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Posts> postList = new ArrayList<>(); // initialise list
        // for now we dont need to limit how many posts we retrieve
//        db.collection()
//      read from database
//        put all data in an arraylist
        Log.d("uhm","THIS SHOUDL BE DESIPLASDYD");
        db.collection("posts").whereNotEqualTo("likes",99).limit(30).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
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
                        Posts post = new Posts(title,price,likes,desc,uid,p_uri,author);
                        Log.d("uhm",post.toString());
                        // add post to arraylist

                    }
                }
                else {
                    Log.d("uhm","Not workking", task.getException());

                }
            }
        }); // make it not equals null or something
return "FSAF";
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.description);
            textView.setText(item_description[position]);

            TextView textView_price = (TextView) gridView.findViewById(R.id.item_price);
            textView_price.setText(item_price[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.itemPicture);

            String item = item_description[position];

            imageView.setImageResource(image[position]);

            gridView.setBackgroundColor(Color.WHITE);





        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return item_description.length;
    }

    @Override
    public Object getItem(int position) {
        // this returns the Post object of the view
//        Posts post =
        // we will have a list of posts
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
