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
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateContactActivity extends AppCompatActivity {

    private Contact newContact;
    private boolean add;
    private Contact contactToEdit;

    private static final Pattern email =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );

    private TextInputEditText textEmail;
    private TextInputEditText textName;
    private TextInputEditText textSurname;
    private TextInputEditText textDisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);


        textEmail = (TextInputEditText) findViewById(R.id.create_contact_email);
        textName = (TextInputEditText) findViewById(R.id.create_contact_name);
        textSurname = (TextInputEditText) findViewById(R.id.create_contact_surname);
        textDisplayName = (TextInputEditText) findViewById(R.id.create_contact_displayName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_contact_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;
        actionbar.setTitle("Create Contact");

        add = (Boolean) getIntent().getSerializableExtra("Add");

        contactToEdit = (Contact) getIntent().getSerializableExtra("contactToEdit");

        if(contactToEdit != null){
            actionbar.setTitle("Edit Contact");
            textName.setText(contactToEdit.getFirst());
            textSurname.setText(contactToEdit.getLast());
            textEmail.setText(contactToEdit.getEmail());
            textDisplayName.setText(contactToEdit.getDisplayName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case R.id.create_contact_toolbar_save:
                if(validateEmail() && validateName() && validateSurname() && validateDisplayName()){
                    createContact();
                    break;
                }else {
                    break;
                }

            case R.id.create_contact_toolbar_cancel:
                message = "Canceled";
                finish();
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

    private void createContact(){
        if(add == true){
            SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
            String loggedAccount = sharedPreferences.getString("username", "");
            String userId = null;
            for(Account a : Data.accounts){
                if(a.getUsername().equals(loggedAccount))
                    userId = a.getId();
            }

            newContact = new Contact();
            newContact.setId(String.valueOf(125 + Data.contacts.size()));
            newContact.setEmail(textEmail.getText().toString());
            newContact.setFirst(textName.getText().toString());
            newContact.setLast(textSurname.getText().toString());
            newContact.setDisplayName(textDisplayName.getText().toString());

            ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
            Call<Void> addContact = service.addNewContact(newContact, userId);
            addContact.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateContactActivity.this, "Uspesno kreiran kontakt", Toast.LENGTH_LONG);
                    Data.getContactsByUsername();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateContactActivity.this, "Nemozeeeee!!!", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }else if(add == false){
            contactToEdit.setFirst(textName.getText().toString());
            contactToEdit.setLast(textSurname.getText().toString());
            contactToEdit.setEmail(textEmail.getText().toString());
            contactToEdit.setDisplayName(textDisplayName.getText().toString());

            ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
            Call<Void> updateContact = service.updateContact(contactToEdit, contactToEdit.getId());
            updateContact.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateContactActivity.this, "Uspesno izmenjen kontakt", Toast.LENGTH_LONG);
                    Data.getContactsByUsername();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateContactActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }
    }

    private boolean validateEmail(){
        String emailInput = textEmail.getText().toString().trim();
        Matcher m = email.matcher(emailInput);

        if(emailInput.isEmpty()){
            textEmail.setError("Prazno polje!");
            return false;
        }else if(!m.matches()){
            textEmail.setError("Niste dobro uneli email");
            return false;
        }else {
            textEmail.setError(null);
            return true;
        }
    }

    private boolean validateName(){
        String nameInput = textName.getText().toString().trim();
        if(nameInput.isEmpty()){
            textName.setError("Ime ne sme biti prazno!");
            return false;
        }else {
            textName.setError(null);
            return true;
        }
    }

    private boolean validateSurname(){
        String surnameInput = textSurname.getText().toString().trim();
        if(surnameInput.isEmpty()){
            textSurname.setError("Prezime ne sme biti prazno!");
            return false;
        }else {
            textSurname.setError(null);
            return true;
        }
    }

    private boolean validateDisplayName(){
        String displayNameInput = textDisplayName.getText().toString().trim();
        if(displayNameInput.isEmpty()){
            textDisplayName.setError("Ovo polje ne sme biti prazno!");
            return false;
        }else {
            textDisplayName.setError(null);
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
