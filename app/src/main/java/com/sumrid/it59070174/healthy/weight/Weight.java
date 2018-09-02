package com.sumrid.it59070174.healthy.weight;

public class Weight {
    private String data;
    private float weight;
    private String status;

    public Weight(){
    }

    public Weight(String data, float weight, String status) {
        this.data = data;
        this.weight = weight;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
}
