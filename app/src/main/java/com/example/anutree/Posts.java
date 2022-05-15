package com.example.anutree;

import android.net.Uri;

import java.util.UUID;

public class Posts {

    public String author; // this is the uid of the user
    public String uid; // unique id
    public String title;
    public Float price;
    public int likes;
    public String description;
    public Uri imageURL;

    public Posts(){}
    public Posts(String title, Float price, int likes, String desc, String uid, Uri imageURL){
        this.title = title;
        this.uid = UUID.randomUUID().toString();
        this.author = uid; // this will be the user's firebase id
        this.price = price;
        this.likes = likes;
        this.description = desc;
        this.imageURL = imageURL;
    }
    public Posts(String title, Float price, int likes, String desc, String uid, Uri imageURL, String author){
        this.title = title;
        this.uid = UUID.randomUUID().toString();
        this.author = uid; // this will be the user's firebase id
        this.price = price;
        this.likes = likes;
        this.description = desc;
        this.imageURL = imageURL;
        this.author = author;
    }

    // getter methods so we can upload a post object to firebase (firebase requirement)
    public String getAuthor() {
        return author;
    }
    public String getUid() {
        return uid;
    }
    public String getTitle() {
        return title;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Uri getImageURL() {
        return imageURL;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "author='" + author + '\'' +
                ", uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageURL=" + imageURL +
                '}';
    }
}
