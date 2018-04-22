package com.example.yazuke.applimobilemachine_a_sous;

/**
 * Created by Yazuke on 2018-04-15.
 */

public class User {
    private String pseudo;
    private int solde;

    public User(String pseudo) {
        this.pseudo = pseudo;
        this.solde=1000;
    }

    public User(String pseudo, int solde) {
        this.pseudo = pseudo;
        this.solde=solde;
    }

    public User() {

    }
    public String getPseudo() {
        return pseudo;
    }

    public void setPesudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public int getSolde(){
        return solde;
    }

    public void setSolde(int solde){
        this.solde=solde;
    }

}
