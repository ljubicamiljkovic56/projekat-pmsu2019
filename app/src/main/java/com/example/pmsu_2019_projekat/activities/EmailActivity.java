package com.example.pmsu_2019_projekat.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Message;

import java.util.ArrayList;
import java.util.Date;

import static com.example.pmsu_2019_projekat.R.*;

public class EmailActivity extends AppCompatActivity {

    public static Message dummy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.email_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("PMSU_2019_Projekat");
        toolbar.setSubtitle("Email Activity");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;

        emailPodaci();
        TextView from = findViewById(id.email_from);
        from.append(dummy2.getFrom().getEmail());
        TextView to = findViewById(id.email_to);
        to.append(dummy2.getTo().get(0).getEmail());
        TextView cc = findViewById(id.email_cc);
        cc.append(dummy2.getTo().get(0).getEmail());
        TextView bcc = findViewById(id.email_bcc);
        bcc.append(dummy2.getTo().get(0).getEmail());
        TextView dateTime = findViewById(id.email_date);
        dateTime.append(dummy2.getDateTime().toString());
        TextView subject = findViewById(id.email_subject);
        subject.append(dummy2.getSubject());
        TextView content = findViewById(id.email_content);
        content.append(dummy2.getContent());
    }

    public static void emailPodaci(){
        dummy2 = new Message();
        dummy2.setId("123");
        Contact sender = ContactActivity.dummy1;
        if(sender == null){
            ContactActivity.kontaktPodaci();
            sender = ContactActivity.dummy1;
        }
        dummy2.setFrom(sender);
        ArrayList<Contact> contactList = new ArrayList<>();
        Contact a = new Contact();
        a.setFirst("Marko");
        a.setLast("Marković");
        a.setEmail("markom@yahoo.com");
        contactList.add(a);
        dummy2.setTo(contactList);
        dummy2.setCc(contactList);
        dummy2.setBcc(contactList);
        dummy2.setDateTime(new Date(2019,04,16,16,41));
        dummy2.setSubject("Veliki pozdrav");
        dummy2.setContent("Ovo je primer neke email poruke");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.email_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case id.email_delete:
                message = "Delete";
                break;
            case id.email_replay:
                message = "Replay";
                break;
            case id.email_replay_to_all:
                message = "Replay to all";
                break;
            case id.email_foward:
                message = "Forward";
                break;
        }
        Toast.makeText(this, message + "  selected", Toast.LENGTH_LONG).show();
        return  super.onOptionsItemSelected(item);
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
