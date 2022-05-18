package com.example.anutree;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class
ImageAdapter extends BaseAdapter {
    private Context context;

//    ArrayList<Posts> posts = getDatabaseData(); // initialise list of posts
//    private final ArrayList<String> item_description = new ArrayList<String>(Arrays.asList("belt", "car", "pc", "hat", "jeans", "puffer", "mac", "monitor"));
//    private final ArrayList<String> item_description = new ArrayList<String>(Arrays.asList(posts.get(0).title,posts.get(1).title));
//    private ArrayList<String> item_price;
    private final ArrayList<Posts> item_description;



//    public ArrayList<String> getItem_price() {
//        return ArrayList<String> item_price = (ArrayList<String>) posts.stream().map((p) -> p.price.toString()).collect(Collectors.toList());
//    }


//    private final String[] item_description = {"belt", "car", "pc", "hat", "jeans", "puffer", "mac", "monitor"};
//    private final String[] item_price = {"$50", "$15000", "$2300", "$100", "$50", "$150", "$1200", "$300"};
//    private final int[] image = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
//            R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_10};


    public ImageAdapter(Context context, ArrayList<Posts> item_description) {
        this.context = context;
        this.item_description = item_description;
    }

    private ArrayList<Posts> getDatabaseData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Posts> postList = new ArrayList<>(); // initialise list
//      read from database
//        put all data in an arraylist
        Log.d("uhm","THIS SHOULD BE DISPLAYS");
        db.collection("posts").whereNotEqualTo("likes",-1).limit(30).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Map<String, Object> p = document.getData();
//                        Log.d("uhm",p.get("title").toString());

                        Uri p_uri = Uri.parse(((String) p.get("imageURL")));
                        String title = ((String) p.get("title"));
                        String author = ((String) p.get("author_id"));
                        Float price = ((Double) p.get("price")).floatValue();
                        int likes = (int) ((long) p.get("likes"));
                        String uid = ((String) p.get("uid"));
                        String desc = ((String) p.get("description"));
                        String name = (String) p.get("name");
                        Posts post = new Posts(title,price,likes,desc,author,name,p_uri);
                        Log.d("uhm",post.toString());
                        // add post to arraylist
                        postList.add(post);

                    }
                }
                else {
                    Log.d("uhm","Not workking", task.getException());

                }
            }
        }); // make it not equals null or something
//        return postList;
        return postList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

//            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

        } else {
            gridView = (View) convertView;
        }

        // get layout from mobile.xml
        gridView = inflater.inflate(R.layout.grid_item, null);

        // set value into textview
        TextView textView = (TextView) gridView.findViewById(R.id.description);
        textView.setText(item_description.get(position).title);

        TextView textView_price = (TextView) gridView.findViewById(R.id.item_price);
        textView_price.setText(item_description.get(position).price.toString());

        // set image based on selected text
        ImageView imageView = (ImageView) gridView.findViewById(R.id.itemPicture);

//            String item = item_description.get(position).title;
        Glide.with(this.context).load(item_description.get(position).imageURL).into(imageView);
//            imageView.setIma (item_description.get(position).imageURL);

        gridView.setBackgroundColor(Color.WHITE);

        return gridView;
    }

    @Override
    public int getCount() {
        return item_description.size();
    }

    @Override
    public Object getItem(int position) {
        // this returns the Post object of the view
        Posts post = item_description.get(position);
        return post;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
