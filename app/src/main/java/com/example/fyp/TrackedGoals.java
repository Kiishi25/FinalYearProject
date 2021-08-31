package com.example.fyp;

import java.util.Comparator;

public class TrackedGoals {

   private String date;
  private  int value;
 //  private String userid;






    public TrackedGoals( String date, int value) {
        this.date = date;
        this.value = value;
        //this.userid = userid;
     //   this.goalId = goalId;

    }

   // public String getUserid() {
    //    return userid;
  //  }

  //  public void setUserid(String userid) {
  //      this.userid = userid;
  //  }
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




    public String toString()
    {
        return date + ", " + value + " ";
    }
}
