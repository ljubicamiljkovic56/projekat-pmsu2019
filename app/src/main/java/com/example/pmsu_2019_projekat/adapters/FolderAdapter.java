package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.tools.Data;

public class FolderAdapter extends BaseAdapter {

    private Activity activity;

    public FolderAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Data.getFolders().size();
    }

    @Override
    public Object getItem(int position) {
        return Data.getFolders().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Folder folder = Data.getFolders().get(position);

        if(convertView == null){
            view = activity.getLayoutInflater().inflate(R.layout.folders_list_item, null);

            TextView title = view.findViewById(R.id.folderTitle);
            TextView desc = view.findViewById(R.id.folderDesc);

        }
        return view;
    }
}
