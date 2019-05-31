package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContactService {
    @GET("get/contacts")
    Call<List<Contact>> getAllContacts();

    @GET("get/contacts/{accountId}")
    Call<List<Contact>> getContactsByAccount(@Path("accountId") String accountId);

    @POST("add/contact/{accountId}")
    Call<Void> addNewContact(@Body Contact contact, @Path("accountId") String accountId);
}
