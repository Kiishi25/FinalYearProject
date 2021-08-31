package com.example.fyp;

public class Mood {

    String date;
    int feel;
    String reason;
    String feeling;



    public Mood(String date, int feel) {
        this.date = date;
        this.feel = feel;

    }
    public Mood() {

    }

    public int getFeel() {
        return feel;
    }

    public void setFeel(int feel) {
        this.feel = feel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }
}
