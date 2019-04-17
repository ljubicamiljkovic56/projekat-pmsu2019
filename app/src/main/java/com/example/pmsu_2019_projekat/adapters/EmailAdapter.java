package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Message;
import com.example.pmsu_2019_projekat.tools.Data;

public class EmailAdapter extends BaseAdapter {

    private Activity activity;

    public EmailAdapter(Activity activity) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Message email = Data.getEmails().get(position);

        if(convertView == null)
            v = activity.getLayoutInflater().inflate(R.layout.emails_list_item, null);

        TextView from = v.findViewById(R.id.EFrom);
        TextView title = v.findViewById(R.id.ETitle);
        TextView content = v.findViewById(R.id.EContent);

        from.setText(email.getFrom().getFirst() + " " + email.getFrom().getLast());
        title.setText(email.getSubject());
        content.setText(email.getContent());

        return v;
    }
}
