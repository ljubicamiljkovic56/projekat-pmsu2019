package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.User;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private User newUser;
    private TextInputEditText textName;
    private TextInputEditText textLastname;
    private TextInputEditText textUsername;
    private TextInputEditText textPassword;
    private TextInputEditText textRepeatPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Registration");


        textName = (TextInputEditText) findViewById(R.id.register_firstname);
        textLastname = (TextInputEditText) findViewById(R.id.register_lastname);
        textUsername = (TextInputEditText) findViewById(R.id.register_username);
        textPassword = (TextInputEditText) findViewById(R.id.register_password);
        textRepeatPassword = (TextInputEditText) findViewById(R.id.register_password_repeat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.register_save) {
            if(validateName() && validateSurname() && validateUsername() && validatePassword() && validateRepeatPassword()){
                makeUser();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        return;
    }

    private void makeUser(){

        newUser = new User();
        newUser.setFirstname(textName.getText().toString());
        newUser.setLastname(textLastname.getText().toString());
        newUser.setUsername(textUsername.getText().toString());
        newUser.setPassword(textRepeatPassword.getText().toString());


        UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
        Call<Void> registerUser = service.registerUser(newUser);
        registerUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegisterActivity.this, "Uspesno ste se registrovali", Toast.LENGTH_LONG);
                Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                return;
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Registracija neuspesna", Toast.LENGTH_LONG);
                return;
            }
        });

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
