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

import java.util.regex.Pattern;

public class CreateContactActivity extends AppCompatActivity {

    private static final Pattern email =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );

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
                message = "Save";
                break;
            case R.id.create_contact_toolbar_cancel:
                message = "Cancel";
                break;
        }
        Toast.makeText(this, message + "  selected", Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

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


    public void validateInputContact(MenuItem menuItem){
        if(!validateEmail() | !validateName() | !validateSurname()){
            return;
        }
        String inputT = "Email" + textEmail.getText().toString();
        inputT += "\n";
        inputT += "Name: " + textName.getText().toString();
        inputT += "\n";
        inputT += "Surname: " + textSurname.getText().toString();
        Toast.makeText(this, inputT, Toast.LENGTH_LONG);
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
