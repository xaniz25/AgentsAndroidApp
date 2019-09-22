/*Created by Shanice Talan on Sept 19, 2019
CMPP 264 Java: Day 12 Assignment - Android App
Connects to Travel Experts DB to do Select, Insert, Update & Delete
on Agents table */

package com.example.agentsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//connects to TravelExperts SQLite DB
public class DBHelper extends SQLiteOpenHelper{
    private static int version = 1;
    private static String dbName = "TravelExpertsSqlLite.db";
    private static String dbPath = "/data/data/com.example.agentsapp/databases/";
    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
