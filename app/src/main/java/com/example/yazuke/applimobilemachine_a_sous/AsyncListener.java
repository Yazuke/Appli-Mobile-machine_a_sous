package com.example.yazuke.applimobilemachine_a_sous;

/**
 * Created by Skiwa on 26-Mar-18.
 */

public interface AsyncListener {
    public void onProgressUpdate(int numRouleau,String prochain);
    public void onComplete(int numRouleau);
}