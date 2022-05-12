package com.example.anutree;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[] item_description = {"belt", "car", "pc", "hat", "jeans", "puffer", "mac", "monitor"};
    private final String[] item_price = {"$50", "$15000", "$2300", "$100", "$50", "$150", "$1200", "$300"};
    private final int[] image = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_10};

    public ImageAdapter(Context context, String[] item_description) {
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_item, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.description);
            textView.setText(item_description[position]);

            TextView textView_price = (TextView) gridView
                    .findViewById(R.id.item_price);
            textView_price.setText(item_price[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.itemPicture);

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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
