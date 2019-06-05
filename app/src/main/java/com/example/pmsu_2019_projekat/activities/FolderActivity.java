package com.example.pmsu_2019_projekat.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.EmailAdapter;
import com.example.pmsu_2019_projekat.adapters.FolderAdapter;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FolderActivity extends AppCompatActivity {

    public static Folder folder1;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        

        folder1 = (Folder) getIntent().getSerializableExtra("Folder");
        toolbar = (Toolbar) findViewById(R.id.folder_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(folder1.getName());
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        List foldersAndMessagesList = new ArrayList();
        if(folder1.getSubfolders() != null)
            foldersAndMessagesList.addAll(folder1.getSubfolders());
        if(folder1.getMessages() != null)
            foldersAndMessagesList.addAll(folder1.getMessages());

        ListView folderList = findViewById(R.id.folder_list_view);
        FolderAdapter folderAdapter = new FolderAdapter(this, foldersAndMessagesList);
        folderList.setOnItemClickListener(new FolderItemClickListener());
        folderList.setAdapter(folderAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.folder_menu, menu);
        MenuItem editButton = menu.findItem(R.id.folder_toolbar_edit);
        MenuItem deleteButton = menu.findItem(R.id.folder_toolbar_delete);
        if(folder1.getName().equals("Sent") || folder1.getName().equals("Drafts")){
            editButton.setVisible(false);
            deleteButton.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.folder_toolbar_edit:
                Intent i = new Intent(FolderActivity.this, CreateFolderActivity.class);
                i.putExtra("Add", false);
                i.putExtra("folderToEdit", folder1);
                startActivity(i);
                break;
            case R.id.folder_toolbar_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", deleteDialogClickListener)
                        .setNegativeButton("No", deleteDialogClickListener).show();
                break;

        }
        return  super.onOptionsItemSelected(item);
    }

    private class FolderItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getAdapter().getItem(position) instanceof Message){
                Message email = (Message) parent.getAdapter().getItem(position);
                Intent intent = new Intent(FolderActivity.this, EmailActivity.class);
                intent.putExtra("Email", email);
                startActivity(intent);
            }else if (parent.getAdapter().getItem(position) instanceof Folder){
                Folder folder = (Folder) parent.getAdapter().getItem(position);
                Intent intent = new Intent(FolderActivity.this, FolderActivity.class);
                intent.putExtra("Folder", folder);
                startActivity(intent);
            }
        }
    }

    DialogInterface.OnClickListener deleteDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    FolderService service = RetrofitClient.getRetrofitInstance().create(FolderService.class);
                    Call<Void> deleteFolder = service.deleteFolder(folder1.getId());
                    deleteFolder.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(FolderActivity.this, "Folder deleted", Toast.LENGTH_LONG);
                            Data.getFoldersByAccountID();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(FolderActivity.this, "Something went wrong", Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(FolderActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
