package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {
    @GET("contacts")
    Call<List<Contact>> getAllContacts();

    @GET("contacts/byUser/{username}")
    Call<List<Contact>> getContactsByUsername(@Path("username") String username);

    @POST("contacts/add/{username}")
    Call<Void> addNewContact(@Body Contact contact, @Path("username") String username);

    @PUT("contacts/update/{id}")
    Call<Void> updateContact(@Body Contact contact, @Path("id") String contactId);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") String id);
}
