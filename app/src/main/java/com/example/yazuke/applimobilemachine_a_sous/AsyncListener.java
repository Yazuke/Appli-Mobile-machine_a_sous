package com.example.yazuke.applimobilemachine_a_sous;

//Permet de gérer les communications entre les classes MainActivity et RouleauAsync

public interface AsyncListener {
    //Lorsqu'un tour a eu lieu
    public void onProgressUpdate(int numRouleau,String prochain);

    //Lorsqu'un rouleau a fini de tourner
    public void onComplete(int numRouleau,String prochain);
}