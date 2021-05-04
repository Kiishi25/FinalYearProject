package com.example.fyp;

public class User {

    private String fname;
    private String email;
    private String lname;


    public User(String fname,String lname, String email){
        this.fname = fname;
        this.lname = lname;
        this.email = email;

    }
    public User(){



    }
    public void setfName(String name) {
        this.fname = name;
    }



    public String getFName() {
        return fname;
    }

    public String getLname() {
        return fname;
    }

    public String getEmail() {
        return email;
    }
}
