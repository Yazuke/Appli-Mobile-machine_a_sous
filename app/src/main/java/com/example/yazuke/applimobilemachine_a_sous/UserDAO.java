package com.example.yazuke.applimobilemachine_a_sous;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Yazuke on 2018-04-15.
 */

public class UserDAO extends DAOBase {


    public static final String TABLE_NAME = "user";
    public static final String KEY = "id";
    public static final String PSEUDO = "pseudo";
    public static final String MDP = "mdp";
    public static final String SOLDE = "solde";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PSEUDO + " TEXT, " + MDP + " TEXT, " +SOLDE+" INT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public UserDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @param u  à ajouter à la base
     */
    public void ajouter(User u) {
        ContentValues value = new ContentValues();
        value.put(UserDAO.PSEUDO, u.getPseudo());
        value.put(UserDAO.MDP, u.getMdp());
        value.put(UserDAO.SOLDE, u.getSolde());
        mDb.insert(UserDAO.TABLE_NAME, null, value);
    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {

        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});

    }

    /**
     * @param u le métier modifié
     */
    public void modifier(User u) {
        ContentValues value = new ContentValues();
        value.put(SOLDE, u.getSolde());
        mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(u.getId())});
    }

    public Cursor selectionner(String sql, String[] selectionArgs){
        Cursor c = mDb.rawQuery(sql,selectionArgs);
        return c;
    }
}

