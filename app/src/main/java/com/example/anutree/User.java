package com.example.anutree;

import static java.lang.System.out;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    // Makes object to store in Firebase
    public String fullName,uID,email,password,pfp;
    public ArrayList<String> savedPosts;


    public User()
    {}

    public User(String fullName,String uID,String email,String password,String pfp,ArrayList<String> savedPosts) {
        this.fullName =fullName;
        this.uID =uID;
        this.email =email;
        this.password =password;
        this.pfp =pfp;
        this.savedPosts = new ArrayList<String >();
    }



    /*    this class implements parcelable
            the following is from the following link:
            https://developer.android.com/reference/android/os/Parcelable
            this is so we can add a User object as an extra to an intent (through parcelable)
       */

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPfp() {
        return pfp;
    }

    public void setPfp(String pfp) {
        this.pfp = pfp;
    }

    protected User(Parcel in) {
        fullName = in.readString();
        uID = in.readString();
        email = in.readString();
        password = in.readString();
        pfp = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(uID);
        parcel.writeString(email);
    }







}
