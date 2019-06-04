package com.example.pmsu_2019_projekat.activities;

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
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateContactActivity extends AppCompatActivity {

    private Contact newContact;

    private static final Pattern email =
            Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]");
//            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
//                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );

    private TextInputEditText textEmail;
    private TextInputEditText textName;
    private TextInputEditText textSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);


        textEmail = (TextInputEditText) findViewById(R.id.create_contact_email);
        textName = (TextInputEditText) findViewById(R.id.create_contact_name);
        textSurname = (TextInputEditText) findViewById(R.id.create_contact_surname);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_contact_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;
        actionbar.setTitle("Create Contact");
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
               // message = "Save";
                if(validateEmail() && validateName() && validateSurname()){
                   // saveContact("Save");
                    message = "Save";
                    break;
                }

            case R.id.create_contact_toolbar_cancel:
                //saveContact("Cancel");
                message = "Cancel";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

/*    private void saveContact(String operation){
        newContact = new Contact();
        newContact.setId(String.valueOf(125 + Data.contacts.size()));
        newContact.setEmail(textEmail.toString());
        newContact.setFirst(textName.toString());
        newContact.setLast(textSurname.toString());
        newContact.setDisplayName(textName.toString().toLowerCase());
        newContact.setFormat("text");
        newContact.setPhoto(null);
        if(operation == "Save"){
            Data.contacts.get(1).getEmail();
            ContactService contactService = RetrofitClient.getRetrofitInstance().create(ContactService.class);
            Call<Void> saveContact = contactService.addNewContact(newContact, "1");
            saveContact.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateContactActivity.this, "Uspesno dodat novi kontakt", Toast.LENGTH_LONG);

                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateContactActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }
    }*/

    private boolean validateEmail(){
        String emailInput = textEmail.getText().toString().trim();
        if(emailInput.isEmpty()){
            textEmail.setError("Prazno polje!");
            return false;
        }else if(email.matcher(emailInput).matches()){
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


/*    public void validateInputContact(MenuItem menuItem){
        if(!validateEmail() | !validateName() | !validateSurname()){
            return;
        }
        String inputT = "Email" + textEmail.getText().toString();
        inputT += "\n";
        inputT += "Name: " + textName.getText().toString();
        inputT += "\n";
        inputT += "Surname: " + textSurname.getText().toString();
        Toast.makeText(this, inputT, Toast.LENGTH_LONG);
    }*/

    //private void createContact(String operation){

    //    newContact = new Contact();
    //    newContact.setId(String.valueOf(125 + Data.contacts.size()));
    //    SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
    //    String loggedAccount = sharedPreferences.getString("username", "");

    //    newContact.setFirst(textName);
    //    newContact.setLast(textSurname);
    //    newContact.setEmail(textEmail);
    //    if(operation == "Save"){
    //        ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
    //        Call<Void> addContact = service.addNewContact(newContact);
    //        addContact.enqueue(new Callback<Void>() {
    //            @Override
    //            public void onResponse(Call<Void> call, Response<Void> response) {
    //                Toast.makeText(CreateContactActivity.this, "Uspesno kreiran kontakt", Toast.LENGTH_LONG);

    //                finish();
    //            }

    //            @Override
    //            public void onFailure(Call<Void> call, Throwable t) {
    //                Toast.makeText(CreateContactActivity.this, "Nemozeeeee!!!", Toast.LENGTH_LONG);
    //                finish();
    //            }
    //        });
    //    }else if(operation == "Cancel"){
    //        finish();
    //    }

    //}

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
