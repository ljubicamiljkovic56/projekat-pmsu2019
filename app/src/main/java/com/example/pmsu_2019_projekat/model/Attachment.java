package com.example.pmsu_2019_projekat.model;

import android.util.Base64;

import java.io.Serializable;

public class Attachment implements Serializable {

    private String id;
    private Base64 data;
    private String type;
    private String name;
    private Message message;


    public Attachment() {
    }

    public Attachment(String id, Base64 data, String type, String name, Message message) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.name = name;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Base64 getData() {
        return data;
    }

    public void setData(Base64 data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}


