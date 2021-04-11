package com.example.fyp;

public class ScreenItem {

    String Title;
    String Description;


    int ScreenImage;

    public ScreenItem (String title, String description, int screenImage) {
        Title = title;
        Description = description;
        ScreenImage = screenImage;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImage = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImage;
    }
}
