package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactService {
    @GET("get/contacts")
    Call<List<Message>> getAllContacts();
}
