package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends BaseAdapter {

    private Activity activity;

    private List dataList;

    public FolderAdapter(Activity activity, List dataList) {
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
        if(dataList.get(position) instanceof Folder){
            Folder folder = (Folder) dataList.get(position);

            view = activity.getLayoutInflater().inflate(R.layout.folders_list_item, null);

            TextView title = view.findViewById(R.id.folderTitle);
            TextView message = view.findViewById(R.id.folderMessage);
            TextView folders = view.findViewById(R.id.foldersFolders);

            title.setText(folder.getName());
            if(folder.getMessages() != null)
                message.setText("messages: " + folder.getMessages().size());
            else
                message.setText("messages: 0" );

            if(folder.getSubFolders() != null)
                folders.setText("folders: " + folder.getSubFolders().size());
            else
                folders.setText("folders: 0" );

            return view;
        }else{
            Message email = (Message) dataList.get(position);

            view = activity.getLayoutInflater().inflate(R.layout.emails_list_item, null);

            TextView from = view.findViewById(R.id.EFrom);
            TextView title = view.findViewById(R.id.ETitle);
            TextView content = view.findViewById(R.id.EContent);

            if(email != null){
                if(email.getFrom() == null){
                    from.setText(Data.loggedInUser);
                    title.setText(email.getSubject());
                    content.setText(email.getContent());
                }else {
                    from.setText(email.getFrom());
                    title.setText(email.getSubject());
                    content.setText(email.getContent());
                }
            }

            return view;
        }
    }
}
