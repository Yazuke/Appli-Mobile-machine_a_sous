package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;


public class Jeu {

    public int nbIterations=0;
    public boolean victoire=false;
    public boolean estLance=false;

    private boolean[] rouleauxTournent={false,false,false};


    public Jeu(){}


    ////////////////////////////
    // -- Démarrage du jeu -- //
    ////////////////////////////

    //IMPORTANT/////////

    //Les threads sont remplacés par des AsyncTasks dans le MainActivity
    //La classe jeu va juste servir à gérer les mises, les tests de victoire etc mais pas les rouleaux eux mêmes

    //////////////////


    public void demarrer(){
        nbIterations++;

        rouleauxTournent[0]=true;
        rouleauxTournent[1]=true;
        rouleauxTournent[2]=true;

        Log.i("MaS","Lancement du jeu");
        estLance=true;
        victoire=false;
    }

    public void arreter(int id){
        id-=1;

        rouleauxTournent[id]=false;

        if((!rouleauxTournent[0])&&(!rouleauxTournent[1])&&(!rouleauxTournent[2])){
            victoire=true;
            Log.i("MaS","Tous les rouleaux sont arretés");
        }
    }

    //////////////////////
    // -- Accesseurs -- //
    //////////////////////

    public boolean estLance(){
        return estLance;
    }


}
