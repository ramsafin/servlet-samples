package ru.kpfu.itis.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int id;
    private String text;
    private String publishedTime;
    private User user;

    public Post(int id, String text, User user) {
        this.id = id;
        this.text = text;
        this.publishedTime = currentDate();
        this.user = user;
    }

    private static String currentDate(){
        return new SimpleDateFormat("E, dd.MM.yyyy HH:mm:ss").format(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
