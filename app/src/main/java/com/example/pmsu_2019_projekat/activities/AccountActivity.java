package com.example.pmsu_2019_projekat.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.services.AccountService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.services.UserService;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    private boolean add;
    private Account accountToEdit;
    private Account newAccount;
    private static final Pattern email =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\+]{1,256}" +
                    "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +")" );
    private TextInputEditText textEmail;
    private TextInputEditText textDisplayName;
    private TextInputEditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        textEmail = (TextInputEditText) findViewById(R.id.account_email);
        textDisplayName = (TextInputEditText) findViewById(R.id.account_display_name);
        textPassword = (TextInputEditText) findViewById(R.id.account_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.account_activity_toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Account");

        add = (Boolean) getIntent().getSerializableExtra("Add");
        accountToEdit = (Account) getIntent().getSerializableExtra("accountToEdit");

        if(accountToEdit != null){
            actionBar.setTitle("Edit Account");
            textEmail.setText(accountToEdit.getUsername());
            textDisplayName.setText(accountToEdit.getDisplayName());
            textPassword.setText(accountToEdit.getPassword());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.account_toolbar_save:
                if (validateDisplayName() && validateEmail() && validatePassword()) {
                    saveAccount();
                    break;
                } else {
                    break;
                }

            case R.id.account_toolbar_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", deleteDialogClickListener)
                        .setNegativeButton("No", deleteDialogClickListener).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveAccount(){
        if(add == true){
            SharedPreferences sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
            String user = sharedPreferences.getString("username", "");

            newAccount = new Account();
            newAccount.setDisplayName(textDisplayName.getText().toString());
            newAccount.setUsername(textEmail.getText().toString());
            newAccount.setPassword(textPassword.getText().toString());

            UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
            Call<Void> addAccount = service.addAccountToUser(newAccount, user);
            addAccount.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(AccountActivity.this, "Uspesno ste dodali nalog", Toast.LENGTH_LONG);
                    Data.getAccounts();
                    Data.getFoldersByAccountID();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AccountActivity.this, "Neuspesno dodavanje!", Toast.LENGTH_LONG);
                }
            });

        }else{
            accountToEdit.setUsername(textEmail.getText().toString());
            accountToEdit.setDisplayName(textDisplayName.getText().toString());
            accountToEdit.setPassword(textPassword.getText().toString());

            AccountService service = RetrofitClient.getRetrofitInstance().create(AccountService.class);
            Call<Void> updateAccount = service.updateAccount(accountToEdit);
            updateAccount.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(AccountActivity.this, "Uspesno ste izmenili nalog", Toast.LENGTH_LONG);
                    Data.getAccounts();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(AccountActivity.this, "Neuspesna izmena!", Toast.LENGTH_LONG);
                }
            });

        }

    }

    DialogInterface.OnClickListener deleteDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    AccountService service = RetrofitClient.getRetrofitInstance().create(AccountService.class);
                    Call<Void> deleteAccount = service.deleteAccount(accountToEdit.getId());
                    deleteAccount.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(AccountActivity.this, "Nalog izbrisan", Toast.LENGTH_LONG);
                            Data.getAccounts();
                            Data.getFoldersByAccountID();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(AccountActivity.this, "Something went wrong", Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(AccountActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

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

    private boolean validatePassword(){
        String passwordInput = textPassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            textPassword.setError("Morate uneti password!");
            return false;
        }else {
            textPassword.setError(null);
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
