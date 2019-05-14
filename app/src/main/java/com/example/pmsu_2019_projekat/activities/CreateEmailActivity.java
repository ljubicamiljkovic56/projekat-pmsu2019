package com.example.pmsu_2019_projekat.activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;

import java.util.regex.Pattern;

import static com.example.pmsu_2019_projekat.R.*;

public class CreateEmailActivity extends AppCompatActivity {

    private static final Pattern email =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );

    private TextInputEditText textTo;
    private TextInputEditText textCc;
    private TextInputEditText textBcc;
    private TextInputEditText textSubject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_create_email);


        textTo = (TextInputEditText) findViewById(id.email_receiver);
        textCc = (TextInputEditText) findViewById(id.email_cc);
        textBcc = (TextInputEditText) findViewById(id.email_bcc);
        textSubject = (TextInputEditText) findViewById(id.email_subject);

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
                message = "Send";
                break;
            case id.toolbar_cancel:
                message = "Cancel";
                break;
        }
        Toast.makeText(this, message + "  selected", Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
    }

    private boolean validateEmail(){

        String emailInput = textTo.getText().toString().trim();

        if(emailInput.isEmpty()){
            textTo.setError("Prazno polje!");
            return false;
        }else  if(email.matcher(emailInput).matches()){
            textTo.setError("Niste dobro uneli email");
            return false;
        }else {
            textTo.setError(null);
            return true;
        }
    }
    private boolean validateCC(){

        String emailInput = textCc.getText().toString().trim();

        if(emailInput.isEmpty()){
            textCc.setError("Prazno polje!");
            return false;
        }else  if(email.matcher(emailInput).matches()){
            textCc.setError("Niste dobro uneli email");
            return false;
        }else {
            textCc.setError(null);
            return true;
        }
    }

    private boolean validateBCC(){

        String emailInput = textBcc.getText().toString().trim();

        if(emailInput.isEmpty()){
            textBcc.setError("Prazno polje!");
            return false;
        }else  if(email.matcher(emailInput).matches()){
            textBcc.setError("Niste dobro uneli email");
            return false;
        }else {
            textBcc.setError(null);
            return true;
        }
    }

    private boolean validateSubject(){
        String subjectInput = textSubject.getText().toString().trim();
        if(subjectInput.length() > 20){
            textSubject.setError("Subject predugacak!");
            return false;
        }else {
            textSubject.setError(null);
            return true;
        }
    }

    public void validateInput(MenuItem menuItem){
        if(!validateEmail() | !validateCC() | !validateBCC() | !validateSubject()){
            return;
        }
        String inputT = "Email" + textTo.getText().toString();
        inputT += "\n";
        inputT += "CC: " + textCc.getText().toString();
        inputT += "\n";
        inputT += "BCC: " + textBcc.getText().toString();
        inputT += "\n";
        inputT += "Subject: " + textSubject.getText().toString();
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
