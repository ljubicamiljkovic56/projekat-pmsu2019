package com.example.pmsu_2019_projekat.activities;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.adapters.EmailAdapter;
import com.example.pmsu_2019_projekat.adapters.TabLayoutAdapter;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.List;

public class FolderActivity extends AppCompatActivity {

    public static Folder folder1;
    public static Message message1;

    Toolbar toolbar;

//    TabLayout tabLayout;
//    ViewPager viewPager;
//    TabLayoutAdapter tabLayoutAdapter;
//    TabItem folderItem;
//    TabItem emailItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

//
//        tabLayout = (TabLayout) findViewById(R.id.tabLayoutFolder);
//        folderItem = (TabItem) findViewById(R.id.tabItemFolder);
//        emailItem = (TabItem) findViewById(R.id.tabItemEmail);
//        viewPager = (ViewPager) findViewById(R.id.viewPagerFolder);

//
//        tabLayout.addTab(tabLayout.newTab().setText("Folder"));
//        tabLayout.addTab(tabLayout.newTab().setText("Emails"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

/*        final TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabLayoutAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    toolbar.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimaryVivid));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimaryVivid));
                }else if(tab.getPosition() == 1){
                    toolbar.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimaryDark));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimaryDark));
                }else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(FolderActivity.this, R.color.colorPrimary));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));*/

        folder1 = (Folder) getIntent().getSerializableExtra("Folder");
        toolbar = (Toolbar) findViewById(R.id.folder_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(folder1.getName());
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        List<Message> messagesList = folder1.getMessages();

        ListView folderList = findViewById(R.id.folder_list_view);
        EmailAdapter folderAdapter = new EmailAdapter(this, messagesList);
        folderList.setOnItemClickListener(new FolderItemClickListener());
        folderList.setAdapter(folderAdapter);


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
                Toast.makeText(this, message + "  selected", Toast.LENGTH_LONG).show();
                Intent i = new Intent(FolderActivity.this, CreateFolderActivity.class);
                i.putExtra("Folder", folder1);
                startActivity(i);
                break;

        }
        return  super.onOptionsItemSelected(item);
    }

    private class FolderItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Message email = (Message) parent.getAdapter().getItem(position);
            Intent intent = new Intent(FolderActivity.this, EmailActivity.class);
            intent.putExtra("Email", email);
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
