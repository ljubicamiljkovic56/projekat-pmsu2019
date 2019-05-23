package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private String id;
    private String smtp;
    private String pop3imap;
    private String username;
    private String password;
    private ArrayList<Message> messages;
    private  ArrayList<Contact> contacts;
    private ArrayList<Folder> folders;


    public Account() {
        super();
    }



    public Account(String id, String smtp, String pop3imap, String username, String password,ArrayList<Message> messages,
                   ArrayList<Contact> contacts, ArrayList<Folder> folders) {
        this.id = id;
        this.smtp = smtp;
        this.pop3imap = pop3imap;
        this.username = username;
        this.password = password;
        this.messages = messages;
        this.contacts = contacts;
        this.folders = folders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPop3imap() {
        return pop3imap;
    }

    public void setPop3imap(String pop3imap) {
        this.pop3imap = pop3imap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Contact> getContacts() { return contacts; }

    public void setContacts(ArrayList<Contact> contacts) { this.contacts = contacts; }

    public ArrayList<Folder> getFolders() { return folders; }

    public void setFolders(ArrayList<Folder> folders) { this.folders = folders; }
}