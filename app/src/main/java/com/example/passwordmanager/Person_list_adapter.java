package com.example.passwordmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Person_list_adapter extends ArrayAdapter<Person> {

    private static final String TAG="PersonListAdapter";
    private Context mcontext;
    private  int mresource;
    public Person_list_adapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id=getItem(position).getID();
        String name=getItem(position).getName();
        String password=getItem(position).getPassword();

        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mresource,parent,false);

        TextView tvid=(TextView)convertView.findViewById(R.id.textView);
        TextView tvname=(TextView)convertView.findViewById(R.id.textView2);
        TextView tvpassword=(TextView)convertView.findViewById(R.id.textView3);

        tvid.setText(id);
        tvname.setText(name);
        tvpassword.setText(password);
        return convertView;


    }
}
