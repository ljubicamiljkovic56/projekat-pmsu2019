package com.example.pmsu_2019_projekat.services;

import com.example.pmsu_2019_projekat.model.Folder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FolderService {

    @GET("folders")
    Call<List<Folder>> getAllFolders();

    @GET("folders/byAccount/{accountId}")
    Call<Folder> getFoldersByAccount(@Path("accountId") String accountId);

    @POST("add/folder/{accountId}")
    Call<Void> addNewFolder(@Body Folder folder, @Path("accountId") String accountId);

    @PUT("update/folder/{id}")
    Call<Void> updateFolder(@Body Folder folder, @Path("id") String folderId);

    @DELETE("delete/folder/{id}")
    Call<Void> deleteFolder(@Path("id") String id);
}
