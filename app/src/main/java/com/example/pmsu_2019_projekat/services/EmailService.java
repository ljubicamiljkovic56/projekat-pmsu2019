package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface EmailService {

    @GET("get/emails")
    Call<List<Message>> getAllEmails();

    @POST("add/email")
    Call<Void> addNewEmail(@Body Message email);
}
