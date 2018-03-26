package com.example.yazuke.applimobilemachine_a_sous;

//Permet de gérer les communications entre les classes MainActivity et RouleauAsync

public interface AsyncListener {
    public void onProgressUpdate(int numRouleau,String prochain);
    public void onComplete(int numRouleau);
}