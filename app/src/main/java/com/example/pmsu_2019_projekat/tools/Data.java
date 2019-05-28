package com.example.pmsu_2019_projekat.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.activities.ContactActivity;
import com.example.pmsu_2019_projekat.activities.ContactsActivity;
import com.example.pmsu_2019_projekat.activities.EmailsActivity;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Data {

    public static List<Account> accounts;
    public static List<Message> emails;
    public static List<Contact> contacts;
    public static List<Folder> folders;
    public static List<Folder> defaultFolders;
    public static Account loggedInUser;

    public Data(String username){
        accounts = new ArrayList<Account>();
        emails = new ArrayList<Message>();
        contacts = new ArrayList<Contact>();
        folders = new ArrayList<Folder>();
        defaultFolders = new ArrayList<Folder>();
        getAccounts();
        getLoggedInUser(username);
        getContactsByAccountID();
        getDefaultFolders();
        getFoldersByAccountID();
    }

    private void getLoggedInUser(String username) {
        for(Account a : accounts)
            if(a.getUsername().equals(username))
                loggedInUser = a;
    }

    public void getAccounts(){
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
    }

    public static void getContactsByAccountID(){
        ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
        Call<List<Contact>> call = service.getContactsByAccount(loggedInUser.getId());
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts = response.body();
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
            }
        });
    }

    public static void getFoldersByAccountID(){
        FolderService service = RetrofitClient.getRetrofitInstance().create(FolderService.class);
        Call<List<Folder>> call = service.getFoldersByAccount(loggedInUser.getId());
        call.enqueue(new Callback<List<Folder>>() {
            @Override
            public void onResponse(Call<List<Folder>> call, Response<List<Folder>> response) {
                folders.clear();
                folders.addAll(defaultFolders);
                folders.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Folder>> call, Throwable t) {
                Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
            }
        });
    }
    
    public static void getDefaultFolders(){
        
        Folder drafts = new Folder();
        drafts.setId("3");
        drafts.setName("Drafts");
        drafts.setMessages(new ArrayList<Message>());

        Folder sent = new Folder();
        sent.setId("4");
        sent.setName("Sent");
        sent.setMessages(new ArrayList<Message>());

        defaultFolders.add(drafts);
        defaultFolders.add(sent);

    }

    /*
    public static ArrayList<Message> getEmails(){
        emails = new ArrayList<>();
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
        contacts = new ArrayList<>();
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
        folders = new ArrayList<>();

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
    }*/
}
