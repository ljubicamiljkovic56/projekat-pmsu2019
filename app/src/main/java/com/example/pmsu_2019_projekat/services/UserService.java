package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @FormUrlEncoded
    @POST("users/loginUser")
    Call<Void> loginUser(@Field("username") String username, @Field("password") String password);

    @POST("users/registrationUser")
    Call<Void> registerUser(@Body User user);
}
