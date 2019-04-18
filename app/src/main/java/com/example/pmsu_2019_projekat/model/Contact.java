package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private String id;
    private String first;
    private String last;
    private String email;
    private String format;
    private Photo display;

    public Contact(){
        super();
    }

    public Contact(String id, String first, String last, Photo display, String email, String format) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.display = display;
        this.email = email;
        this.format = format;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Photo getDisplay() {
        return display;
    }

    public void setDisplay(Photo photo) {
        this.display = display;
    }
}
