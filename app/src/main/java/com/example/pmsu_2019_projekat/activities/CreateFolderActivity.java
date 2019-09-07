package com.example.pmsu_2019_projekat.activities;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.Condition;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.model.Operation;
import com.example.pmsu_2019_projekat.model.Rule;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFolderActivity extends AppCompatActivity {

    Toolbar toolbar;

    private Folder newFolder;
    private TextInputEditText textName;
    private boolean add;
    private Spinner condition;
    private Spinner operation;
    private Folder folderToEdit;
    private String parentFolderId;
    private String folderAccount;

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

        add = (Boolean) getIntent().getSerializableExtra("Add");
        parentFolderId = (String) getIntent().getSerializableExtra("ParentFolder");
        folderAccount = (String) getIntent().getSerializableExtra("folderAccount");

        condition = (Spinner) findViewById(R.id.spinnerCondition);
        operation = (Spinner) findViewById(R.id.spinnerOperation);

        folderToEdit = (Folder) getIntent().getSerializableExtra("folderToEdit");

        if(folderToEdit != null){
            actionbar.setTitle("Edit Folder");
            textName.setText(folderToEdit.getName());
            if(folderToEdit.getRules() != null && !folderToEdit.getRules().isEmpty()){
                condition.setSelection(Condition.toInt(folderToEdit.getRules().get(0).getCondition()));
                operation.setSelection(Operation.toInt(folderToEdit.getRules().get(0).getOperation()));
            }

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
        switch (item.getItemId()){
            case R.id.toolbar_save:
                if(validateName()){
                    saveFolder();
                    break;
                } else {
                    break;
                }

            case R.id.toolbar_cancel:
                finish();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void saveFolder(){
        Rule r = new Rule();
        r.setCondition(Condition.valueOf(condition.getSelectedItem().toString()));
        r.setOperation(Operation.valueOf(operation.getSelectedItem().toString()));
        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(r);

        if(add == true){
            SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
            String user = sharedPreferences.getString("username", "");

            newFolder = new Folder();
            newFolder.setName(textName.getText().toString());
            newFolder.setMessages(new ArrayList<Message>());
            newFolder.setRules(rules);
            FolderService folderService = RetrofitClient.getRetrofitInstance().create(FolderService.class);
            Call<Void> addFolder = folderService.addNewFolder(newFolder, parentFolderId, folderAccount);
            addFolder.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateFolderActivity.this, "Uspesno dodat novi folder", Toast.LENGTH_LONG);
                    Data.getFoldersByAccountID();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateFolderActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }else if(add == false){
            folderToEdit.setName(textName.getText().toString());
            folderToEdit.setRules(rules);
            FolderService folderService = RetrofitClient.getRetrofitInstance().create(FolderService.class);
            Call<Void> updateFolder = folderService.updateFolder(folderToEdit, folderToEdit.getId());
            updateFolder.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateFolderActivity.this, "Uspesno izmenjen folder", Toast.LENGTH_LONG);
                    Data.getFoldersByAccountID();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateFolderActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
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
