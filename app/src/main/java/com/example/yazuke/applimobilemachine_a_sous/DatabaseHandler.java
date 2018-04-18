package com.example.yazuke.applimobilemachine_a_sous;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by nic-1 on 3/26/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "user";
    public static final String USER_KEY = "ID";
    public static final String USER_PSEUDO = "pseudo";
    public static final String USER_MDP = "mdp";
    public static final String USER_SOLDE = "solde";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_PSEUDO + " TEXT, " +
                    USER_MDP + " TEXT, " +
                    USER_SOLDE + " INT);";

    public DatabaseHandler(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(TABLE_CREATE);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(TABLE_DROP);
        onCreate(db);
    }

    public boolean ajouter(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(USER_PSEUDO, u.getPseudo());
        value.put(USER_MDP, u.getMdp());
        value.put(USER_SOLDE, u.getSolde());
        Log.d(TAG, "ajout " + u.getPseudo() + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, value);
        if (result == -1) {
            return false;
        } else return true;
    }

    public Cursor selectionner(String pseudo,String mdp){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT ID FROM "+TABLE_NAME+" WHERE pseudo = ? AND mdp= ?";
        Cursor data=db.rawQuery(query,new String[] {pseudo, mdp});
        return data;
    }
}




