package com.example.anutree;

import static java.lang.System.out;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    // Makes object to store in Firebase
    public String fullName,uID,email,password;
    public User() {}

    public User(String fullName,String uID,String email,String password) {
        this.fullName =fullName;
        this.uID =uID;
        this.email =email;
        this.password =password;
    }

    /*    this class implements parcelable
            the following is from the following link:
            https://developer.android.com/reference/android/os/Parcelable
            this is so we can add a User object as an extra to an intent (through parcelable)
       */

    protected User(Parcel in) {
        fullName = in.readString();
        uID = in.readString();
        email = in.readString();
        password = in.readString();
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
