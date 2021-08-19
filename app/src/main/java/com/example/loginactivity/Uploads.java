package com.example.loginactivity;

public class Uploads {
    private String title;
    private String imageUrl;
    private String multiLineText;
    private String author;
    private String currentDate;
    private String status;
    // Empty Constructor to initialize Firebase;
    public Uploads() {
    }
    // Parametrized Constructor to Uploads;
    public Uploads(String title, String imageUrl, String multiLineText, String author, String currentDate, String status) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.multiLineText = multiLineText;
        this.author = author;
        this.currentDate = currentDate;
        this.status = status;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMultiLineText() {
        return multiLineText;
    }

    public void setMultiLineText(String multiLineText) {
        this.multiLineText = multiLineText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
