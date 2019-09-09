package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.AccountAdapter;
import com.example.pmsu_2019_projekat.model.Account;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.List;

public class AccountsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.accounts_activity_toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Accounts");

        List<Account> accounts = Data.accounts;
        if (accounts.isEmpty() == false && accounts != null){
            generateDataList(accounts);
        }else{
            Toast.makeText(AccountsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
        }
    }

    private void generateDataList(List<Account> acList) {
        ListView accountsList = findViewById(R.id.accounts_list_view);
        AccountAdapter fAdapter = new AccountAdapter(this, acList);
        accountsList.setOnItemClickListener(new AccountsItemClickListener());
        accountsList.setAdapter(fAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.accounts_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.accounts_create) {
            Intent a = new Intent(AccountsActivity.this, AccountActivity.class);
            a.putExtra("Add", true);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    private class AccountsItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Account account = (Account) parent.getAdapter().getItem(position);
            Intent intent = new Intent(AccountsActivity.this, AccountActivity.class);
            intent.putExtra("accountToEdit", account);
            intent.putExtra("Add", false);
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
