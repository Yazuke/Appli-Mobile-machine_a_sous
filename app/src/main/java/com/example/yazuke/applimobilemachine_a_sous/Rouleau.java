package com.example.yazuke.applimobilemachine_a_sous;


public class Rouleau {

    private String[] sequence=new String[8];            //Séquence d'un rouleau
    private String[] sequenceAffichee=new String[3];    //3 symboles affichés à un moment t
    private int roll=0;


    /////////////////////////
    // -- Constructeurs -- //
    /////////////////////////


    //Crée un rouleau en fonction de son id et de sa séquence par défaut
    public Rouleau(int numeroRouleau){
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
                sequence[1]="R";
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

    //Crée un rouleau en fonction de son id et de sa séquence par défaut à une position donnée (reprise de partie)
    public Rouleau(int id, int roll){
        switch (id){
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
                sequence[5]="R";
                sequence[6]="P";
                sequence[7]="O";
                break;
        }

        sequenceAffichee[0]=sequence[0];
        sequenceAffichee[1]=sequence[1];
        sequenceAffichee[2]=sequence[2];

        //Tours passés en fin de partie précédente
        this.roll(roll);
    }


    //////////////////////////////////
    // -- Fonctions de roulement -- //
    //////////////////////////////////


    public void roll(){ //Fait avancer un rouleau d'une case vers le bas
        this.roll++; //si position%8=0, alors on est retourné à l'affichage de base du rouleau

        this.sequenceAffichee[0]=this.sequence[roll%8];

        //Cas spéciaux
        if(roll%8==6){
            this.sequenceAffichee[1]=this.sequence[7];
            this.sequenceAffichee[2]=this.sequence[0];
        }else if(roll%8==7){
            this.sequenceAffichee[1]=this.sequence[0];
            this.sequenceAffichee[2]=this.sequence[1];
        }else{
            this.sequenceAffichee[1]=this.sequence[roll%8+1];
            this.sequenceAffichee[2]=this.sequence[roll%8+2];
        }
    }

    public void roll(int roll){ //Fait avancer un rouleau d'un nombre de cases
        this.roll+=roll;

        this.sequenceAffichee[0]=this.sequence[roll%8];

        //Cas spéciaux
        if(roll%8==6){
            this.sequenceAffichee[1]=this.sequence[7];
            this.sequenceAffichee[2]=this.sequence[0];
        }else if(roll%8==7){
            this.sequenceAffichee[1]=this.sequence[0];
            this.sequenceAffichee[2]=this.sequence[1];
        }else{
            this.sequenceAffichee[1]=this.sequence[roll%8+1];
            this.sequenceAffichee[2]=this.sequence[roll%8+2];
        }
    }


    //////////////////////
    // -- Accesseurs -- //
    //////////////////////


    public String getSequence(){
        String res=new String();
        for (int i=0;i<7;i++){
            res+=sequence[i]+" ";
        }
        return res;
    }
    public String[] getSequenceAffichee(){
        String[] res=new String[3];

        for (int i=0;i<3;i++){
            res[i]=sequenceAffichee[i]+" ";
        }
        return res;
    }
    public int getRoll(){return roll;}
    public String getSequenceAffichee(int n){
        return sequenceAffichee[n];
    }
    public String get1(){return sequence[(roll+1)%8];}
    public String get2(){return sequence[(roll+2)%8];}
    public String get3(){return sequence[(roll+3)%8];}
    public String[] getAffichage(){
        String[] res=new String[3];
        res[0]=sequence[(roll+1)%8];
        res[1]=sequence[(roll+2)%8];
        res[2]=sequence[(roll+3)%8];

        return res;
    }
    public String getProchain(){return sequence[(roll+3)%8];}
}
