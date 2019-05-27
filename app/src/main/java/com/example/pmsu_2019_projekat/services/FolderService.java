package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Folder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FolderService {

    @GET("get/folders")
    Call<List<Folder>> getAllFolders();

    @GET("get/folders/{accountId}")
    Call<List<Folder>> getFoldersByAccount(@Path("accountId") String accountId);
}
