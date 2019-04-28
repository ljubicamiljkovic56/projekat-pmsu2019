package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
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

import static com.example.pmsu_2019_projekat.R.*;

public class EmailsActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_emails);

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

        ListView emailsList = findViewById(id.emails_list_view);
        EmailAdapter eAdapter = new EmailAdapter(this);
        emailsList.setOnItemClickListener(new EmailsItemClickListener());
        emailsList.setAdapter(eAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.emails_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmailsActivity.this, CreateEmailActivity.class);
                startActivity(intent);
            }
        });
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
