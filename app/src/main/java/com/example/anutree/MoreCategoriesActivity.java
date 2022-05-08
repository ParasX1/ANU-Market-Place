package com.example.anutree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MoreCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_categories);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Categories");
        categories.add("Vehicles");
        categories.add("Books, Music and Games");
        categories.add("All Categories");
        categories.add("Jewelry and Clothing");
        categories.add("Electronics");
        categories.add("Home");
        categories.add("Services for Hire");
        categories.add("Sport/Fitness");
        categories.add("Misc");
        categories.add("Free");

        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,categories);
        ListView categoryList = findViewById(R.id.listview);
        categoryList.setAdapter(categoryAdapter);

    }
}