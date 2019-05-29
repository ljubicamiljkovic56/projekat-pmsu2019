package com.example.pmsu_2019_projekat.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.pmsu_2019_projekat.fragments.EmailFragment;
import com.example.pmsu_2019_projekat.fragments.FolderFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {


    private Context context;
    int totalTab;

    public TabLayoutAdapter(Context context, FragmentManager fm, int totalTab){
        super(fm);
        context = context;
        this.totalTab = totalTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FolderFragment folderFragment = new FolderFragment();
                return  folderFragment;

            case 1:
                EmailFragment emailFragment = new EmailFragment();
                return emailFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
