package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @FormUrlEncoded
    @POST("users/loginUser")
    Call<Void> loginUser(@Field("username") String username, @Field("password") String password);

    @POST("users/registrationUser")
    Call<Void> registerUser(@Body User user);

    @PUT("users/addAccaunt/{username}")
    Call<Void> addAccountToUser(@Body Account account, @Path("username") String username);

    @FormUrlEncoded
    @POST("users/getUser")
    Call<User> getUser(@Field("username") String username, @Field("password") String password);

    @PUT("users/updateUser")
    Call<Void> updateUser(@Body User user);
}
