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

    @GET("messages")
    Call<List<Message>> getAllEmails();

    @GET("messages/byUser/{username}")
    Call<List<Message>> getEmailsByUsername(@Path("username") String username);

    @GET("emails/{accountId}")
    Call<List<Message>> getEmailsByAccount(@Path("accountId") String accountId);

    @POST("messages/send/{username}")
    Call<Void> sendEmail(@Body Message email,@Path("username") String accountUsername);

    @DELETE("messages/{id}")
    Call<Void> deleteEmail(@Path("id") String id);
}
