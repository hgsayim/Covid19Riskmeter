package com.covid19riskmeter.Classes;

public class Disease {

    private int id;
    private User user;
    private String diseaseName;
    private String diseaseDescription;


    public Disease(int id, User user, String diseaseName, String diseaseDescription) {
        this.id = id;
        this.user = user;
        this.diseaseName = diseaseName;
        this.diseaseDescription = diseaseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }
}
