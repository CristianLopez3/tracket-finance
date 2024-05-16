package com.finance.tracket.models;

import org.bson.Document;

public class User {

    private String name;
    private String position;

    public User() {}

    public User(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User(Document doc) {
        this.name = doc.getString("name");
        this.position = doc.getString("position");
    }

}
