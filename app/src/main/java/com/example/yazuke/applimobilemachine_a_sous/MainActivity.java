package com.example.yazuke.applimobilemachine_a_sous;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Jeu jeu;
    ImageView levier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////
        //-- Images des rouleaux --//
        /////////////////////////////

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



        ////////////////////////
        //-- Gestion du jeu --//
        ////////////////////////

        //Création du jeu
        this.jeu=new Jeu();

        //Lancement du jeu
        //this.jeu.demarrer();




        ///////////////////////////
        //-- Gestion du levier --//
        ///////////////////////////

        //Evenements lors d'un drag sur le levier
        final class MyTouchListener implements View.OnTouchListener {
            float dY;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    //Lors d'un clic sur le bouton
                    case MotionEvent.ACTION_DOWN:
                        dY = view.getY() - event.getRawY();              //Récupère sa position relative
                        break;

                    //Lors d'un mouvement du bouton
                    case MotionEvent.ACTION_MOVE:
                        //Ne peut pas avoir une position négative, ni trop forte.
                        if(view.getY()>=0 && view.getY()<findViewById(R.id.target).getY()){    //Teste si le bouton ne dépasse pas les bords
                            view.animate()
                                    .y(event.getRawY() + dY)                                    //Déplace le bouton et le verrouille verticalement
                                    .setDuration(0)
                                    .start();
                        }

                        if(view.getY()>=findViewById(R.id.target).getY()){   //Est rentré dans la zone de detection, on lance le jeu
                            if(!jeu.estLance()){                            //Evite de lancer plusieurs fois le jeu

                                jeu.demarrer();
                                Log.i("MaS","Lancement du jeu");

                                resetBoutons();
                            }
                        }
                        break;

                    //Lors du relachement du bouton
                    case MotionEvent.ACTION_UP:
                        //Evite les blocages, si le levier sort du cadre, on le replace correctement

                        //Si le levier est trop haut
                        if(view.getY()<0)
                            view.setY(0);

                        //Si il descend plus bas que la cible (trop bas)
                        if(view.getY()>=findViewById(R.id.target).getY())
                            view.setY(findViewById(R.id.target).getY());

                    default:
                        return false;
                }
                return true;
            }
        }

        findViewById(R.id.levier).setOnTouchListener(new MyTouchListener());









    }


    /////////////////////////
    //-- Boutons d'arrêt --//
    /////////////////////////

    public void arretRouleau(View v){

        switch(v.getId()){
            case R.id.button_stop1:
                Log.i("MaS","Arrêt rouleau 1");
                this.jeu.arreterRouleau(1);
                findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopped);
            break;
            case R.id.button_stop2:
                Log.i("MaS","Arrêt rouleau 2");
                this.jeu.arreterRouleau(2);
                findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopped);
                break;
            case R.id.button_stop3:
                Log.i("MaS","Arrêt rouleau 3");
                this.jeu.arreterRouleau(3);
                findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopped);
                break;
        }

        //Change le background si victoire
        if(jeu.victoire){
            LinearLayout background=findViewById(R.id.background);
            background.setBackgroundResource(R.drawable.background_win);
        }
    }

    public void resetBoutons(){
        findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopper);

    }




}



//mise à jour des affichages (pas encore fonctionnel, ne pas supprimer)
//            rouleau 1

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
//            rouleau 2

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
//            rouleau 3

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







