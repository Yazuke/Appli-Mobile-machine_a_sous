package com.example.yazuke.applimobilemachine_a_sous;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private int selectedID;
    private  Button button, button4, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        button = (Button) findViewById(R.id.button); // Boutton pour démarrer la partie
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DemarrerPartie();
            }

        });

        button = (Button) findViewById(R.id.button2);// Bouton pour changer d'utilisateur
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUsers();
            }
        });

        button4 = (Button) findViewById(R.id.button4); // Boutton pour quitter l'application
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                quitterApp();
            }

        });

        Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
    }

    public void DemarrerPartie() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id",selectedID);
        startActivity(intent);
    }

    public void changeUsers(){
        Log.i("MaS", "CHANGER UTILISATEURS");
        Intent intent2 = new Intent(this, Login.class);
        startActivity(intent2);
    }


    //On appel l'intention du HOME pour quitter l'application
    public void quitterApp() {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    /*int pid = android.os.Process.myPid();=====> use this if you want to kill your activity. But its not a good one to do.
    android.os.Process.killProcess(pid);*/

    }




}


