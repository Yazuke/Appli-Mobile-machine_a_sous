package com.example.yazuke.applimobilemachine_a_sous;

import android.util.Log;


public class Jeu {

        private RouleauThread r1;
        private RouleauThread r2;
        private RouleauThread r3;

        public boolean victoire=false;

    public Jeu(){

        //Création des threads pour chaque rouleau
        //  - Chaque thread crée un rouleau
        r1 = new RouleauThread(1);
        r2 = new RouleauThread(2);
        r3 = new RouleauThread(3);


        //Récupération du score etc


    }

    //Lance les threads
    public void demarrer(){
        r1.start();
        r2.start();
        r3.start();
    }

    public void arreterRouleau(int idRouleau) {
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
            Log.i("MaS", "***Tous les rouleaux arrêtés");
            Log.i("MaS", "***Rouleau 1 : " + r1.getRouleau().getSequenceAffichee());
            Log.i("MaS", "***Rouleau 2 : " + r2.getRouleau().getSequenceAffichee());
            Log.i("MaS", "***Rouleau 3 : " + r3.getRouleau().getSequenceAffichee());

            victoire=true;  //pour tester
        }
    }


}
