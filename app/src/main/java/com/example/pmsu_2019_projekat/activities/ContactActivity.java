package com.example.pmsu_2019_projekat.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.services.ContactService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;
import com.example.pmsu_2019_projekat.tools.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {

    public static Contact dummy1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_activity_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar ();
        actionbar.setDisplayHomeAsUpEnabled(true) ;
        actionbar.setTitle("Contact info");

        dummy1 = (Contact) getIntent().getSerializableExtra("Contact");
        TextView name = findViewById(R.id.contact_name);
        name.append(dummy1.getFirst());
        TextView lastname = findViewById(R.id.contact_surname);
        lastname.append(dummy1.getLast());
        TextView email = findViewById(R.id.contact_email);
        email.append(dummy1.getEmail());
        TextView displayName = findViewById(R.id.contact_displayName);
        displayName.append(dummy1.getDisplayName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String message = "";
        switch (item.getItemId()){
            case R.id.contact_toolbar_edit:
                Intent i = new Intent(ContactActivity.this, CreateContactActivity.class);
                i.putExtra("Add", false);
                i.putExtra("contactToEdit", dummy1);
                startActivity(i);
                message = "Edit";
                break;
            case R.id.contact_toolbar_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", deleteDialogClickListener)
                        .setNegativeButton("No", deleteDialogClickListener).show();
                message = "Delete";
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
                    ContactService service = RetrofitClient.getRetrofitInstance().create(ContactService.class);
                    Call<Void> deleteContact = service.deleteContact(dummy1.getId());
                    deleteContact.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(ContactActivity.this, "Contact deleted", Toast.LENGTH_LONG);
                            Data.getContactsByUsername();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(ContactActivity.this, "Something went wrong", Toast.LENGTH_LONG);
                            finish();
                        }
                    });
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    Toast.makeText(ContactActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
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
