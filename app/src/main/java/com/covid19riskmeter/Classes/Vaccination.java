package com.covid19riskmeter.Classes;

public class Vaccination {

    private int id;
    private User user;
    private String vaccinationName;
    private String vaccinationDate;
    private int vaccinationDose;

    public Vaccination(int id, User user, String vaccinationName, String vaccinationDate, int vaccinationDose) {
        this.id = id;
        this.user = user;
        this.vaccinationName = vaccinationName;
        this.vaccinationDate = vaccinationDate;
        this.vaccinationDose = vaccinationDose;
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

    public String getVaccinationName() {
        return vaccinationName;
    }

    public void setVaccinationName(String vaccinationName) {
        this.vaccinationName = vaccinationName;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public int getVaccinationDose() {
        return vaccinationDose;
    }

    public void setVaccinationDose(int vaccinationDose) {
        this.vaccinationDose = vaccinationDose;
    }
}
