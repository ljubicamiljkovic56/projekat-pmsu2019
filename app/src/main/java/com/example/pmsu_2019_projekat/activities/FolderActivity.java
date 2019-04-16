package com.example.pmsu_2019_projekat.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FolderActivity extends AppCompatActivity {

    public static Folder folder1;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        toolbar = (Toolbar) findViewById(R.id.folder_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PMSU_2019_Projekat");
        toolbar.setSubtitle("Folder Activity");
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        folderPodaci();
        TextView name = findViewById(R.id.folder_name_txt);
        name.append(folder1.getName());
        TextView messageDetails = findViewById(R.id.folder_message);
        messageDetails.append(folder1.getMessages().toString());




    }
    public static void folderPodaci(){
        folder1 = new Folder();
        folder1.setId("1");
        folder1.setName("Folder 1");
        folder1.setParentFolder(new Folder());
        ArrayList<Folder> folders = new ArrayList<>();
        folder1.setSubfolders(folders);
        ArrayList<Message> messages = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        Message m1 = new Message();
        Message m2 = new Message();
        m1.setId("22");
        Contact contact = new Contact();
        contact.setFirst("Marko");
        contact.setLast("Marković");
        contact.setEmail("markom@yahoo.com");
        Contact contact1 = new Contact();
        contact.setFirst("Aca");
        contact.setLast("Acic");
        contact.setEmail("aca@gmail.com");
        contacts.add(contact);
        contacts.add(contact1);
        m1.setFrom(contact);
        m1.setTo(contacts);
        m1.setCc(contacts);
        m1.setBcc(contacts);
        m1.setDateTime(new Date(2019,04,16,16,41));
        m1.setSubject("nn");
        m1.setContent("Poruka1");
        m2.setId("23");
        m2.setFrom(contact1);
        m2.setTo(contacts);
        m2.setCc(contacts);
        m2.setBcc(contacts);
        m2.setDateTime(new Date(2019,04,16,16,41));
        m2.setSubject("Dobrodosli");
        m2.setContent("Poruka2");
        messages.add(m1);
        messages.add(m2);
        folder1.setMessages(messages);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.folder_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case R.id.toolbar_edit:
                message = "Edit";
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
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
