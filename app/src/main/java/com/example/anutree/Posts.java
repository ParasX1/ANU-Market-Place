package com.example.anutree;

import java.util.UUID;

public class Posts {

    public String author; // this is the uid of the user
    public String uid; // unique id
    public Float price;
    public String description;
    public String imageURL; // need to change this - Uri ??


    public Posts(Float price, String desc, String uid){
        this.uid = UUID.randomUUID().toString();
        this.author = uid;
        this.price = price;
        this.description = desc;
    }

}
