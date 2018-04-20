package com.example.yazuke.applimobilemachine_a_sous;

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
    private Button btnSave,btnDelete;
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
        btnDelete=(Button) findViewById(R.id.delete);
        btnPlay=(TextView)findViewById(R.id.play);
        editable_item=(EditText) findViewById(R.id.editable_item);
        mDatabaseHandler=new DatabaseHandler(this);
        //On récupère les infos de l'Intent
        Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
        selectedName=receivedIntent.getStringExtra("name");

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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHandler.supprimer(selectedID,selectedName);
                editable_item.setText("");
                toastMessage("Compte supprimé");
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

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
