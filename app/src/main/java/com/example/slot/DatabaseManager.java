package com.example.slot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, "PlayerDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table PlayerTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "name text, score text, rate text)";
        db.execSQL(sql);
    }

    public void insert(String nameName, String scoreName, String rateName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into PlayerTable values (";
        sql += "null, '" + nameName + "','" + scoreName + "','" + rateName + "')";
        db.execSQL(sql);
        db.close();
    }



    public void updateByPlayer(String name, String score) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update PlayerTable set score = '" + score + "'";
        sql += "where name = '" + name + "'";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> getPlayer() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PlayerTable";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            list.add(name);
        }
        return list;
    }

    public String[] get(String nameTitle) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PlayerTable where name = '" + nameTitle + "'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[3];
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String score = cursor.getString(2);
            String rate = cursor.getString(3);
            entry[0] = name;
            entry[1] = score;
            entry[2] = rate;
        }
        db.close();
        return entry;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
