package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;


public class Jeu {

        private RouleauThread r1;
        private RouleauThread r2;
        private RouleauThread r3;
        public int nbIterations=0;
        public boolean victoire=false;
        private boolean estLance=false;

    public Jeu(){
        //TODO, trucs à initialiser
    }


    ////////////////////////////
    // -- Démarrage du jeu -- //
    ////////////////////////////


    //Lance les threads
    public void demarrer(){
        nbIterations++;

        if(nbIterations==1){
            //  - Chaque thread crée un rouleau par défaut
            r1 = new RouleauThread(1);
            r2 = new RouleauThread(2);
            r3 = new RouleauThread(3);

            r1.start();
            r2.start();
            r3.start();
        }else{


            //  - Chaque thread crée un rouleau en fonction de ce qui était affiché avant

            Log.i("MaS","Before: r1 : "+r1.getRouleau().getSequenceAffichee());
            r1 = new RouleauThread(1,r1.getRouleau().getRoll());
            r2 = new RouleauThread(2,r2.getRouleau().getRoll());
            r3 = new RouleauThread(3,r3.getRouleau().getRoll());

            Log.i("MaS","After: r1 : "+r1.getRouleau().getSequenceAffichee());

            r1.start();
            r2.start();
            r3.start();
        }

        estLance=true;
        victoire=false;
    }



    ////////////////////////////////
    // -- Gestion des rouleaux -- //
    ////////////////////////////////


    public void arreterRouleau(int idRouleau) {
        if (nbIterations!=0){   //Evite de valider un jeu non lancé
            switch (idRouleau) {
                case 1:
                    r1.fini = true;
                    break;
                case 2:
                    r2.fini = true;
                    break;
                case 3:
                    r3.fini = true;
            }

            if (r1.fini && r2.fini && r3.fini) {
                Log.i("MaS", "***Tous les rouleaux arrêtés***");
                Log.i("MaS", "*** Rouleau 1 : " + r1.getRouleau().getSequenceAffichee()+" *** ");
                Log.i("MaS", "*** Rouleau 2 : " + r2.getRouleau().getSequenceAffichee()+" *** ");
                Log.i("MaS", "*** Rouleau 3 : " + r3.getRouleau().getSequenceAffichee()+" *** ");

                //TODO: à vérifier si utiles
                victoire=true;
                estLance=false;
            }
        }

    }


    //////////////////////
    // -- Accesseurs -- //
    //////////////////////

    public boolean estLance(){
        return estLance;
    }


}
