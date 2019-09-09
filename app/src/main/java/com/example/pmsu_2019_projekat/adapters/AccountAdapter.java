package com.example.pmsu_2019_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pmsu_2019_projekat.R;
import com.example.pmsu_2019_projekat.model.Account;

import java.util.List;

public class AccountAdapter extends BaseAdapter {

    private Activity activity;
    private List<Account> dataList;

    public AccountAdapter(Activity activity, List<Account> dataList) {
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
        View v = convertView;
        Account account = dataList.get(position);

        if(convertView == null)
            v = activity.getLayoutInflater().inflate(R.layout.accounts_list_item, null);

        TextView account_username = v.findViewById(R.id.account_username);
        TextView account_display_name = v.findViewById(R.id.account_display_name);
        account_username.setText(account.getUsername());
        account_display_name.setText(account.getDisplayName());

        return v;
    }
}
