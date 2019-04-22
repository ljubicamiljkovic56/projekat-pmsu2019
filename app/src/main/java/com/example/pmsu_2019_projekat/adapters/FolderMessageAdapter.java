package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.activities.FolderActivity;
import com.example.pmsu_2019_projekat.model.Folder;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.tools.Data;

public class FolderMessageAdapter extends BaseAdapter {

    private Activity activity;

    public FolderMessageAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Data.getEmails().size();
    }

    @Override
    public Object getItem(int position) {
        return Data.getEmails().get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Message message = Data.getEmails().get(position);

        if(convertView == null)
            view = activity.getLayoutInflater().inflate(R.layout.folder_list_item, null);

        TextView title = view.findViewById(R.id.messageTitle);
        TextView messageDesc = view.findViewById(R.id.messageDesc);

        title.setText(message.getFrom().getFirst());
        messageDesc.setText(message.getSubject());


        return view;
    }


}
