package com.example.pmsu_2019_projekat.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.ContactAdapter;
import com.example.pmsu_2019_projekat.adapters.EmailAdapter;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsActivity extends NavigationActivity{

    ProgressDialog progressDialog;
    public static List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        progressDialog = new ProgressDialog(ContactsActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.contacts_toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Contacts");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* pre bilo
        ListView contactsList = findViewById(R.id.contacts_list_view);
        ContactAdapter contactAdapter = new ContactAdapter(this);
        contactsList.setOnItemClickListener(new ContactsItemClickListener());
        contactsList.setAdapter(contactAdapter);
        */

        ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
        Call<List<Contact>> call = service.getAllContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                progressDialog.dismiss();
                contacts = response.body();
                generateDataList(contacts);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ContactsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.d("Ovo je tvoja greska:", "Greska: " + t.getMessage());
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.contacts_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, CreateContactActivity.class);
                startActivity(intent);
            }
        });
    }


    private void generateDataList(List<Contact> csList) {
        ListView contactsList = findViewById(R.id.contacts_list_view);
        ContactAdapter cAdapter = new ContactAdapter(this, csList);
        contactsList.setOnItemClickListener(new ContactsItemClickListener());
        contactsList.setAdapter(cAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contacts_create) {
            Toast.makeText(this, "Create contact selected", Toast.LENGTH_LONG).show();
            Intent a = new Intent(ContactsActivity.this, CreateContactActivity.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }


    private class ContactsItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Contact contact = (Contact) parent.getAdapter().getItem(position);
            Intent intent = new Intent(ContactsActivity.this, ContactActivity.class);
            intent.putExtra("Contact", contact);
            startActivity(intent);
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
