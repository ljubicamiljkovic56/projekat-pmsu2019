package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmailService {

    @GET("get/emails")
    Call<List<Message>> getAllEmails();

    @GET("get/emails/{accountId}")
    Call<List<Message>> getEmailsByAccount(@Path("accountId") String accountId);

    @POST("add/email")
    Call<Void> addNewEmail(@Body Message email);

    @DELETE("delete/email/{id}")
    Call<Void> deleteEmail(@Path("id") String id);
}
