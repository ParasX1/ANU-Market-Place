package com.example.anutree;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Posts  {

    public String author; // this is the uid of the user
    public String uid; // unique id
    public String title;
    public Float price;
    public int likes;
    public String description;
    public Uri imageURL;

    public Posts(){}
    public Posts(String title, Float price, int likes, String desc, String author, Uri imageURL){
        this.title = title;
        this.uid = UUID.randomUUID().toString();
        this.author = author; // this will be the user's firebase id
        this.price = price;
        this.likes = likes;
        this.description = desc;
        this.imageURL = imageURL;
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


    /*    this class implements parcelable
          the following is from the following link:
          https://developer.android.com/reference/android/os/Parcelable
          this is so we can add a Posts object as an extra to an intent (through parcelable)
     */
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(title);
//        parcel.writeFloat(price);
//        parcel.writeInt(likes);
//        parcel.writeString(description);
//        parcel.writeString(uid);
//        parcel.writeParcelable(imageURL,i);
//        parcel.writeString(author);
//    }
//
//    public static final Parcelable.Creator<Posts> CREATOR = new Parcelable.Creator<Posts>() {
//        public Posts createFromParcel(Parcel in) {
//            return new Posts(in);
//        }
//
//        public Posts[] newArray(int size) {
//            return new Posts[size];
//        }
//    };
//
//
//
//    private Posts(Parcel in){
//        this.title = in.readString();
//        this.uid = in.readString();
//        this.author = in.readString();
//        this.price = in.readFloat();
//        this.likes = in.readInt();
//        this.description = in.readString();
//        this.imageURL = in.readParcelable(Uri.class.getClassLoader());
//
//
//    }




}
