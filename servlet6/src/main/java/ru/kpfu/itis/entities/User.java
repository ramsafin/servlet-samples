package ru.kpfu.itis.entities;

public class User {

    private int id;
    private String email;
    private String password;
    private String salt;
    private String sex;
    private String subscription;
    private String about;
    private String remember;//"remember me" cookies value

    public User(int id, String email, String password, String salt, String sex, String subscription, String about, String remember) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.sex = sex;
        this.subscription = subscription;
        this.about = about;
        this.remember = remember;
    }

    public User(String email, String password,String sex, String subscription, String about, String remember) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.subscription = subscription;
        this.about = about;
        this.remember = remember;
    }

    public User(String email, String password,String sex, String subscription, String about) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.subscription = subscription;
        this.about = about;
    }

    public User(String sex, String subscription, String about) {
        this.sex = sex;
        this.subscription = subscription;
        this.about = about;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public int getId() {
        return id;
    }

}
