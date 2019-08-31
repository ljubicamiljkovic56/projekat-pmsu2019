package com.example.pmsu_2019_projekat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Message implements Serializable {

    private String id;
    private String from;
    private ArrayList<String> to;
    private ArrayList<String> cc;
    private ArrayList<String> bcc;
    private Date dateTime;
    private String subject;
    private String content;
    private ArrayList<Tag> tags;
    private ArrayList<Attachment> attachments;
    private boolean unread;

    public Message(){
        super();
    }



    public Message(String id, String from, ArrayList<String> to, ArrayList<String> cc, ArrayList<String> bcc, Date dateTime, String subject, String content, ArrayList<Tag> tags, ArrayList<Attachment> attachments, boolean unread) {
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
        this.unread = unread;
    }

    public static Comparator<Message> MessageDateComparator = new Comparator<Message>() {
        public int compare(Message a, Message b) {
            return a.getDateTime().compareTo(b.getDateTime());
        }
    };

    public static Comparator<Message> MessageDateComparatorDesc = new Comparator<Message>() {
        public int compare(Message a, Message b) {
            return b.getDateTime().compareTo(a.getDateTime());
        }
    };

    @Override
    public String toString() {
        return from + ": " + "Subject: " + subject + " " + "Content: " + content + " ";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    public ArrayList<String> getCc() {
        return cc;
    }

    public void setCc(ArrayList<String> cc) {
        this.cc = cc;
    }

    public ArrayList<String> getBcc() {
        return bcc;
    }

    public void setBcc(ArrayList<String> bcc) {
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

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }
}
