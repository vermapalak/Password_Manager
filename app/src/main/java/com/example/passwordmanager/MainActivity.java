package com.example.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mpreference;
    private SharedPreferences.Editor meditor;
    private EditText mname;
    private EditText mpassword;
    private Button btnlogin;
    private Button btnviewall;
    private CheckBox mcheckbox;
    private database_helper_class db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new database_helper_class(this);
        mname=(EditText)findViewById(R.id.name);
        mpassword=(EditText)findViewById(R.id.password);
        btnlogin=(Button)findViewById(R.id.login);
        mcheckbox=(CheckBox)findViewById(R.id.checkbox);
        btnviewall=(Button)findViewById(R.id.viewall);


        mpreference= PreferenceManager.getDefaultSharedPreferences(this);
        meditor=mpreference.edit();
        checksharedpreferences();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mcheckbox.isChecked())
                {
                    String name=mname.getText().toString();
                    String password=mpassword.getText().toString();
                    boolean isInserted= db.insert(name,password);
                    meditor.putString(getString(R.string.checkbox),"True");
                    meditor.commit();
                    if(isInserted==true)
                    {
                        Toast.makeText(MainActivity.this,"Password is stored ",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                    }


                    meditor.putString(getString(R.string.name),name);
                    meditor.commit();


                    meditor.putString(getString(R.string.password),password);
                    meditor.commit();
                }
                else {
                    meditor.putString(getString(R.string.checkbox),"False");
                    meditor.commit();


                    //String name=mname.getText().toString();
                    meditor.putString(getString(R.string.name),"");
                    meditor.commit();

                    // String password=mpassword.getText().toString();
                    meditor.putString(getString(R.string.password),"");
                    meditor.commit();
                    Toast.makeText(MainActivity.this,"Password is not stored ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnviewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                                Intent intent = new Intent(MainActivity.this, Second_Screen.class);


                                startActivity(intent);
                        }


                }
        );
    }
    public void showmessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
        }

    private void checksharedpreferences()
    {
        String checkbox=mpreference.getString(getString(R.string.checkbox),"false");
        String name=mpreference.getString(getString(R.string.name),"");
        String password=mpreference.getString(getString(R.string.password),"");
        mname.setText(name);
        mpassword.setText(password);

        if(checkbox.equals("True"))
            mcheckbox.setChecked(true);
        else
            mcheckbox.setChecked(false);


    };

}
