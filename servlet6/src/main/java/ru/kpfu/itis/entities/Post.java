package ru.kpfu.itis.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

    private int id;
    private String text;
    private String publishedTime;
    private int user_id;
    private String user_name;

    public Post(String text, int user_id) {
        this(text,currentDate(),user_id);
    }

    public Post(String text, String publishedTime, int user_id) {
        this.text = text;
        this.publishedTime = publishedTime;
        this.user_id = user_id;
    }

    public Post(int id, String text, String publishedTime, int user_id) {
        this.id = id;
        this.text = text;
        this.publishedTime = publishedTime;
        this.user_id = user_id;
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
        return user_id;
    }

    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return new StringBuilder("").append(id).append("\ntext\n")
        .append(text).append("\n").append("time = ").
        append(publishedTime).append("\n").append("user =  ").append(user_name).toString();
    }
}
