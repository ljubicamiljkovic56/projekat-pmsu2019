package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable {

    private String id;
    private Contact from;
    private ArrayList<Contact> to;
    private ArrayList<Contact> cc;
    private ArrayList<Contact> bcc;
    private Date dateTime;
    private String subject;
    private String content;
    private ArrayList<Tag> tags;
    private ArrayList<Attachment> attachments;
    private Folder folder;
    private Account account;

    public Message(){
        super();
    }



    public Message(String id, Contact from, ArrayList<Contact> to, ArrayList<Contact> cc, ArrayList<Contact> bcc, Date dateTime, String subject, String content, ArrayList<Tag> tags, ArrayList<Attachment> attachments, Folder folder, Account account) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.dateTime = dateTime;
        this.subject = subject;
        this.content = content;
        this.tags = tags;
        this.attachments = attachments;
        this.folder = folder;
        this.account = account;
    }

    @Override
    public String toString() {
        return from.getFirst() + ": " + "Subject: " + subject + " " + "Content: " + content + " ";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact getFrom() {
        return from;
    }

    public void setFrom(Contact from) {
        this.from = from;
    }

    public ArrayList<Contact> getTo() {
        return to;
    }

    public void setTo(ArrayList<Contact> to) {
        this.to = to;
    }

    public ArrayList<Contact> getCc() {
        return cc;
    }

    public void setCc(ArrayList<Contact> cc) {
        this.cc = cc;
    }

    public ArrayList<Contact> getBcc() {
        return bcc;
    }

    public void setBcc(ArrayList<Contact> bcc) {
        this.bcc = bcc;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
