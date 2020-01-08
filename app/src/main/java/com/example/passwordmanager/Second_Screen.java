package com.example.passwordmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Second_Screen extends AppCompatActivity {

   private database_helper_class db;

   private ListView list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen_layout);
        db=new database_helper_class(this);

        list=(ListView)findViewById(R.id.list_view);
        ArrayList<Person> personlist=new ArrayList<>();
        Cursor res=db.getalldata();
        if(res.getCount()==0)
            return;
        else{
            while(res.moveToNext())
            {
                Person user=new Person("","","");
                user.setID(res.getString(0));
                user.setName(res.getString(1));
                user.setPassword(res.getString(2));

                personlist.add(user);
            }
        }

        Person_list_adapter adapter=new Person_list_adapter(this,R.layout.list_view_adapter,personlist);
        list.setAdapter(adapter);
}

}
