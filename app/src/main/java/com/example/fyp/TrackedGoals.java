package com.example.fyp;

public class TrackedGoals {

    String date;
    int value;
    public Goals goalId;




    public Goals getGoalId() {

        return goalId;
    }

    public void setGoalId(Goals goalId) {
        this.goalId = goalId;
    }


    public TrackedGoals(){


    }

    public TrackedGoals(Goals goalId, String date, int value) {
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
