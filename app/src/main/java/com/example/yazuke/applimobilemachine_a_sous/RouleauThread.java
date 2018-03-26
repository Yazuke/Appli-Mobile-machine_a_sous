package com.example.yazuke.applimobilemachine_a_sous;


import android.util.Log;


public class RouleauThread extends Thread
{
    private Rouleau rouleau;
    private int id;
    public boolean fini;

    /////////////////////////
    // -- Constructeurs -- //
    /////////////////////////

    //Crée un objet rouleau
    public RouleauThread(int id){
        this.rouleau=new Rouleau(id);
        this.id=id;
    }

    //Crée un rouleau avec sa séquence précédente (nouvelle partie)
    public RouleauThread(int id, int roll){
        this.rouleau=new Rouleau(id, roll);
        this.id=id;
    }


    ///////////////////////////////
    // -- Fonction principale -- //
    ///////////////////////////////


    //Fonction de démarrage du thread
    // - Renvoie périodiquement un message
    // - Fait avancer le rouleau d'une position avec rouleau.roll()
    public void run(){
        int count=0;
        while(!fini){                           //fini passe à true lors d'un clic sur un bouton d'arret
            if(count==5000000/id){              //division par 1,2 ou 3 pour des vitesses de défilement différentes
                Log.i("MaS",id+"");
                this.rouleau.roll();

                //appel MainActivity.animationRouleaux();

                count=0;
            }
            count++;
        }

    }


    //////////////////////
    // -- Accesseurs -- //
    //////////////////////


    public Rouleau getRouleau(){
        return rouleau;
    }


}
