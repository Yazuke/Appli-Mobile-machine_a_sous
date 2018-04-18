package com.example.yazuke.applimobilemachine_a_sous;

/**
 * Created by Yazuke on 2018-04-15.
 */

public class User {
    private String pseudo;
    private String mdp;
    private int solde;

    public User(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.solde=1000;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPesudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getSolde(){
        return solde;
    }

    public void setSolde(int solde){
        this.solde=solde;
    }

}
