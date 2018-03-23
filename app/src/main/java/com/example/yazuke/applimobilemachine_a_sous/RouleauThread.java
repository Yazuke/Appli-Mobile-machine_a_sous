package com.example.yazuke.applimobilemachine_a_sous;


import android.util.Log;


public class RouleauThread extends Thread
{
    Rouleau rouleau;
    int id;

    //Crée un objet rouleau
    RouleauThread(int id){
        this.rouleau=new Rouleau(id);
        this.id=id;
    }

    //Fonction de démarrage du thread
    // - Renvoie périodiquement un message
    // - Fait avancer le rouleau d'une position avec rouleau.roll()
    public void run(){
        int count=0;
        while(true){
            if(count==5000000){
                Log.i("MaS",id+"");
                this.rouleau.roll();
                count=0;
            }
            count++;
        }

    }

}
