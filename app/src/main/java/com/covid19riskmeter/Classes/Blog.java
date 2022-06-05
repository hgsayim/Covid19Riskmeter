package com.covid19riskmeter.Classes;

import java.util.List;

public class Blog {

    private int id;
    private String createdAt;
    private String title;
    private String content;
    private String coverPhoto;
    public static List<Blog> list;

    public Blog(int id, String createdAt, String title, String content, String coverPhoto) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
        this.coverPhoto = coverPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
