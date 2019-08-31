package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountService {

    @GET("accounts/getallaccount/{username}")
    Call<List<Account>> getAccountByUser(@Path("username") String username);
}
