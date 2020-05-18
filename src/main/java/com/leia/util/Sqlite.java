package com.leia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    Author:leia
    Write The Code Change The World    
*/public class Sqlite extends SQLiteOpenHelper {
    /*
       1.数据库 名称  字段 版本
     */

    public Sqlite(@Nullable Context context) {
        super(context, "result.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create " +

                "table video" +

                "(id integer primary key autoincrement," +

                "imageurl varchar(80)," +

                "videoname varchar(80)," +

                "videourl varchar(80)," +

                "info varchar(240))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
