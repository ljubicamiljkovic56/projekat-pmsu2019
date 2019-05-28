package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {

    private String id;
    private String name;
    private Folder parentFolder;
    private ArrayList<Folder> subfolders;
    private ArrayList<Message> messages;
    private ArrayList<Rule> rules;

    public Folder(){

    }

    public Folder(String id, String name, ArrayList<Folder> subfolders, Folder parentFolder, ArrayList<Message> messages, ArrayList<Rule> rules) {
        this.id = id;
        this.name = name;
        this.subfolders = subfolders;
        this.parentFolder = parentFolder;
        this.messages = messages;
        this.rules = rules;
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

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentFolder=" + parentFolder +
                ", subfolders=" + subfolders +
                ", messages=" + messages +
                ", rules=" + rules +
                '}';
    }
}
