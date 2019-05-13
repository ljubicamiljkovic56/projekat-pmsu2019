package com.example.pmsu_2019_projekat.activities;

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

    private TextInputLayout textTo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_create_email);


        textTo = findViewById(id.email_receiver);

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
        String emailInput = textTo.getEditText().getText().toString().trim();

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
    public void validateInput(View v){
        if(!validateEmail()){
            return;
        }
        String inputT = "Email" + textTo.getEditText().getText().toString();
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
