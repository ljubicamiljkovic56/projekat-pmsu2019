package com.example.pmsu_2019_projekat.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.services.UserService;
import com.example.pmsu_2019_projekat.tools.Data;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_2019_projekat.R.*;

public class LoginActivity extends AppCompatActivity {

    private static EditText user, pass;
    private Button loginButton;
    private TextView registerText;
    private SharedPreferences sharedPreferences;
    private static Intent intent;
    private ProgressDialog progressDialog;

    public static String loggedIn;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);

        user = (TextInputEditText) findViewById(id.username_edit);
        pass = (TextInputEditText) findViewById(id.password_edit);
        loginButton = (Button) findViewById(id.btnStartEmails);
        registerText = findViewById(id.register_text_view);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String callerActivity = (String) getIntent().getSerializableExtra("activityCaller");
        if(callerActivity != null && callerActivity.equals("ProfileActivity")){
            intent = new Intent(LoginActivity.this,ProfileActivity.class);
        }else{
            intent = new Intent(LoginActivity.this,EmailsActivity.class);
        }
        /*if(sharedPreferences.contains("username")){
            new Data(sharedPreferences.getString("username", ""));
            startActivity(intent);
        }*/

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                String username = user.getText().toString();
                String password = pass.getText().toString();
                String[] i = {username,password};
                //LogIn(username, password);
                loggedIn = null;
                try {
                    loggedIn = new AsyncLogin().execute(i).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(loggedIn != null && loggedIn == "true"){
                    progressDialog.dismiss();
                    new Data(username);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login uspesan",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Niste uneli dobre informacije",Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity();
        }else{
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    /*private static void LogIn(String username, String password) {
        loggedIn = false;
        UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
        Call<Void> call = service.loginUser(username, password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String username = user.getText().toString();
                new Data(username);
                loggedIn = true;
                return;
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ERROR:", "Greska: " + t.getMessage());
                loggedIn = false;
                return;
            }
        });
    }*/

    private class AsyncLogin extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];
            UserService service = RetrofitClient.getRetrofitInstance().create(UserService.class);
            Call<Void> call = service.loginUser(username, password);
            try {
                call.execute();
                loggedIn = "true";
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("ERROR:", "Greska: " + e.getMessage());
                loggedIn = "false";
            }
            return loggedIn;
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

/*                 for(Account a : Data.accounts){
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
                }*/