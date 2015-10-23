package ru.kpfu.itis.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + salt.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (remember != null ? remember.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("email", email)
                .append("password", password)
                .append("salt", salt)
                .append("sex", sex)
                .append("subscription", subscription)
                .append("about", about)
                .append("remember", remember)
                .toString();
    }
}
