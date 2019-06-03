package com.example.pmsu_2019_projekat.activities;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFolderActivity extends AppCompatActivity {

    Toolbar toolbar;

    private ArrayList<Message> messages = new ArrayList<>();
    private Folder newFolder;
    public static Folder f;

    private TextInputEditText textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_folder);

        textName = (TextInputEditText) findViewById(R.id.folder_name);

        toolbar = (Toolbar) findViewById(R.id.create_folder_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;
        actionbar.setTitle("Create Folder");

        f = (Folder) getIntent().getSerializableExtra("Folder");
        if(f != null){
            actionbar.setTitle(f.getName());
          //  EditText editId = findViewById(R.id.folder_id);
          //  editId.append("ID: " + f.getId());
            EditText editName = findViewById(R.id.folder_name);
            editName.append("Name: " + f.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_folder_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case R.id.toolbar_save:
                if(validateName()){
                    saveFolder("Save");
                    message = "Save";
                    break;
                } else {
                    break;
                }

            case R.id.toolbar_cancel:
                saveFolder("Cancel");
                message = "Cancel";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

    private void saveFolder(String operation){
        newFolder = new Folder();
        newFolder.setId(String.valueOf(125 + Data.folders.size()));
        newFolder.setName(textName.getText().toString());
        newFolder.setMessages(messages);
        newFolder.setSubfolders(null);
        newFolder.setParentFolder(null);
        newFolder.setRules(null);
        if(operation == "Save"){
            Data.folders.get(1).getName();
            FolderService folderService = RetrofitClient.getRetrofitInstance().create(FolderService.class);
            Call<Void> saveFolder = folderService.addNewFolder(newFolder);
            saveFolder.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateFolderActivity.this, "Uspesno dodat novi folder", Toast.LENGTH_LONG);
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateFolderActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }else if(operation == "Cancel"){
            Data.folders.get(0).getName();
            finish();
        }

    }


    private boolean validateName(){
        String nameInput = textName.getText().toString().trim();

        if(nameInput.isEmpty()){
            textName.setError("Ime foldera ne sme biti prazno!");
            return false;
        }else if(nameInput.length() > 20){
            textName.setError("Predugacko ime!");
            return false;
        }else {
            textName.setError(null);
            return true;
        }
    }

    public void validateInputName(MenuItem menuItem){
        if(!validateName()){
            return;
        }
        String inputT = "Name" + textName.getText().toString();
        Toast.makeText(this, inputT, Toast.LENGTH_LONG);
    }



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
