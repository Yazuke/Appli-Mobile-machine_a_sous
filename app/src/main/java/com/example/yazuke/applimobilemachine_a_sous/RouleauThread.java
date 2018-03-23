package com.example.yazuke.applimobilemachine_a_sous;


import android.util.Log;


public class RouleauThread extends Thread
{
    private Rouleau rouleau;
    private int id;
    public boolean fini;

    //Crée un objet rouleau
    public RouleauThread(int id){
        this.rouleau=new Rouleau(id);
        this.id=id;
    }


    //Fonction de démarrage du thread
    // - Renvoie périodiquement un message
    // - Fait avancer le rouleau d'une position avec rouleau.roll()
    public void run(){
        int count=0;
        while(!fini){                           //fini passe à true lors d'un clic sur un bouton d'arret
            if(count==5000000/id){              //division par 1,2 ou 3 pour des vitesses de défilement différentes
                Log.i("MaS",id+"");
                this.rouleau.roll();
                count=0;
            }
            count++;
        }

    }

    public Rouleau getRouleau(){
        return rouleau;
    }


}
