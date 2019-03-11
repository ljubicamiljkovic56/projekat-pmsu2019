package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pmsu_2019_projekat.R;

import static com.example.pmsu_2019_projekat.R.*;

public class EmailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_emails);
    }

/*    public void btnStartCreateEmailActivity(View v){
        Intent i = new Intent(EmailsActivity.this, CreateEmailActivity.class);
        startActivity(i);
        finish();
    }*/

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
