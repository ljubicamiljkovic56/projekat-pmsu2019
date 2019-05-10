package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Contact;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.tools.Data;

import java.util.List;

public class ContactAdapter extends BaseAdapter {
    private Activity activity;
    private List<Message> dataList;

    public ContactAdapter(Activity activity, List<Message> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }

    public ContactAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Data.getContacts().size();
    }

    @Override
    public Object getItem(int position) {
        return Data.getContacts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Contact contact = Data.getContacts().get(position);

        if(convertView == null)
            view = activity.getLayoutInflater().inflate(R.layout.contacts_list_item, null);

        ImageView icon = view.findViewById(R.id.icon);
        TextView name = view.findViewById(R.id.CName);
        TextView mail = view.findViewById(R.id.CMail);

        name.setText(contact.getFirst() + " " + contact.getLast());
        mail.setText(contact.getEmail());

        return view;
    }
}
