package com.covid19riskmeter.Classes;


import java.util.List;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserBloodGroup bloodGroup;
    private String gender;
    private int age;
    private UserLocation location;
    private UserCovidStatus covidStatus;
    private List<Disease> diseases;
    private List<Vaccination> vaccinations;

    public User(int id, String firstName, String lastName, String email, String password, UserBloodGroup bloodGroup, String gender, int age, UserLocation location, UserCovidStatus covidStatus, List<Disease> diseases, List<Vaccination> vaccinations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.covidStatus = covidStatus;
        this.diseases = diseases;
        this.vaccinations = vaccinations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(UserBloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserLocation getLocation() {
        return location;
    }

    public void setLocation(UserLocation location) {
        this.location = location;
    }

    public UserCovidStatus getCovidStatus() {
        return covidStatus;
    }

    public void setCovidStatus(UserCovidStatus covidStatus) {
        this.covidStatus = covidStatus;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }
}
