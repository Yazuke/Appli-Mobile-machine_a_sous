package com.example.yazuke.applimobilemachine_a_sous;

/**
 * Created by Skiwa on 23-Mar-18.
 */

public class Jeu {

        RouleauThread r1;
        RouleauThread r2;
        RouleauThread r3;

    Jeu(){

        //Création des threads pour chaque rouleau
        //  - Chaque thread crée un rouleau
        r1 = new RouleauThread(1);
        r2 = new RouleauThread(2);
        r3 = new RouleauThread(3);


        //Récupération du score etc


    }

    //Lance les threads
    public void demarrer(){
        r1.start();
        r2.start();
        r3.start();
    }

    public void arreterRouleau(int idRouleau){
        switch(idRouleau){
            case 1: r1.stop();
            break;
            case 2: r2.stop();
            break;
            case 3: r3.stop();
        }
    }


}
