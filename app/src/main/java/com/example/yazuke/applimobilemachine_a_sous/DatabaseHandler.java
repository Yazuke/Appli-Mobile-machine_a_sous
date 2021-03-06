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
    public static final String USER_SOLDE = "solde";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_PSEUDO + " TEXT, " +
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
        value.put(USER_SOLDE, u.getSolde());
        //value.put(USER_SOLDE, 500);
        Log.d(TAG, "ajout " + u.getPseudo() + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, value);
        if (result == -1) {
            return false;
        } else return true;
    }

    public Cursor selectionner(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        String query="SELECT "+USER_KEY+" FROM "+TABLE_NAME+" WHERE "+USER_PSEUDO+" = '"+name+"'";
        Cursor data=db.rawQuery(query,null);
        return data;
    }

    public void updateName(String newName,int id,String oldName){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+TABLE_NAME+" SET "+USER_PSEUDO+" = '"+newName+"' WHERE "+ USER_KEY+" = '"+id+"' AND "+USER_PSEUDO+" = '"+oldName+"'";
        Log.d(TAG,"updateName: requête: "+query);
        Log.d(TAG,"updateName: Remplacement du pseudo par "+newName);
        db.execSQL(query);
    }

    public void updateSolde(int id,int solde){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+TABLE_NAME+" SET "+USER_SOLDE+" = '"+solde+"' WHERE "+ USER_KEY+" = '"+id+"'";
        Log.d(TAG,"updateName: requête: "+query);
        //Log.d(TAG,"updateName: Remplacement du pseudo par "+newName);
        db.execSQL(query);
    }

    public void supprimer(int id, String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+TABLE_NAME+" WHERE "+USER_KEY+" = '"+id+"' AND "+USER_PSEUDO+" = '"+name+"'";
        Log.d(TAG,"supprimer: suppression de "+name+ " de la base de données");
        db.execSQL(query);
    }

    public User getUser(int user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "
                + USER_KEY + " = " + user_id;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User u = new User();
        //u.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        u.setPesudo((c.getString(c.getColumnIndex(USER_PSEUDO))));
        u.setSolde(c.getInt(c.getColumnIndex(USER_SOLDE)));

        return u;
    }

    public User getUserBySolde(int user_solde) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE "
                + USER_SOLDE + " = " + user_solde;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User u = new User();
        //u.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        u.setPesudo((c.getString(c.getColumnIndex(USER_PSEUDO))));
        u.setSolde(c.getInt(c.getColumnIndex(USER_SOLDE)));

        return u;
    }

    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}




