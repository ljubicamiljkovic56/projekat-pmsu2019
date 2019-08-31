package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private List<Account> userAccounts;
    private List<Contact> userContacts;
    private List<Tag> userTags;

    public User() {
    }

    public User(String id, String username, String password, String firstname, String lastname, List<Account> userAccounts, List<Contact> userContacts, List<Tag> userTags) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userAccounts = userAccounts;
        this.userContacts = userContacts;
        this.userTags = userTags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public List<Contact> getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(List<Contact> userContacts) {
        this.userContacts = userContacts;
    }

    public List<Tag> getUserTags() {
        return userTags;
    }

    public void setUserTags(List<Tag> userTags) {
        this.userTags = userTags;
    }
}
