package com.example.pmsu_2019_projekat.tools;

import com.example.pmsu_2019_projekat.activities.ContactActivity;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Message;

import java.util.ArrayList;
import java.util.Date;

public class Data {

    public static ArrayList<Message> getEmails(){
        ArrayList<Message> emails = new ArrayList<>();
        Message m1 = new Message();
        m1.setId("123");
        Contact sender = ContactActivity.dummy1;
        if(sender == null){
            ContactActivity.kontaktPodaci();
            sender = ContactActivity.dummy1;
        }
        m1.setFrom(sender);
        ArrayList<Contact> contactList = new ArrayList<>();
        Contact a = new Contact();
        a.setFirst("Marko");
        a.setLast("Marković");
        a.setEmail("markom@yahoo.com");
        contactList.add(a);
        m1.setTo(contactList);
        m1.setCc(contactList);
        m1.setBcc(contactList);
        m1.setDateTime(new Date(2019,04,16,16,41));
        m1.setSubject("Veliki pozdrav");
        m1.setContent("Ovo je primer neke email poruke");
        Message m2 = new Message();
        m2.setId("123");
        m2.setFrom(sender);
        m2.setTo(contactList);
        m2.setCc(contactList);
        m2.setBcc(contactList);
        m2.setDateTime(new Date(2019,04,17,12,21));
        m2.setSubject("Naslov druge poruke");
        m2.setContent("Ovo je sadrzaj neke druge email poruke");
        emails.add(m1);
        emails.add(m2);
        return emails;
    }
}
