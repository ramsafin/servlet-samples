package ru.kpfu.itis.entities;

public enum SEX{

    MALE("male"), FEMALE("female");
    String description;
    SEX(String description){
        this.description = description;
    }

    public String toString(){
        return description;
    }
}
