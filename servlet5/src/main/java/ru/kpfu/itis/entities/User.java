package ru.kpfu.itis.entities;

public class User {

    private String email;
    private String password;
    private String sex;
    private boolean subscription;
    private String about;

    public User(String email, String password, String sex, boolean subscription, String about) {
        this.email = email;
        this.password = password;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
