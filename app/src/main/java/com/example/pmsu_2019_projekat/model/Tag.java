package com.example.pmsu_2019_projekat.model;

import java.util.ArrayList;

public class Tag {

    private String id;
    private String name;
    private ArrayList<Message> messages;

    public Tag() {
    }

    public Tag(String id, String name, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

