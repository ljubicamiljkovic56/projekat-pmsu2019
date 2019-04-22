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
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FolderActivity extends AppCompatActivity {

    public static Folder folder1;
    public static Message message1;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);


        //folder1 = Data.getFolders().get(0);
        folder1 = (Folder) getIntent().getSerializableExtra("Folder");
        toolbar = (Toolbar) findViewById(R.id.folder_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(folder1.getName());
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


        TextView messageSender = findViewById(R.id.folder_name_txt);
        messageSender.append(folder1.getMessages().get(0).getFrom().getFirst() +  " " + folder1.getMessages().get(0).getFrom().getLast());
        TextView messageSubject = findViewById(R.id.folder_message_subject);
        messageSubject.append(folder1.getMessages().get(0).getSubject());
       TextView messageDetails = findViewById(R.id.folder_message);
        messageDetails.append(folder1.getMessages().get(0).getContent());

        TextView messageSender2 = findViewById(R.id.folder_name_txt2);
        messageSender2.append(folder1.getMessages().get(1).getFrom().getFirst() +  " " + folder1.getMessages().get(1).getFrom().getLast());
        TextView messageSubject2 = findViewById(R.id.folder_message_subject2);
        messageSubject2.append(folder1.getMessages().get(1).getSubject());
        TextView messageDetails2 = findViewById(R.id.folder_message2);
           messageDetails2.append(folder1.getMessages().get(1).getContent());

        message1 = (Message) getIntent().getSerializableExtra("Message");




        

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
