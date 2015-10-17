package ru.kpfu.itis.entities;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String password;
    private SEX sex;
    private String subscription;
    private String about;

    public User(String email, String password, SEX sex) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        subscription = "off";
        about = "";
    }

    public User(String email, String password, SEX sex, String subscription) {
        this(email,password,sex);
        this.subscription = subscription;
    }

    public User(String email, String password, SEX sex, String subscription, String about) {
        this(email,password,sex,subscription);
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

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equalsIgnoreCase(user.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return email+", "+password+", "+sex+", "+subscription+", "+about;
    }
}
