package com.example.pmsu_2019_projekat.activities;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.services.EmailService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pmsu_2019_projekat.R.*;

public class EmailActivity extends AppCompatActivity {

    public static Message dummy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.email_toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;
        actionbar.setTitle("Email");

        dummy2 = (Message) getIntent().getSerializableExtra("Email");
        TextView from = findViewById(id.email_from);
        if(dummy2.getFrom() == null){
            from.append(Data.loggedInUser);
        }else{
            from.append(dummy2.getFrom());
        }
        TextView to = findViewById(id.email_to);
        if(dummy2.getTo() == null){
            to.append(Data.loggedInUser);
        }else{
            to.append(dummy2.getTo().get(0));
        }
        if(dummy2.getCc() != null && !dummy2.getCc().contains(null) && !dummy2.getCc().isEmpty()){
            TextView cc = findViewById(id.email_cc);
            cc.append(dummy2.getCc().get(0));
        }
        if(dummy2.getBcc() != null && !dummy2.getBcc().contains(null) && !dummy2.getBcc().isEmpty()){
            TextView bcc = findViewById(id.email_bcc);
            bcc.append(dummy2.getBcc().get(0));
        }
        TextView dateTime = findViewById(id.email_date);
        dateTime.append(dummy2.getDateTime().toString());

        if(dummy2.getAttachments() != null && !dummy2.getAttachments().contains(null) && !dummy2.getAttachments().isEmpty()){
            TextView attachment = findViewById(id.email_attachment);
            attachment.append(Html.fromHtml("<font color=\"#0645AD\"><bold>"
                    + dummy2.getAttachments().get(0).getName()
                    + "</bold></font>"));
        }
        TextView subject = findViewById(id.email_subject);
        subject.append(dummy2.getSubject());
        TextView content = findViewById(id.email_content);
        content.append(dummy2.getContent());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", deleteDialogClickListener)
                        .setNegativeButton("No", deleteDialogClickListener).show();
                //message = "Delete";
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

    DialogInterface.OnClickListener deleteDialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    EmailService service = RetrofitClient.getRetrofitInstance().create(EmailService.class);
                    Call<Void> deleteEmail = service.deleteEmail(dummy2.getId());
                    deleteEmail.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(EmailActivity.this, "Email deleted", Toast.LENGTH_LONG);
                            for(Folder f : Data.folders)
                                for(Message m : f.getMessages())
                                    if(m.getId().equals(dummy2.getId()))
                                        f.getMessages().remove(m);
                            Data.getFoldersByAccountID();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(EmailActivity.this, "Something went wrong", Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(EmailActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

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
