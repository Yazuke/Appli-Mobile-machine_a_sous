package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;


public class Jeu {

        public int nbIterations=0;
        public boolean victoire=false;
        private boolean estLance=false;

    public Jeu(){
        //TODO, trucs à initialiser
    }


    ////////////////////////////
    // -- Démarrage du jeu -- //
    ////////////////////////////

    //IMPORTANT/////////

    //Les threads sont remplacés par des AsyncTasks dans le MainActivity
    //La classe jeu va juste servir à gérer les mises etc mais pas les rouleaux eux mêmes

    //////////////////




    //Lance les threads
    public void demarrer(){
        nbIterations++;

        Log.i("MaS","Lancement du jeu");
        estLance=true;
        victoire=false;
    }


    //////////////////////
    // -- Accesseurs -- //
    //////////////////////

    public boolean estLance(){
        return estLance;
    }


}
