package com.covid19riskmeter.Classes;


import java.util.List;

public class User {

    public String id;
    public String name;
    public String email;
    public String password;
    public String blood;
    public String gender;
    public String vaccination;
    public int risk;
    public boolean tobacco;
    public int age;
    public int profileLevel;
    public static User me;

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public boolean isTobacco() {
        return tobacco;
    }

    public void setTobacco(boolean tobacco) {
        this.tobacco = tobacco;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getProfileLevel() {
        return profileLevel;
    }

    public void setProfileLevel(int profileLevel) {
        this.profileLevel = profileLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id,String email, String password, String name,int profileLevel, int age, boolean tobacco,String gender,String blood) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileLevel=profileLevel;
        this.age=age;
        this.tobacco=tobacco;
        this.gender=gender;
        this.blood=blood;
        me = this;

    }



    public String toString(){
        return String.format("{id: %s, name: %s, email: %s, pass: %s, age: %s, blood: %s, profileLevel: %s, vaccination: %s, tobacco: %s}",this.id,this.name,this.email,this.password,this.age,this.blood,this.profileLevel,this.vaccination,this.tobacco);
    }



}
