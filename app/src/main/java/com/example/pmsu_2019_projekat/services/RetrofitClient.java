package com.example.pmsu_2019_projekat.services;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    //192.168.1.6 Jovan stavio tu adresu
    //10.0.2.2 emulator adresa
    private static final String BASE_URL ="http://10.0.2.2:8080/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
