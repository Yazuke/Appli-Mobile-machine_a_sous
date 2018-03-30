package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nic-1 on 3/26/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SCORE";
    public static final String COL_4 = "MULTIPLIER";
    public static final String COL_5 = "DATE";
    private static final String DATABASE_CREATE = "create table" + DATABASE_NAME + "ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SCORE INTEGER, MULTIPLIER INTEGER, DATE INTEGER";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}



