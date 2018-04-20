package com.example.yazuke.applimobilemachine_a_sous;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Yazuke on 2018-04-16.
 */

public class Inscription extends AppCompatActivity{
    DatabaseHandler mDatabaseHandler;
    private TextView btnAdd;
    private EditText psd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        psd = (EditText) findViewById(R.id.pseudo);
        mDatabaseHandler = new DatabaseHandler(this);

        btnAdd = (TextView) findViewById(R.id.validate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pseudo = psd.getText().toString();
                if (psd.length() != 0) {
                    inscription(pseudo);
                    psd.setText("");
                } else {
                    toastMessage("Veuillez entrer votre pseudo et mot de passe pour vous connecter");
                }
            }
        });
    }


    public void inscription(String pseudo){
        User u=new User(pseudo);
        boolean insertData = mDatabaseHandler.ajouter(u);
        if(insertData){
            Intent intent=new Intent(Inscription.this,MainActivity.class);
            startActivity(intent);
            toastMessage("Votre inscription a bien été effectué");
        } else {
            toastMessage("Erreure");
        }
    }
    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
