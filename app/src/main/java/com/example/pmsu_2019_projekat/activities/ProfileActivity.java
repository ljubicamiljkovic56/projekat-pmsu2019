package com.example.pmsu_2019_projekat.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.model.User;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.services.UserService;
import com.example.pmsu_2019_projekat.tools.Data;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends NavigationActivity {

    private User user;
    private SharedPreferences sharedPreferences;
    private TextInputEditText textName;
    private TextInputEditText textLastname;
    private TextInputEditText textUsername;
    private TextInputEditText textPassword;
    private TextInputEditText textRepeatPassword;
    public static String userFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_activity_toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("My Profile");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        String[] i = {username, password};
        try {
            userFound = new ProfileActivity.AsyncFindUser().execute(i).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textName = (TextInputEditText) findViewById(R.id.profile_firstname);
        textLastname = (TextInputEditText) findViewById(R.id.profile_lastname);
        textUsername = (TextInputEditText) findViewById(R.id.profile_username);
        textPassword = (TextInputEditText) findViewById(R.id.profile_password);
        textRepeatPassword = (TextInputEditText) findViewById(R.id.profile_password_repeat);

        if(userFound == "userFound" && user != null){
            textName.setText(user.getFirstname());
            textLastname.setText(user.getLastname());
            textUsername.setText(user.getUsername());
            textPassword.setText(user.getPassword());
            textRepeatPassword.setText(user.getPassword());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //String message = "";
        switch (item.getItemId()){
            case R.id.profile_toolbar_logout:
                SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(this, "Logout", Toast.LENGTH_LONG).show();
                Intent a = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(a);
                break;
            case R.id.manage_accounts_btn:
                Intent b = new Intent(ProfileActivity.this, AccountsActivity.class);
                startActivity(b);
                break;
            case R.id.profile_toolbar_save:
                if(validateName() && validateSurname() && validateUsername() && validatePassword() && validateRepeatPassword()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Morate se odjaviti da bi prihvatili izmene").setPositiveButton("Da", changeDialogClickListener)
                            .setNegativeButton("Ne", changeDialogClickListener).show();

                }
        }

        return super.onOptionsItemSelected(item);
    }

    DialogInterface.OnClickListener changeDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    changeUser();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(ProfileActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void changeUser(){

        user.setFirstname(textName.getText().toString());
        user.setLastname(textLastname.getText().toString());
        user.setUsername(textUsername.getText().toString());
        user.setPassword(textRepeatPassword.getText().toString());

        UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
        Call<Void> updateUser = service.updateUser(user);
        updateUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ProfileActivity.this, "Uspesno izmenjen profil", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent h = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(h);
                finish();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Nesto nije u redu", Toast.LENGTH_LONG).show();
            }
        });

    }

    private class AsyncFindUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];
            UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
            Call<User> call = service.getUser(username, password);
            try {
                user = call.execute().body();
                return "userFound";
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ERROR:", "Greska: " + e.getMessage());
                return "";
            }
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
        String surnameInput = textLastname.getText().toString().trim();
        if(surnameInput.isEmpty()){
            textLastname.setError("Prezime ne sme biti prazno!");
            return false;
        }else {
            textLastname.setError(null);
            return true;
        }
    }

    private boolean validateUsername(){
        String usernameInput = textUsername.getText().toString().trim();
        if(usernameInput.isEmpty()){
            textUsername.setError(" Korisnicko ime ne sme biti prazno!");
            return false;
        }else if(usernameInput == "miki" || usernameInput == "kiki"){
            textUsername.setError("Ovo korisnicko ime se vec koristi!");
            return false;
        } else {
            textUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput = textPassword.getText().toString().trim();
        if(passwordInput.isEmpty()){
            textPassword.setError("Morate uneti password!");
            return false;
        }else if(passwordInput.length() < 4){
            textPassword.setError("Mora biti duze od 4 karaktera!");
            return false;
        }else {
            textPassword.setError(null);
            return true;
        }
    }

    private boolean validateRepeatPassword(){
        String passwordRepeatInput = textRepeatPassword.getText().toString().trim();
        String passwordInput = textPassword.getText().toString().trim();
        if(passwordRepeatInput.isEmpty()){
            textRepeatPassword.setError("Ponovite password!");
            return false;
        }else if(!passwordRepeatInput.equals(passwordInput)){
            textRepeatPassword.setError("Ne poklapa se sa poljem password!");
            return false;
        }else {
            textRepeatPassword.setError(null);
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
