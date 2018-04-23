package com.example.yazuke.applimobilemachine_a_sous;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Yazuke on 2018-04-19.
 */

public class EditDataActivity extends AppCompatActivity {
    private static final String TAG = "EditDataActivity";
    private Button btnSave;
    private TextView btnPlay;
    private EditText editable_item;
    DatabaseHandler mDatabaseHandler;
    private String selectedName;
    private int selectedID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave=(Button) findViewById(R.id.save);
        btnPlay=(TextView)findViewById(R.id.play);

        editable_item=(EditText) findViewById(R.id.editable_item);
        mDatabaseHandler=new DatabaseHandler(this);
        //On récupère les infos de l'Intent
        Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
        selectedName=receivedIntent.getStringExtra("name");
        //toastMessage(selectedName);
        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item=editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHandler.updateName(item,selectedID,selectedName);
                }else{
                    toastMessage("Vous devez entrer un pseudo");
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"play: L'ID "+selectedID+" démarre la partie");
                Intent editIntent = new Intent(EditDataActivity.this,MainActivity.class);
                editIntent.putExtra("id",selectedID);
                startActivity(editIntent);
            }
        });
    }

    public void btnDelete(View vue) {
        final AlertDialog.Builder monDialogue = new AlertDialog.Builder(this);
        monDialogue.setTitle("Attention !");
        monDialogue.setMessage("Voulez-vous vraiment supprimer ce compte ?");

        //Bouton du dialogue
        monDialogue.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                mDatabaseHandler.supprimer(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("Compte supprimé");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        monDialogue.setNegativeButton("Non", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        monDialogue.show();
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
