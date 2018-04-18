package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    DatabaseHandler mDatabaseHandler;
    private EditText psd, mdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        psd = (EditText) findViewById(R.id.pseudo);
        //       pseudo = psd.getText().toString();

        mdp = (EditText) findViewById(R.id.mdp);
        //      password = mdp.getText().toString();


    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void inscription(View view){
        Intent intent=new Intent(Login.this,Inscription.class);
        startActivity(intent);
    }
}