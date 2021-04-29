package com.example.fyp;

public class Journal
{
    String grateful;
    String reason;
    String didWell;
    String id;


    String willDo;

    public Journal(String id, String didWell, String reason, String grateful,String willDo) {
        this.didWell = didWell;
        this.reason = reason;
        this.grateful = grateful;
        this.willDo = willDo;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public String getDidWell() {
        return didWell;

    }

    public String getGrateful() {
        return grateful;
    }

    public String getWillDo() {
        return willDo;
    }
}
