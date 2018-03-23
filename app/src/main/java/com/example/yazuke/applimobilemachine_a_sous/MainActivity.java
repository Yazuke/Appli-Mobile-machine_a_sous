package com.example.yazuke.applimobilemachine_a_sous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Jeu jeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération des images utilisées pour l'affichage
        ImageView r1_1 = (ImageView) findViewById(R.id.r1_1);
        ImageView r1_2 = (ImageView) findViewById(R.id.r1_2);
        ImageView r1_3 = (ImageView) findViewById(R.id.r1_3);

        ImageView r2_1 = (ImageView) findViewById(R.id.r2_1);
        ImageView r2_2 = (ImageView) findViewById(R.id.r2_2);
        ImageView r2_3 = (ImageView) findViewById(R.id.r2_3);

        ImageView r3_1 = (ImageView) findViewById(R.id.r3_1);
        ImageView r3_2 = (ImageView) findViewById(R.id.r3_2);
        ImageView r3_3 = (ImageView) findViewById(R.id.r3_3);

        //Regroupement par rouleau
        ImageView[] affichageRouleau1={r1_1,r1_2,r1_3};
        ImageView[] affichageRouleau2={r2_1,r2_2,r2_3};
        ImageView[] affichageRouleau3={r3_1,r3_2,r3_3};

        //Création du jeu
        this.jeu=new Jeu();

        //Lancement du jeu
        this.jeu.demarrer();


        //Event listener pour les boutons
        //  - Recup id du rouleau visé
        //  - Lancer jeu.arreterRouleau(id)


    }

    public void arretRouleau(View v){

        switch(v.getId()){
            case R.id.button_stop1:
                Log.i("MaS","Arrêt rouleau 1");
                this.jeu.arreterRouleau(1);
            break;
            case R.id.button_stop2:
                Log.i("MaS","Arrêt rouleau 2");
                this.jeu.arreterRouleau(2);
                break;
            case R.id.button_stop3:
                Log.i("MaS","Arrêt rouleau 3");
                this.jeu.arreterRouleau(3);
                break;
        }
    }
}



//mise à jour rouleau 1 affichage
//            for(int i=0;i<3;i++){
//                switch(rouleau1.getCaseAffichee(i)){
//                    case "C": affichageRouleau1[i].setImageResource(R.drawable.cerise);break;
//                    case "Ci": affichageRouleau1[i].setImageResource(R.drawable.citron);break;
//                    case "Cl": affichageRouleau1[i].setImageResource(R.drawable.cloche);break;
//                    case "F": affichageRouleau1[i].setImageResource(R.drawable.fraise);break;
//                    case "O": affichageRouleau1[i].setImageResource(R.drawable.orange);break;
//                    case "P": affichageRouleau1[i].setImageResource(R.drawable.pasteque);break;
//                    case "R": affichageRouleau1[i].setImageResource(R.drawable.raisin);break;
//                    case "7": affichageRouleau1[i].setImageResource(R.drawable.sept);break;
//                }
//            }
//
//            //mise à jour rouleau 2 affichage
//            for(int i=0;i<3;i++){
//                switch(rouleau2.getCaseAffichee(i)){
//                    case "C": affichageRouleau2[i].setImageResource(R.drawable.cerise);break;
//                    case "Ci": affichageRouleau2[i].setImageResource(R.drawable.citron);break;
//                    case "Cl": affichageRouleau2[i].setImageResource(R.drawable.cloche);break;
//                    case "F": affichageRouleau2[i].setImageResource(R.drawable.fraise);break;
//                    case "O": affichageRouleau2[i].setImageResource(R.drawable.orange);break;
//                    case "P": affichageRouleau2[i].setImageResource(R.drawable.pasteque);break;
//                    case "R": affichageRouleau2[i].setImageResource(R.drawable.raisin);break;
//                    case "7": affichageRouleau2[i].setImageResource(R.drawable.sept);break;
//                }
//            }
//
//            //mise à jour rouleau 3 affichage
//            for(int i=0;i<3;i++){
//                switch(rouleau3.getCaseAffichee(i)){
//                    case "C": affichageRouleau3[i].setImageResource(R.drawable.cerise);break;
//                    case "Ci": affichageRouleau3[i].setImageResource(R.drawable.citron);break;
//                    case "Cl": affichageRouleau3[i].setImageResource(R.drawable.cloche);break;
//                    case "F": affichageRouleau3[i].setImageResource(R.drawable.fraise);break;
//                    case "O": affichageRouleau3[i].setImageResource(R.drawable.orange);break;
//                    case "P": affichageRouleau3[i].setImageResource(R.drawable.pasteque);break;
//                    case "R": affichageRouleau3[i].setImageResource(R.drawable.raisin);break;
//                    case "7": affichageRouleau3[i].setImageResource(R.drawable.sept);break;
//                }
//            }
// }







