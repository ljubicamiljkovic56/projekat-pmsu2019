package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.regex.Pattern;

import static com.example.pmsu_2019_projekat.R.*;

public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button loginButton;
    SharedPreferences sharedPreferences;
    Intent intent;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);

        user = (TextInputEditText) findViewById(id.username_edit);
        pass = (TextInputEditText) findViewById(id.password_edit);
        loginButton = (Button) findViewById(id.btnStartEmails);
        sharedPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        intent = new Intent(LoginActivity.this,EmailsActivity.class);
        if(sharedPreferences.contains("username")){
            startActivity(intent);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                for(Account a : Data.getInstance().accounts){
                    if(a.getUsername().equals(username) && !a.getPassword().equals(password)){
                        pass.setError("Pogrešna lozinka");
                        break;
                    }else if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Login uspesan",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    }else if(!a.getUsername().equals(username)){
                        user.setError("Nepostojeći korisnik");
                    }else{
                        Toast.makeText(getApplicationContext(),"Niste uneli dobre informacije",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

 /*   public void btnStartEmailsActivity(View v){
        Intent i = new Intent(LoginActivity.this, EmailsActivity.class);
        startActivity(i);
        finish();
    }*/

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity();
        }else{
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
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
