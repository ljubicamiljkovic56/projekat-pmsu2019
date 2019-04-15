package com.example.pmsu_2019_projekat.model;

import java.util.ArrayList;

public class Folder {

    private String id;
    private String name;
    private Folder parentFolder;
    private ArrayList<Folder> subfolders;
    private ArrayList<Message> messages;

    public Folder(){

    }

    public Folder(String id, String name, ArrayList<Folder> subfolders, Folder parentFolder, ArrayList<Message> messages) {
        this.id = id;
        this.name = name;
        this.subfolders = subfolders;
        this.parentFolder = parentFolder;
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

    public ArrayList<Folder> getSubfolders() {
        return subfolders;
    }

    public void setSubfolders(ArrayList<Folder> subfolders) {
        this.subfolders = subfolders;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
