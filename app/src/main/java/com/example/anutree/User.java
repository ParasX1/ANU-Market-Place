package com.example.anutree;

public class User {
    // Makes object to store in Firebase
    public String fullName,uID,email,password;
    public User() {}

    public User(String fullName,String uID,String email,String password) {
        this.fullName =fullName;
        this.uID =uID;
        this.email =email;
        this.password =password;
    }
}
