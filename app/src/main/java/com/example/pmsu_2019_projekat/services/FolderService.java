package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Folder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FolderService {

    @GET("get/folders")
    Call<List<Folder>> getAllFolders();
}
