package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    DatabaseHandler mDatabaseHandler;
    private TextView btnAdd,btnIns;
    private EditText psd, mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        psd = (EditText) findViewById(R.id.pseudo);
        mdp = (EditText) findViewById(R.id.mdp);
        mDatabaseHandler = new DatabaseHandler(this);
        btnAdd = (TextView) findViewById(R.id.textView);
        btnIns = (TextView) findViewById(R.id.textView2);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pseudo=psd.getText().toString();
                String password=mdp.getText().toString();
                if (psd.length()!=0 && mdp.length()!=0){
                    connect(pseudo,password);
                    psd.setText("");
                    mdp.setText("");
                }else {
                    toastMessage("Veuillez entrer votre pseudo et mot de passe pour vous connecter");
                }
            }
        });


        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Inscription.class);
                startActivity(intent);
            }
        });
    }

    public void connect(String pseudo, String mdp){
        Cursor c = mDatabaseHandler.selectionner(pseudo,mdp);
        if(c!=null){
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        } else{
            toastMessage("La connection a échoué ! Les identifiants ne sont pas corrects, veuillez réessayer");
        }
        c.close();
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}