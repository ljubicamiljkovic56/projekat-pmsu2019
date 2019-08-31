package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {

    private String id;
    private String smtpAdress;
    private int smtpPort;
    private int inServerType;
    private String inServerAdress;
    private int inServerPort;
    private String username;
    private String password;
    private String displayName;
    private ArrayList<Message> messages;
    private ArrayList<Folder> folders;
    private String token;


    public Account() {
        super();
    }

    public Account(String id, String smtpAdress, int smtpPort, int inServerType, String inServerAdress, int inServerPort, String username, String password, String displayName, ArrayList<Message> messages, ArrayList<Folder> folders, String token) {
        this.id = id;
        this.smtpAdress = smtpAdress;
        this.smtpPort = smtpPort;
        this.inServerType = inServerType;
        this.inServerAdress = inServerAdress;
        this.inServerPort = inServerPort;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.messages = messages;
        this.folders = folders;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmtpAdress() {
        return smtpAdress;
    }

    public void setSmtpAdress(String smtpAdress) {
        this.smtpAdress = smtpAdress;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public int getInServerType() {
        return inServerType;
    }

    public void setInServerType(int inServerType) {
        this.inServerType = inServerType;
    }

    public String getInServerAdress() {
        return inServerAdress;
    }

    public void setInServerAdress(String inServerAdress) {
        this.inServerAdress = inServerAdress;
    }

    public int getInServerPort() {
        return inServerPort;
    }

    public void setInServerPort(int inServerPort) {
        this.inServerPort = inServerPort;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}