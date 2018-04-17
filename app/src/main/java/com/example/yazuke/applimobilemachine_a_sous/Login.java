package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    UserDAO u=new UserDAO(this);
    String sql= new String("select ID from User where pseudo =? AND mdp=?");
    String pseudo=new String();
    String password=new String();

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//        EditText psd = (EditText) findViewById (R.id.pseudo);
//        pseudo = psd.getText().toString();
//
//        EditText mdp = (EditText) findViewById (R.id.mdp);
//        password = mdp.getText().toString();
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void connect(View v){
        u.open();
        Cursor c=u.selectionner(sql,new String[]{pseudo,password});
        if(c!=null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        u.close();
    }
    public void inscription(View v){
        u.open();
            Intent intent = new Intent(Login.this, Inscription.class);
            startActivity(intent);

    }

    //lance le jeu sans connection (tests)
    public void jeu(View v){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }


}