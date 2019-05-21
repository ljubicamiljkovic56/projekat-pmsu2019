package com.example.pmsu_2019_projekat.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.FolderAdapter;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.services.FolderService;
import com.example.pmsu_2019_projekat.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoldersActivity extends NavigationActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);


        progressDialog = new ProgressDialog(FoldersActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.folders_toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Folders");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FolderService service = RetrofitClient.getRetrofitInstance().create(FolderService.class);
        Call<List<Folder>> call = service.getAllFolders();
        call.enqueue(new Callback<List<Folder>>() {
            @Override
            public void onResponse(Call<List<Folder>> call, Response<List<Folder>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Folder>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FoldersActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.folders_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoldersActivity.this, CreateFolderActivity.class);
                startActivity(intent);
            }
        });
    }


    private void generateDataList(List<Folder> fsList) {
        ListView foldersList = findViewById(R.id.folders_list_view);
        FolderAdapter fAdapter = new FolderAdapter(this, fsList);
        foldersList.setOnItemClickListener(new FoldersItemClickListener());
        foldersList.setAdapter(fAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.folders_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.folders_create) {
            Toast.makeText(this, "Create folder  selected", Toast.LENGTH_LONG).show();
            Intent a = new Intent(FoldersActivity.this, CreateFolderActivity.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    private class FoldersItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Folder folder = (Folder) parent.getAdapter().getItem(position);
            Intent intent = new Intent(FoldersActivity.this, FolderActivity.class);
            intent.putExtra("Folder", folder);
            startActivity(intent);
        }
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
