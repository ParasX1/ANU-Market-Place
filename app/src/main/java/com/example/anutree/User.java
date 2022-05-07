package com.example.anutree;

public class User {
    // Makes object to store in Firebase
    public String fullName,uID,email;
    public User() {}

    public User(String fullName,String uID,String email) {
        this.fullName =fullName;
        this.uID =uID;
        this.email =email;
    }
}
