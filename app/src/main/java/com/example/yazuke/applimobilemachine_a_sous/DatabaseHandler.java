package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nic-1 on 3/26/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String USER_KEY = "id";
    public static final String USER_PSEUDO = "pseudo";
    public static final String USER_MDP = "mdp";
    public static final String USER_SOLDE = "solde";

    public static final String USER_TABLE_NAME = "User";
    public static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";";
    public static final String USER_TABLE_CREATE =
            "CREATE TABLE " + USER_TABLE_NAME + " (" +
                    USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_PSEUDO + " TEXT, " +
                    USER_MDP + " TEXT);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(USER_TABLE_DROP);
        onCreate(db);
    }
}




