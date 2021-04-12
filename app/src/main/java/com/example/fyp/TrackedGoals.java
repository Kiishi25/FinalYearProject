package com.example.fyp;

public class TrackedGoals {

    String date;
    int value;
    String goalId;




    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }


    public TrackedGoals(){


    }

    public TrackedGoals( String date, int value) {
        this.date = date;
        this.value = value;
        this.goalId = goalId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
