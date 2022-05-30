package com.covid19riskmeter.Classes;

public class UserBloodGroup {

    private int id;
    private String groupName;

    public UserBloodGroup(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
