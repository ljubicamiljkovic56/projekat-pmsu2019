package com.example.pmsu_2019_projekat.tools;

import com.example.pmsu_2019_projekat.activities.ContactActivity;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;

import java.util.ArrayList;
import java.util.Date;

public class Data {

    public static ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        Account a1 = new Account();
        a1.setId("1");
        a1.setUsername("miki@gmail.com");
        a1.setPassword("miki123");
        ArrayList<Message> messages = new ArrayList<>();
        a1.setMessages(messages);
        Account a2 = new Account();
        a2.setId("2");
        a2.setUsername("kiki@icloud.com");
        a2.setPassword("kiki123");
        accounts.add(a1);
        accounts.add(a2);
        return accounts;
    }

    public static ArrayList<Message> getEmails(){
        ArrayList<Message> emails = new ArrayList<>();
        Message m1 = new Message();
        m1.setId("123");
        m1.setFrom(getContacts().get(1));
        ArrayList<Contact> contactList = new ArrayList<>();
        contactList.add(getContacts().get(0));
        m1.setTo(contactList);
        m1.setCc(contactList);
        m1.setBcc(contactList);
        m1.setDateTime(new Date(2019,04,16,16,41));
        m1.setSubject("Veliki pozdrav");
        m1.setContent("Ovo je primer neke email poruke");
        Message m2 = new Message();
        m2.setId("123");
        m2.setFrom(getContacts().get(2));
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

    public static ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact a = new Contact();
        a.setId("1");
        a.setFirst("Marko");
        a.setLast("Marković");
        a.setEmail("markom@yahoo.com");
        Contact b = new Contact();
        b.setFirst("Jašar");
        b.setLast("Ahmedovski");
        b.setEmail("jasara@uns.ac.rs");
        b.setId("2");
        Contact c = new Contact();
        c.setFirst("Mitar");
        c.setLast("Mirić");
        c.setEmail("mitarm@gmail.com");
        c.setId("3");
        contacts.add(a);
        contacts.add(b);
        contacts.add(c);
        return contacts;
    }

    public static ArrayList<Folder> getFolders(){
        ArrayList<Folder> folders = new ArrayList<>();

        Folder folder1 = new Folder();
        folder1.setId("1");
        folder1.setName("Folder 1");
        folder1.setMessages(getEmails());


        Folder folder2 = new Folder();
        folder2.setId("2");
        folder2.setName("Folder 2");
        folder2.setMessages(getEmails());

        Folder drafts = new Folder();
        drafts.setId("3");
        drafts.setName("Drafts");
        drafts.setMessages(getEmails());

        Folder sent = new Folder();
        sent.setId("4");
        sent.setName("Sent");
        sent.setMessages(getEmails());

        folders.add(folder1);
        folders.add(folder2);
        folders.add(drafts);
        folders.add(sent);


        return folders;
    }
}
