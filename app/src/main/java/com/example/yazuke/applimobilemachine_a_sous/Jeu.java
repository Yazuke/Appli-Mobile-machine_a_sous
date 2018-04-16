package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;


public class Jeu {

    public int nbIterations=0;
    public boolean fini=false;
    public boolean estLance=false;

    private boolean[] rouleauxTournent={false,false,false};


    public Jeu(){}


    ////////////////////////////
    // -- Démarrage du jeu -- //
    ////////////////////////////


    public void demarrer(){
        nbIterations++;

        rouleauxTournent[0]=true;
        rouleauxTournent[1]=true;
        rouleauxTournent[2]=true;

        Log.i("MaS","Lancement du jeu");
        estLance=true;
        fini=false;
    }

    public void arreter(int id){
        id-=1;

        rouleauxTournent[id]=false;

        if((!rouleauxTournent[0])&&(!rouleauxTournent[1])&&(!rouleauxTournent[2])){
            fini=true;
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
