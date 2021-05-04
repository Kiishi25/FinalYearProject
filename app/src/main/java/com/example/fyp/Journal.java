package com.example.fyp;

public class Journal
{
    String grateful;
    String reason;
    String today;
    String id;
    String willDo;

    public Journal() {

    }

    public Journal(String id, String today, String reason, String grateful,String willDo) {
        this.today =  today;
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

    public void setGrateful(String grateful) {
        this.grateful = grateful;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getToday() {
        return today;
    }

    public void setWillDo(String willDo) {
        this.willDo = willDo;
    }



    public void setToday(String today) {
        this.today = today;
    }

    public String getGrateful() {
        return grateful;
    }

    public String getWillDo() {
        return willDo;
    }
}
