package ru.kpfu.itis.entities;

public class User {

    private String email;
    private String password;
    private String sex;
    private String checkbox;

    public User(String email, String password, String sex, String checkbox) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.checkbox = checkbox;
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

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }


    @Override
    public String toString() {
        return email+","+password+","+sex+","+checkbox;
    }
}
