package com.example.pmsu_2019_projekat.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.EmailAdapter;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_2019_projekat.R.*;

public class EmailsActivity extends NavigationActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ProgressDialog progressDialog;
    private List<Message> messagesList;
    private SharedPreferences sharedPreferences;
    private long interval = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_emails);
        handler = new Handler();


        progressDialog = new ProgressDialog(EmailsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Toolbar toolbar = (Toolbar) findViewById(id.emails_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(drawable.ic_menu_white_24dp);
        actionBar.setTitle("Inbox");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupSharedPreferences();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.emails_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmailsActivity.this, CreateEmailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void generateDataList() {
        ListView emailsList = findViewById(id.emails_list_view);
        EmailAdapter eAdapter = new EmailAdapter(this, messagesList);
        emailsList.setOnItemClickListener(new EmailsItemClickListener());
        emailsList.setAdapter(eAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emails_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.emails_create) {
            Toast.makeText(this, "Create email selected", Toast.LENGTH_LONG).show();
            Intent a = new Intent(EmailsActivity.this, CreateEmailActivity.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    private class EmailsItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Message email = (Message) parent.getAdapter().getItem(position);
            Intent intent = new Intent(EmailsActivity.this, EmailActivity.class);
            intent.putExtra("Email", email);
            startActivity(intent);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {
        if (key.equals("pref_sort")) {
            if(sharedPref.getString(key, "ascending").equals("ascending")){
                Collections.sort(messagesList, Message.MessageDateComparator);
                generateDataList();
            }else if(sharedPref.getString(key, "ascending").equals("descending")){
                Collections.sort(messagesList, Message.MessageDateComparatorDesc);
                generateDataList();
            }
        }else if(key.equals("pref_sync")){
            if(sharedPref.getBoolean(key,true) == false){
                stopRepeatingTask();
            }else{
                startRepeatingTask();
            }
        }
    }

    Runnable dataLoader = new Runnable() {
        @Override
        public void run() {
            try{
                String syncTimeStr = sharedPreferences.getString("pref_sync_list", "1");
                interval = TimeUnit.MINUTES.toMillis(Integer.parseInt(syncTimeStr));
                Toast toast = Toast.makeText(getApplicationContext(), "Syncing", Toast.LENGTH_SHORT);
                toast.show();

                messagesList = Data.getInstance().emails;

                EmailService service = RetrofitClient.getRetrofitInstance().create(EmailService.class);
                Call<List<Message>> call = service.getAllEmails();
                call.enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                        progressDialog.dismiss();
                        messagesList = response.body();
                        generateDataList();
                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(EmailsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
                    }
                });

            }finally {
                handler.postDelayed(dataLoader, interval);
            }
        }
    };

    void startRepeatingTask() {
        dataLoader.run();
    }

    void stopRepeatingTask(){
        handler.removeCallbacks(dataLoader);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startRepeatingTask();
        stopRepeatingTask();
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
        stopRepeatingTask();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }


}
