package com.sumrid.it59070174.healthy.weight;

import java.util.Date;

public class Weight {
    private String dateStr;
    private String date;
    private float weight;
    private String status;

    public Weight(){
    }

    public Weight(String dateStr, float weight, String status) {
        this.dateStr = dateStr;
        this.weight = weight;
        this.status = status;
        this.date = new Date().toString();
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
