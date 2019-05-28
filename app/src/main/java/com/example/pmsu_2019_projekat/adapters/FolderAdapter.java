package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.List;

public class FolderAdapter extends BaseAdapter {

    private Activity activity;

    private List<Folder> dataList;

    public FolderAdapter(Activity activity, List<Folder> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }


    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Folder folder = dataList.get(position);

        if(convertView == null)
            view = activity.getLayoutInflater().inflate(R.layout.folders_list_item, null);

        TextView title = view.findViewById(R.id.folderTitle);
        TextView message = view.findViewById(R.id.folderMessage);

        title.setText(folder.getName());
        if(folder.getMessages() != null)
            message.setText("messages: " + folder.getMessages().size());
        else
            message.setText("messages: 0" );

        return view;
    }
}
