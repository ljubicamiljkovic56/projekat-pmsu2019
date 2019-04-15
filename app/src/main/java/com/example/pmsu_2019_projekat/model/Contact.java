package com.example.pmsu_2019_projekat.model;

public class Contact {

    private String id;
    private String first;
    private String last;
    private String display;
    private String email;
    private String format;
    private Photo photo;

    public Contact(){

    }

    public Contact(String id, String first, String last, String display, String email, String format, Photo photo) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.display = display;
        this.email = email;
        this.format = format;
        this.photo = photo;
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

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
