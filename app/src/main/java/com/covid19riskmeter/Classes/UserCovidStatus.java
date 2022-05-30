package com.covid19riskmeter.Classes;

public class UserCovidStatus {

    private int id;
    private String covidStatus;

    public UserCovidStatus(int id, String covidStatus) {
        this.id = id;
        this.covidStatus = covidStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCovidStatus() {
        return covidStatus;
    }

    public void setCovidStatus(String covidStatus) {
        this.covidStatus = covidStatus;
    }
}
