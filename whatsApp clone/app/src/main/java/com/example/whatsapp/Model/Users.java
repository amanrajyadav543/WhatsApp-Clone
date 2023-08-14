package com.example.whatsapp.Model;

public class Users {

    String profilePic  , username , mail , password, userId , lastmessage;

    public Users(String profile, String username, String mail, String password, String userId, String lastmessage) {
        this.profilePic = profile;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastmessage = lastmessage;
    }
    public  Users(){

    }

    // signUp constructor
    public Users(String  username, String mail, String password) {

        this.username = username;
        this.mail = mail;
        this.password = password;

    }

    public static void getUserId(String key) {
    }

    public String getUserId() {
        return userId;
    }

  

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfile() {
        return profilePic;
    }

    public void setProfile(String profile) {
        this.profilePic = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }
}
