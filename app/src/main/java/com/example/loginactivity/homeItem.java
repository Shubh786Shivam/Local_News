package com.example.loginactivity;

public class homeItem {

    private String imageUrl;
    private String title;
    private String date;

    //Constructor

    public homeItem(String imageUrl, String title, String date) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.date = date;
    }
    //Getters

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
