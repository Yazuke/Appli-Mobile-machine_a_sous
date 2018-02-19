package com.example.yazuke.applimobilemachine_a_sous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création des rouleaux et assignation de leur séquence
        Rouleau rouleau1=new Rouleau(1);
        Rouleau rouleau2=new Rouleau(2);
        Rouleau rouleau3=new Rouleau(3);


//        Log.i("MaS",rouleau1.getSequenceAffichee());
//        Log.i("MaS",rouleau2.getSequenceAffichee());
//        Log.i("MaS",rouleau3.getSequenceAffichee());

        for (int i=0;i<20;i++){
            Log.i("MaS",rouleau1.getPosition()+"");
            Log.i("MaS",rouleau1.getSequenceAffichee());
            rouleau1.roll();
        }


//        Log.i("MaS",rouleau1.getSequenceAffichee());
//        Log.i("MaS",rouleau2.getSequenceAffichee());
//        Log.i("MaS",rouleau3.getSequenceAffichee());

    }


}
