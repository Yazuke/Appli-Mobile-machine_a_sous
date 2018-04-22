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
import android.view.ViewDebug;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements AsyncListener, PopupMenu.OnMenuItemClickListener {

    private Jeu jeu;
    private ImageView levier;
    DatabaseHandler mDatabaseHandler;

    private ImageView[][] affichageRouleaux;
    private FrameLayout[][] layoutRouleaux;

    private RouleauAsync rouleauAsync1;
    private RouleauAsync rouleauAsync2;
    private RouleauAsync rouleauAsync3;
    private RouleauAsync[] rouleauxAsync=new RouleauAsync[3];
    private float distance;
    private int selectedID;
    private TextView score;

    private User user;
    private FrameLayout baseTop;

    private int[] countPos=new int[3];
    private long tempsRotation=200;
    private int cout=50;
    private int prix=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHandler=new DatabaseHandler(this);
        //////////////////////////////////////
        //////////////////////////////////////
        //--set up user--//
        //////////////////////////
        Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
        user=mDatabaseHandler.getUser(selectedID);
        //toastMessage(user.getPseudo() + user.getSolde());


        score = (TextView)findViewById(R.id.score);
        score.setText("" + user.getSolde()+"$");
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


        //Récupération des layouts pour chaque image (évite de redimensionner des images en les bougeant)
        FrameLayout f1_0 = findViewById(R.id.f1_0);
        FrameLayout f1_1 = findViewById(R.id.f1_1);
        FrameLayout f1_2 = findViewById(R.id.f1_2);
        FrameLayout f1_3 = findViewById(R.id.f1_3);

        FrameLayout f2_0 = findViewById(R.id.f2_0);
        FrameLayout f2_1 = findViewById(R.id.f2_1);
        FrameLayout f2_2 = findViewById(R.id.f2_2);
        FrameLayout f2_3 = findViewById(R.id.f2_3);

        FrameLayout f3_0 = findViewById(R.id.f3_0);
        FrameLayout f3_1 = findViewById(R.id.f3_1);
        FrameLayout f3_2 = findViewById(R.id.f3_2);
        FrameLayout f3_3 = findViewById(R.id.f3_3);

        //Range tout dans un tableau
        FrameLayout[] layoutRouleau1 = new FrameLayout[]{f1_0, f1_1,f1_2,f1_3};
        FrameLayout[] layoutRouleau2 = new FrameLayout[]{f2_0, f2_1,f2_2,f2_3};
        FrameLayout[] layoutRouleau3 = new FrameLayout[]{f3_0, f3_1,f3_2,f3_3};
        this.layoutRouleaux=new FrameLayout[][]{layoutRouleau1,layoutRouleau2,layoutRouleau3};


        //Layouts invisibles utilisés comme références
        this.baseTop=findViewById(R.id.baseTop);

        this.distance = getResources().getDimensionPixelSize(R.dimen.distance);

        //Utilisé pour compter le nombre de tours passés
        this.countPos[0]=0;
        this.countPos[1]=0;
        this.countPos[2]=0;




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

                        //Est rentré dans la zone de detection, on lance le jeu
                        if(view.getY()>=findViewById(R.id.target).getY()){
                            //Evite de lancer plusieurs fois le jeu
                            if(!jeu.estLance){

                                //Remet le fond par défaut
                                backgroundDefault();

                                //Démarre le jeu
                                jeu.demarrer();

                                //ajuste le solde
                                user.setSolde(user.getSolde()-cout);
                                score.setText("" + user.getSolde()+"$");
                                mDatabaseHandler.updateSolde(selectedID, user.getSolde());
                                //Remet les boutons par défaut
                                resetBoutons();

                                //Crée les async tasks, une par rouleau
                                rouleauAsync1 = new RouleauAsync(MainActivity.this,1, countPos[0]);
                                rouleauAsync2 = new RouleauAsync(MainActivity.this,2, countPos[1]);
                                rouleauAsync3 = new RouleauAsync(MainActivity.this,3, countPos[2]);

                                rouleauxAsync[0]=rouleauAsync1;
                                rouleauxAsync[1]=rouleauAsync2;
                                rouleauxAsync[2]=rouleauAsync3;

                                //Lance les trois async tasks simultanément, en threads
                                rouleauAsync1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                rouleauAsync2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                rouleauAsync3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                            }
                        }
                        break;

                    //Lors du relachement du bouton
                    //Evite les blocages, si le levier sort du cadre, on le replace correctement
                    case MotionEvent.ACTION_UP:

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



    public void arretRouleau(View v) {

        //Arrete le rouleau et l'async Task liés au bouton d'arret
        if (this.jeu.nbIterations != 0) {   //Evite les actions sur un jeu non lancé
            if(!jeu.fini){
                switch (v.getId()) {
                    case R.id.button_stop1:
                        Log.i("MaS", "--X-1-X--");
                        rouleauAsync1.cancel = true;
                        jeu.arreter(1);
                        findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopped);
                        break;
                    case R.id.button_stop2:
                        Log.i("MaS", "--X-2-X--");
                        rouleauAsync2.cancel = true;
                        jeu.arreter(2);
                        findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopped);
                        break;
                    case R.id.button_stop3:
                        Log.i("MaS", "--X-3-X--");
                        rouleauAsync3.cancel = true;
                        jeu.arreter(3);
                        findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopped);
                        break;
                }
            }
            if(jeu.fini){
                Log.i("MaS", "Jeu fini");
                jeu.estLance = false;
            }
        }
    }

    //Remet les boutons dans un état non cliqué
    public void resetBoutons(){
        findViewById(R.id.button_stop1).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop2).setBackgroundResource(R.drawable.stopper);
        findViewById(R.id.button_stop3).setBackgroundResource(R.drawable.stopper);
    }



    ////////////////////////////
    //-- Verif des rouleaux --//
    ////////////////////////////


    public void verifRouleaux(){
        //Récupère les trois symboles affichés
        String[] sequence1=rouleauAsync1.getRouleau().getAffichage();
        String[] sequence2=rouleauAsync2.getRouleau().getAffichage();
        String[] sequence3=rouleauAsync3.getRouleau().getAffichage();

        //Affiche les symboles affichés
        Log.i("MaS","Rouleau 1: "+sequence1[0]+" "+sequence1[1]+" "+sequence1[2]);
        Log.i("MaS","Rouleau 2: "+sequence2[0]+" "+sequence2[1]+" "+sequence2[2]);
        Log.i("MaS","Rouleau 3: "+sequence3[0]+" "+sequence3[1]+" "+sequence3[2]);




        boolean victoire=false;

        //Teste chaque ligne horizontale pour voir si elle est gagnante
        for(int i=0;i<3;i++){
            if(sequence1[i].equals(sequence2[i])&&sequence2[i].equals(sequence3[i])){
                ajustPrix(sequence1[i]);
                victoire=true;
                Log.i("MaS","WIN EN LIGNE "+(i+1)+", symbole: "+sequence1[i]);

            }
        }

        //Teste les diagonales
        if(sequence1[0].equals(sequence2[1])&&sequence2[1].equals(sequence3[2])){
            ajustPrix(sequence1[1]);
            victoire=true;
            Log.i("MaS","WIN EN DIAGONALE , symbole: "+sequence1[0]);
        }
        if(sequence1[2].equals(sequence2[1])&&sequence2[1].equals(sequence3[0])){
            ajustPrix(sequence1[1]);
            victoire=true;
            Log.i("MaS","WIN EN DIAGONALE , symbole: "+sequence1[2]);
        }

        //Fait clignoter le fond
        if(victoire){
            Blinker blinker=new Blinker();
            blinker.execute(1);
        }else{
            //Replace le levier au top
            levier=findViewById(R.id.levier);
            levier.animate()
                    .y(0)
                    .setDuration(500)
                    .start();

            //Remet les boutons dans un état non cliqué
            resetBoutons();
        }
        if(victoire==true){
            user.setSolde(user.getSolde()+prix);
            score.setText("" + user.getSolde()+"$");
            mDatabaseHandler.updateSolde(selectedID, user.getSolde());
            Log.i("MaS","LE PRIX EST DE: ---> "+prix);;
        }
    }



    ////////////////////
    //-- Background --//
    ////////////////////


    //Change le fond pour celui de victoire
    public void backgroundWin(){
        LinearLayout background=findViewById(R.id.background);

        background.setBackgroundResource(R.drawable.background_win);
    }

    //Change le fond pour celui par défaut
    public void backgroundDefault(){
        LinearLayout background=findViewById(R.id.background);
        background.setBackgroundResource(R.drawable.background_normal);
    }



    ////////////////////////////////
    //-- Animation des rouleaux --//
    ////////////////////////////////


    public void animationRouleau(int id,String prochain){
        id--;        //puisque le rouleau 1 a l'id 0

        //Récupère le temps de rotation d'un rouleauAsync (pour permettre une animation propre)
        tempsRotation=RouleauAsync.temps;
        tempsRotation/=(id+1);

        //Permet d'optimiser le code et ne pas répéter toutes les opérations dans le switch qui suit
        int cases[]=new int[4];

        //Rend les animations linéaires
        Interpolator interpolator=new LinearInterpolator();

        //Défini l'emplacement de chaque case (puisqu'elles se déplacent lors de l'animation)
        switch(countPos[id]%4) {
            case 0:
                cases[0] = 0;
                cases[1] = 1;
                cases[2] = 2;
                cases[3] = 3;
                break;
            case 1:
                cases[0] = 3;
                cases[1] = 0;
                cases[2] = 1;
                cases[3] = 2;
                break;
            case 2:
                cases[0] = 2;
                cases[1] = 3;
                cases[2] = 0;
                cases[3] = 1;
                break;
            case 3:
                cases[0] = 1;
                cases[1] = 2;
                cases[2] = 3;
                cases[3] = 0;
                break;
        }

        //Remplace la prochaine image du rouleau par l'image correspondante
            switch(prochain){
                case "C": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.cerise);break;
                case "Ci": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.citron);break;
                case "Cl": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.cloche);break;
                case "F": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.fraise);break;
                case "O": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.orange);break;
                case "P": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.pasteque);break;
                case "R": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.raisin);break;
                case "7": affichageRouleaux[id][cases[3]].setImageResource(R.drawable.sept);break;
            }

        //Renvoie la case cachée en bas vers le haut (cachée aussi)
        layoutRouleaux[id][cases[3]].animate()
                .y(baseTop.getY())
                .setStartDelay(0)
                .setDuration(0)
                .start();

        //Anime tous les rouleaux vers le bas
        layoutRouleaux[id][cases[0]].animate()
                .y(layoutRouleaux[id][cases[0]].getY() + distance)
                .setInterpolator(interpolator)
                .setDuration(tempsRotation)
                .start();
        layoutRouleaux[id][cases[1]].animate()
                .y(layoutRouleaux[id][cases[1]].getY() + distance)
                .setInterpolator(interpolator)
                .setDuration(tempsRotation)
                .start();

        layoutRouleaux[id][cases[2]].animate()
                .y(layoutRouleaux[id][cases[2]].getY() + distance)
                .setInterpolator(interpolator)
                .setDuration(tempsRotation)
                .start();
        layoutRouleaux[id][cases[3]].animate()
                .y(layoutRouleaux[id][cases[3]].getY() + distance)
                .setInterpolator(interpolator)
                .setDuration(tempsRotation)
                .start();

        //Indique qu'un tour a eu lieu
        this.countPos[id]++;
    }

    //Replace les cases à leur emplacement fixe
    public void correctionPosition(int id){
        id--;   //puisque le rouleau 1 a l'id 0


        //Récupère le temps de rotation d'un rouleauAsync (pour permettre une animation propre)
        tempsRotation=RouleauAsync.temps;

        //Permet d'optimiser le code et ne pas répéter toutes les opérations dans le switch qui suit
        int cases[]=new int[4];

        //Défini l'emplacement de chaque case (puisqu'elles se déplacent lors de l'animation)
        switch(countPos[id]%4) {
            case 0:
                cases[0] = 0;
                cases[1] = 1;
                cases[2] = 2;
                cases[3] = 3;
                break;
            case 1:
                cases[0] = 3;
                cases[1] = 0;
                cases[2] = 1;
                cases[3] = 2;
                break;
            case 2:
                cases[0] = 2;
                cases[1] = 3;
                cases[2] = 0;
                cases[3] = 1;
                break;
            case 3:
                cases[0] = 1;
                cases[1] = 2;
                cases[2] = 3;
                cases[3] = 0;
                break;
        }

        //Fixe les cases à leur emplacement défini
        layoutRouleaux[id][cases[0]].animate()
                .y(baseTop.getY()+distance)
                .setDuration(tempsRotation)
                .start();

        layoutRouleaux[id][cases[1]].animate()
                .y(baseTop.getY()+(distance*2))
                .setDuration(tempsRotation)
                .start();

        layoutRouleaux[id][cases[2]].animate()
                .y(baseTop.getY()+(distance*3))
                .setDuration(tempsRotation)
                .start();

        layoutRouleaux[id][cases[3]].animate()
                .y(baseTop.getY()+(distance*4))
                .setDuration(tempsRotation)
                .start();
    }


    ///////////////////////////////////
    // -- Gestion des async tasks -- //
    ///////////////////////////////////

    //On reçoit les informations grace à AsyncListener qui fait le lien avec les AsyncTasks


    //Récéption d'un numéro de rouleau lorsqu'il fait un tour et du prochain symbole à arriver
    @Override
    public void onProgressUpdate(int numRouleau,String prochain) {
        animationRouleau(numRouleau,prochain);
    }


    //Lors de la fin d'une AsyncTask (d'un rouleau), on corrige sa position.
    // Si ils sont tous arretés, on lance la vérification
    @Override
    public void onComplete(int numRouleau, String prochain){
        correctionPosition(numRouleau);
        if(!jeu.estLance){
            verifRouleaux();
        }
    }



    //////////////////////
    //-- Intent combo --//
    //////////////////////


    public void combo(View v) {
        Intent intent = new Intent(MainActivity.this, ComboActivity.class);
        startActivity(intent);
    }



    //////////////
    //-- Menu --//
    //////////////


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1: //Changé utilisateur// Redirige sur la page de login
                Intent intent2 = new Intent(MainActivity.this,
                        Login.class);
                startActivity(intent2);
                return true;
            case R.id.item2: // Retour au menu principal
                Intent intent = new Intent(MainActivity.this, // New intent avec en paramètres // le contex de l'application et la classe menu.
                        Menu.class);
                startActivity(intent);
                return true;
            case R.id.item3:
                Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            default:
                return false;
        }
    }



    public void showPopup(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    public void quitApp(){finish();}


    public void ajustPrix(String s){

        switch (s){
            case "C":
                prix = 100;
                break;

            case "O":
                prix = 200;
                break;

            case "Cl":
                prix = 300;
                break;

            case "F":
                prix = 400;
                break;

            case "P":
                prix = 500;
                break;

            case "R":
                prix = 600;
                break;

            case "Ci":
                prix = 700;
                break;

            case "7":
                prix = 1800;
                break;
        }
    }




    ///////////////////////////////////////////
    //-- Clignotement du fond (async task) --//
    ///////////////////////////////////////////


    private class Blinker extends AsyncTask<Integer, Integer, Integer> {
        private boolean on=false;
        private boolean fini=false;
        //Chaque 800ms, envoie publishProgress()
        @Override
        protected Integer doInBackground(Integer...params) {
            int i=0;
            while(i<4){
                if (i==3)
                    fini=true;
                try {
                    on=!on;
                    i++;
                    Thread.sleep(800);
                    publishProgress();
                } catch (InterruptedException e) {e.printStackTrace();}
            }
            return 1;
        }

        //Change le fond pour l'autre afin de créer un clignotement
        //Une fois fini, on replace le levier et reset boutons
        @Override
        protected void onProgressUpdate(Integer...params){
            if(on)
                backgroundDefault();
            else
                backgroundWin();

            if(fini){
                //Replace le levier au top
                levier=findViewById(R.id.levier);
                levier.animate()
                        .y(0)
                        .setDuration(500)
                        .start();

                resetBoutons();
            }
        }
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}







