package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pmsu_2019_projekat.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onProfileClicked(View v){

        Intent a = new Intent(NavigationActivity.this, ProfileActivity.class);
        startActivity(a);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_emails) {
            Intent a = new Intent(NavigationActivity.this, EmailsActivity.class);
            startActivity(a);
        } else if (id == R.id.nav_contacts) {
            Intent c = new Intent(NavigationActivity.this, ContactsActivity.class);
            startActivity(c);
        } else if (id == R.id.nav_folders) {
            Intent d = new Intent(NavigationActivity.this, FoldersActivity.class);
            startActivity(d);
        } else if (id == R.id.nav_settings) {
            Intent f = new Intent(NavigationActivity.this, SettingsActivity.class);
            startActivity(f);
        }else if (id == R.id.nav_logout) {
            Intent h = new Intent(NavigationActivity.this, LoginActivity.class);
            startActivity(h);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
