package com.example.fyp;

public class User {

    private String Fname;
    private String email;
    private String Lname;


    public User(String Fname,String Lname, String email){
        this.Fname = Fname;
        this.Lname = Lname;
        this.email = email;




    }

    public void setFName(String name) {
        this.Fname = name;
    }



    public String getFName() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getEmail() {
        return email;
    }
}
