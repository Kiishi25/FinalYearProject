package com.example.fyp;

import java.util.Map;

public class Goals {


    public String names;
    public String period;
    public int target;
public String id;
    public String startDate;
    public String endDate;
    public String type;
    public String measure;



    public Goals() {
    }



    public Goals(String type) {
        this.type = type;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Goals(String id, String names, String period, int target, String type, String startDate, String endDate, String measure) {
        this.names = names;
        this.period = period;
        this.target = target;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.measure = measure;
        this.id = id;



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {

        return names;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }


    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPeriod() {

        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String toString()
    {
        return id + ", " + type + ", ";
    }

}

