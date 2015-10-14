package ru.kpfu.itis.entities;

public class User {

    //TODO int id

    private String email;
    private String password;
    private String checkboxStatus;
    private String sex;

    public User() {}

    public User(String email, String password, String sex, String checkboxStatus) {
        this.email = email;
        this.password = password;
        this.checkboxStatus = checkboxStatus;
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckboxStatus(){
        return checkboxStatus;
    }

    public String getSex() {
        return sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCheckboxStatus(String checkboxStatus) {
        this.checkboxStatus = checkboxStatus;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
