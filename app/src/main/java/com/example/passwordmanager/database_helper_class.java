package com.example.passwordmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class database_helper_class extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "COUNTRIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";

    // Database Information
    static final String DB_NAME = "Password_Record.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " INTEGER);";

    public database_helper_class(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(String name, String desc) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(SUBJECT, name);
        contentValue.put(DESC, desc);
       long result= db.insert(TABLE_NAME, null, contentValue);
        if(result==-1)
            return false;
        else
            return true;
    }
    @NonNull
    public Cursor getalldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        if(res.getCount()!=0)
            return res;
        return res;
    }
}

