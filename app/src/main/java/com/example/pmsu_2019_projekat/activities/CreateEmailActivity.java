package com.example.pmsu_2019_projekat.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_2019_projekat.R.*;

public class CreateEmailActivity extends AppCompatActivity {

    private Message newEmail;
    private static final Pattern emailPattern=
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );

    private TextInputEditText textTo;
    private TextInputEditText textCc;
    private TextInputEditText textBcc;
    private TextInputEditText textSubject;
    private EditText textContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_create_email);


        textTo = (TextInputEditText) findViewById(id.email_receiver);
        textCc = (TextInputEditText) findViewById(id.email_cc);
        textBcc = (TextInputEditText) findViewById(id.email_bcc);
        textSubject = (TextInputEditText) findViewById(id.email_subject);
        textContent = (EditText) findViewById(id.email_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.create_email_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Email");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_email_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case id.toolbar_send:
                if(validateTo() && validateCc() && validateBcc()){
                    //sendEmail("Send");
                    message = "Sent";
                    break;
                }else {
                    break;
                }
            case id.toolbar_cancel:
                //sendEmail("Cancel");
                message = "Canceled";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

    private static Contact contactFinder(String contactEmail){
        List<Contact> csList = Data.contacts;
        if(csList != null){
            for(Contact c : csList){
                if(c.getEmail().equals(contactEmail)){
                    return c;
                }
            }
        }
        return null;
    }

    /*private void sendEmail(String operation){
        newEmail = new Message();
        SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        String loggedAccount = sharedPreferences.getString("username", "");
        Contact from = new Contact();
        from.setEmail(loggedAccount);
        newEmail.setFrom(from);

        ArrayList<Contact> to = new ArrayList<>();
        Contact fromContact = contactFinder(textTo.getText().toString().trim());
        if(fromContact != null){
            to.add(fromContact);
        }else{
            fromContact = new Contact();
            fromContact.setEmail(textTo.getText().toString().trim());
            to.add(fromContact);
        }
        newEmail.setTo(to);
        ArrayList<Contact> cc = new ArrayList<>();
        if(textCc.getText() != null){
            cc.add(contactFinder(textCc.getText().toString().trim()));
        }
        newEmail.setCc(cc);
        ArrayList<Contact> bcc = new ArrayList<>();
        if(textBcc.getText() != null){
            bcc.add(contactFinder(textBcc.getText().toString().trim()));
        }
        newEmail.setBcc(bcc);
        newEmail.setDateTime(new Date());
        newEmail.setSubject(textSubject.getText().toString());
        newEmail.setContent(textContent.getText().toString());
        if(operation == "Send"){
            Data.folders.get(1).getMessages().add(newEmail);
            EmailService service = RetrofitClient.getRetrofitInstance().create(EmailService.class);
            Call<Void> addEmail = service.addNewEmail(newEmail);
            addEmail.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(CreateEmailActivity.this, "Uspesno poslat email", Toast.LENGTH_LONG);

                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CreateEmailActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG);
                    finish();
                }
            });
        }else if(operation == "Cancel"){
            Data.defaultFolders.get(0).getMessages().add(newEmail);
            finish();
        }

    }*/

    private boolean validateTo(){

        String emailInput = textTo.getText().toString().trim();
        Matcher m = emailPattern.matcher(emailInput);

        if(emailInput.isEmpty()){
            textTo.setError("Prazno polje!");
            return false;
        }else  if(!m.matches()){
            textTo.setError("Niste dobro uneli email");
            return false;
        }else {
            textTo.setError(null);
            return true;
        }
    }
    private boolean validateCc(){

        String emailInputCC = textCc.getText().toString().trim();
        Matcher m = emailPattern.matcher(emailInputCC);

        if(textCc.getText().toString().equals("")) {
            textCc.setError(null);
            return true;
        }else if(!m.matches()){
            textCc.setError("Niste dobro uneli email");
            return false;
        }else {
            textCc.setError(null);
            return true;
        }
    }

    private boolean validateBcc() {
        String emailInputBCC = textBcc.getText().toString().trim();
        Matcher m2 = emailPattern.matcher(emailInputBCC);
        if (textBcc.getText().toString().equals("")) {
            textBcc.setError(null);
            return true;
        } else if (!m2.matches()) {
            textBcc.setError("Niste dobro uneli email");
            return false;
        } else {
            textBcc.setError(null);
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
