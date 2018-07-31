package com.example.youssef.o6uresgisteration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YouSsef ESsam on 12/16/2017.
 */

public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context) {
        super(context, "Registration_DB", null, 4 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registration ( id INTEGER primary key, username TEXT, GPA DECIMAL, level INTEGER, major TEXT ,Credit_Hour INTEGER )");
        db.execSQL("create table admin ( username TEXT primary key, password Text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table registration");
        db.execSQL("drop table admin");
        onCreate(db);

    }
}
