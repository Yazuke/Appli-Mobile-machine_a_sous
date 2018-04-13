package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements AsyncListener, PopupMenu.OnMenuItemClickListener {

    private Jeu jeu;
    private ImageView levier;

    private ImageView[][] affichageRouleaux;

    private RouleauAsync rouleauAsync1;
    private RouleauAsync rouleauAsync2;
    private RouleauAsync rouleauAsync3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////////////////////////
        //////////////////////////////////////





        /////////////////////////////
        //-- Images des rouleaux --//
        /////////////////////////////

        //Récupération des images utilisées pour l'affichage
        ImageView r1_0 = findViewById(R.id.r1_0);
        ImageView r1_1 = findViewById(R.id.r1_1);
        ImageView r1_2 = findViewById(R.id.r1_2);
        ImageView r1_3 = findViewById(R.id.r1_3);

        ImageView r2_0 = findViewById(R.id.r2_0);
        ImageView r2_1 = findViewById(R.id.r2_1);
        ImageView r2_2 = findViewById(R.id.r2_2);
        ImageView r2_3 = findViewById(R.id.r2_3);

        ImageView r3_0 = findViewById(R.id.r3_0);
        ImageView r3_1 = findViewById(R.id.r3_1);
        ImageView r3_2 = findViewById(R.id.r3_2);
        ImageView r3_3 = findViewById(R.id.r3_3);

        //Range tout dans un tableau affichageRouleaux[numRouleau][numCase]
        ImageView[] affichageRouleaux1 = new ImageView[]{r1_0, r1_1,r1_2,r1_3};
        ImageView[] affichageRouleaux2 = new ImageView[]{r2_0, r2_1,r2_2,r2_3};
        ImageView[] affichageRouleaux3 = new ImageView[]{r3_0, r3_1,r3_2,r3_3};
        this.affichageRouleaux=new ImageView[][]{affichageRouleaux1,affichageRouleaux2,affichageRouleaux3};





        ////////////////////////
        //-- Gestion du jeu --//
        ////////////////////////

        //Création du jeu
        this.jeu=new Jeu();





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
                        dY = view.getY() - event.getRawY();//Récupère sa position relative
                        break;

                    //Lors d'un mouvement du bouton
                    case MotionEvent.ACTION_MOVE:
                        //Ne peut pas avoir une position négative, ni trop forte.
                        if(view.getY()>=0 && view.getY()<findViewById(R.id.target).getY()){//Teste si le bouton ne dépasse pas les bords
                            view.animate()
                                    .y(event.getRawY() + dY) //Déplace le bouton et le verrouille verticalement
                                    .setDuration(0)
                                    .start();
                        }

                        if(view.getY()>=findViewById(R.id.target).getY()){//Est rentré dans la zone de detection, on lance le jeu
                            if(!jeu.estLance){                          //Evite de lancer plusieurs fois le jeu

                                jeu.demarrer();

                                resetBoutons();

                                //Crée les async tasks, une par rouleau
                                rouleauAsync1 = new RouleauAsync(MainActivity.this,1);
                                rouleauAsync2 = new RouleauAsync(MainActivity.this,2);
                                rouleauAsync3 = new RouleauAsync(MainActivity.this,3);


                                //Lance les trois async tasks simultanément, en threads
                                rouleauAsync1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                rouleauAsync2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                rouleauAsync3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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

        if(this.jeu.nbIterations!=0){   //Evite les actions sur un jeu non lancé
            switch(v.getId()){
                case R.id.button_stop1:
                    Log.i("MaS","Arrêt rouleau 1");
                    rouleauAsync1.cancel=true;
                    jeu.arreter(1);
                    findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopped);
                    break;
                case R.id.button_stop2:
                    Log.i("MaS","Arrêt rouleau 2");
                    rouleauAsync2.cancel=true;
                    jeu.arreter(2);
                    findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopped);
                    break;
                case R.id.button_stop3:
                    Log.i("MaS","Arrêt rouleau 3");
                    rouleauAsync3.cancel=true;
                    jeu.arreter(3);
                    findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopped);
                    break;
            }

            //Change le background si victoire
            if(jeu.victoire){
                Log.i("MaS","Jeu fini");

                //Change le background pour celui éclairé
                //LinearLayout background=findViewById(R.id.background);
                //background.setBackgroundResource(R.drawable.background_win);

                jeu.estLance=false;
                //Remet les boutons dans un été non cliqué
                resetBoutons();

                //Replace le levier au top
                levier=findViewById(R.id.levier);
                levier.animate()
                        .y(0)
                        .setDuration(500)
                        .start();

            }
        }

    }

    public void resetBoutons(){
        findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopper);

    }





    ////////////////////////////////
    //-- Animation des rouleaux --//
    ////////////////////////////////

    public void animationRouleau(int id,String prochain){
        id=id-1; //rouleau 1 a l'id 0

//
//        affichageRouleaux[id][0].setBackgroundColor(Color.GREEN);
//        affichageRouleaux[id][1].setBackgroundColor(Color.BLUE);
//        affichageRouleaux[id][2].setBackgroundColor(Color.RED);
//        affichageRouleaux[id][3].setBackgroundColor(Color.MAGENTA);
//


        //Place la prochaine image, anime le rouleau choisi d'une case vers le bas et remonte toutes les cases (pour boucler)



        //Place la prochaine image
        switch(prochain){
            //On remplace l'image 0 du rouleau choisi (id) par l'image correspondante
                    case "C": affichageRouleaux[id][0].setImageResource(R.drawable.cerise);break;
                    case "Ci": affichageRouleaux[id][0].setImageResource(R.drawable.citron);break;
                    case "Cl": affichageRouleaux[id][0].setImageResource(R.drawable.cloche);break;
                    case "F": affichageRouleaux[id][0].setImageResource(R.drawable.fraise);break;
                    case "O": affichageRouleaux[id][0].setImageResource(R.drawable.orange);break;
                    case "P": affichageRouleaux[id][0].setImageResource(R.drawable.pasteque);break;
                    case "R": affichageRouleaux[id][0].setImageResource(R.drawable.raisin);break;
                    case "7": affichageRouleaux[id][0].setImageResource(R.drawable.sept);break;
        }


        //Anime le rouleau vers le bas
        affichageRouleaux[id][0].animate()
            .y(affichageRouleaux[id][0].getY()+200)
            .setDuration(300)
            .start();

        affichageRouleaux[id][1].animate()
                .y(affichageRouleaux[id][1].getY()+200)
                .setDuration(300)
                .start();

        affichageRouleaux[id][2].animate()
                .y(affichageRouleaux[id][2].getY()+200)
                .setDuration(300)
                .start();

        affichageRouleaux[id][3].animate()
                .y(affichageRouleaux[id][3].getY()+200)
                .setDuration(300)
                .start();


        //Les symboles sont réassignés
//        affichageRouleaux[id][0]=affichageRouleaux[id][3];
//        affichageRouleaux[id][3]=affichageRouleaux[id][2];
//        affichageRouleaux[id][2]=affichageRouleaux[id][1];
//        affichageRouleaux[id][1]=affichageRouleaux[id][0];
//


//            switch(id){
//                default:
//                    affichageRouleaux[id][3].animate()
//                            .setStartDelay(300)
//                            .setDuration(0)
//                            .y(defautY0)
//                            .start();
//                    break;
//
//            }
////
//
//            affichageRouleaux[id][0]=affichageRouleaux[id][3];
//            affichageRouleaux[id][1]=affichageRouleaux[id][0];
//            affichageRouleaux[id][2]=affichageRouleaux[id][1];
//            affichageRouleaux[id][3]=affichageRouleaux[id][2];
        }





    ///////////////////////////////////
    // -- Gestion des async tasks -- //
    ///////////////////////////////////

    //On reçoit les informations grace à AsyncListener qui fait le lien avec les AsyncTasks
    @Override
    public void onProgressUpdate(int numRouleau,String prochain) {      //Récéption d'un numéro de rouleau lorsqu'il fait un tour et du prochain symbole à arriver

        animationRouleau(numRouleau,prochain);

    }


    @Override
    public void onComplete(int numRouleau){

    }

    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    public void combo(View view){
        Intent i = new Intent(this, ComboActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                return true;
            case R.id.item2:
                return true;
            case R.id.item3:
                return true;
            case R.id.item4:
                return true;
            default:
                return false;
        }
    }
}






