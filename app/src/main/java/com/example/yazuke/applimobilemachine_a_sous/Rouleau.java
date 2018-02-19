package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;

/**
 * Created by Skiwa on 18-Feb-18.
 */

public class Rouleau {

    private String[] sequence=new String[8];            //Séquence d'un rouleau
    private String[] sequenceAffichee=new String[3];    //3 symboles affichés à un moment t
    private int position=0;

    public Rouleau(int numeroRouleau){
        //Assignation de la séquence par défaut des rouleaux
        switch (numeroRouleau){
            case 1:
                sequence[0]="C";
                sequence[1]="O";
                sequence[2]="Cl";
                sequence[3]="F";
                sequence[4]="P";
                sequence[5]="7";
                sequence[6]="R";
                sequence[7]="Ci";
                break;
            case 2:
                sequence[0]="P";
                sequence[1]="Cl";
                sequence[2]="C";
                sequence[3]="O";
                sequence[4]="Ci";
                sequence[5]="R";
                sequence[6]="F";
                sequence[7]="7";
                break;
            case 3:
                sequence[0]="7";
                sequence[1]="C";
                sequence[2]="F";
                sequence[3]="Ci";
                sequence[4]="Cl";
                sequence[5]="C";
                sequence[6]="P";
                sequence[7]="O";
                break;
        }

        //Trois premières cases de la séquences, affichées en début de partie
        sequenceAffichee[0]=sequence[0];
        sequenceAffichee[1]=sequence[1];
        sequenceAffichee[2]=sequence[2];
    }



    public void roll(){ //Fait avancer un rouleau d'une case vers le bas
        position++; //si position%8=0, alors on est retourné à l'affichage de base du rouleau

        sequenceAffichee[0]=sequence[position%8];

        //Cas spéciaux
        if(position%8==6){
            sequenceAffichee[1]=sequence[7];
            sequenceAffichee[2]=sequence[0];
        }else if(position%8==7){
            sequenceAffichee[1]=sequence[0];
            sequenceAffichee[2]=sequence[1];
        }else{
            sequenceAffichee[1]=sequence[position%8+1];
            sequenceAffichee[2]=sequence[position%8+2];
        }
    }




    public String getSequence(){
        String res=new String();
        for (int i=0;i<7;i++){
            res+=sequence[i]+" ";
        }
        return res;
    }
    public String getSequenceAffichee(){
        String res=new String();
        for (int i=0;i<3;i++){
            res+=sequenceAffichee[i]+" ";
        }
        return res;
    }
    public int getPosition(){
        return position;
    }
}
