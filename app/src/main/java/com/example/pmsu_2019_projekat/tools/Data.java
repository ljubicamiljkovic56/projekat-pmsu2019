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
import com.example.pmsu_2019_projekat.model.User;
import com.example.pmsu_2019_projekat.services.AccountService;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.services.UserService;

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
    public static String loggedInUser;

    public Data(String username){
        accounts = new ArrayList<Account>();
        emails = new ArrayList<Message>();
        contacts = new ArrayList<Contact>();
        folders = new ArrayList<Folder>();
        getAccounts();
        getContactsByUsername();
        getFoldersByAccountID();
    }

    public static void getAccounts(){
        AccountService service = RetrofitClient.getRetrofitInstance().create(AccountService.class);
        Call<List<Account>> call = service.getAccountByUser(loggedInUser);
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                accounts = response.body();
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
            }
        });
    }

    public static void getContactsByUsername(){
        ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
        Call<List<Contact>> call = service.getContactsByUsername(loggedInUser);
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
        folders.clear();
        for(Account a : accounts){
            FolderService service = RetrofitClient.getRetrofitInstance().create(FolderService.class);
            Call<Folder> call = service.getFoldersByAccount(a.getId());
            call.enqueue(new Callback<Folder>() {
                @Override
                public void onResponse(Call<Folder> call, Response<Folder> response) {
                    folders.add(response.body());
                }

                @Override
                public void onFailure(Call<Folder> call, Throwable t) {
                    Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
                }
            });
        }
    }
}
