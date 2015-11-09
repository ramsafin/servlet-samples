package ru.kpfu.itis.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int id;
    private String text;
    private String publishedTime;
    private int userId;
    private String userName;

    public Post(String text, int userId) {
        this(text,currentDate(), userId);
    }

    public Post(String text, String publishedTime, int userId) {
        this.text = text;
        this.publishedTime = publishedTime;
        this.userId = userId;
    }

    public Post(int id, String text, String publishedTime, int userId) {
        this.id = id;
        this.text = text;
        this.publishedTime = publishedTime;
        this.userId = userId;
    }

    private static String currentDate(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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

    public int getUser() {
        return userId;
    }

    public void setUser(int user_id) {
        this.userId = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }

}
