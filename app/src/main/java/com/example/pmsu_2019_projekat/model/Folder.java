package com.example.pmsu_2019_projekat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {

    private String id;
    private String name;
    @JsonProperty("subFolders")
    private ArrayList<Folder> subFolders;
    private ArrayList<Message> messages;
    private ArrayList<Rule> rules;

    public Folder(){

    }

    public Folder(String id, String name, ArrayList<Folder> subFolders, ArrayList<Message> messages, ArrayList<Rule> rules) {
        this.id = id;
        this.name = name;
        this.subFolders = subFolders;
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

    public ArrayList<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(ArrayList<Folder> subFolders) {
        this.subFolders = subFolders;
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
                ", subfolders=" + subFolders +
                ", messages=" + messages +
                ", rules=" + rules +
                '}';
    }
}
