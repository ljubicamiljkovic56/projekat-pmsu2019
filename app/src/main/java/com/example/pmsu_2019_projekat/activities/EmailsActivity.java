package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.pmsu_2019_projekat.R;

import static com.example.pmsu_2019_projekat.R.*;

public class EmailsActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_emails);

        Toolbar toolbar = (Toolbar) findViewById(id.emails_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Emails Activity");
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(drawable.ic_menu_white_24dp);
        actionBar.setHomeButtonEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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



    public void onButtonClicked(View v){

        switch (v.getId()){
            case id.create_email:
                Intent a = new Intent(EmailsActivity.this, CreateEmailActivity.class);
                startActivity(a);
                break;
            case id.create_folder:
                Intent b = new Intent(EmailsActivity.this, CreateFolderActivity.class);
                startActivity(b);
                break;
            case id.email:
                Intent c = new Intent(EmailsActivity.this, EmailActivity.class);
                startActivity(c);
                break;
            case id.folders:
                Intent d = new Intent(EmailsActivity.this, FoldersActivity.class);
                startActivity(d);
                break;
            case id.folder:
                Intent e = new Intent(EmailsActivity.this, FolderActivity.class);
                startActivity(e);
                break;
            case id.settings:
                Intent f = new Intent(EmailsActivity.this, SettingsActivity.class);
                startActivity(f);
                break;
            case id.profile:
                Intent g = new Intent(EmailsActivity.this, ProfileActivity.class);
                startActivity(g);
                break;
            case id.contacts:
                Intent h = new Intent(EmailsActivity.this, ContactsActivity.class);
                startActivity(h);
                break;
            case id.create_contact:
                Intent i = new Intent(EmailsActivity.this, CreateContactActivity.class);
                startActivity(i);
                break;
            case id.contact:
                Intent j = new Intent(EmailsActivity.this, ContactActivity.class);
                startActivity(j);
                break;
            case id.createEmail:
                Toast.makeText(this, "Create email selected", Toast.LENGTH_LONG).show();
                Intent k = new Intent(EmailsActivity.this, CreateEmailActivity.class);
                startActivity(k);
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
