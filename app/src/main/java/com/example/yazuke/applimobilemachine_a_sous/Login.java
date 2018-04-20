package com.example.yazuke.applimobilemachine_a_sous;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;



public class Login extends AppCompatActivity {
    DatabaseHandler mDatabaseHandler;
    private TextView btnIns;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mDatabaseHandler = new DatabaseHandler(this);
        mListView=(ListView) findViewById(R.id.listView);
        btnIns = (TextView) findViewById(R.id.textView2);
        
        populateListView();
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Inscription.class);
                startActivity(intent);
            }
        });
    }


    private void populateListView() {
        Log.d(TAG,"populateListView: Affichage des données dans la ListView");
        Cursor data= mDatabaseHandler.selectionner();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        final ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name =adapterView.getItemAtPosition(i).toString();
                Log.d(TAG,"onItemClick: Vous avez cliiqué sur "+name);
                Cursor data=mDatabaseHandler.getItemID(name);
                int itemID=-1;
                while(data.moveToNext()){
                    itemID=data.getInt(0);
                }
                if(itemID>-1){
                    Log.d(TAG,"onItemClick: L'ID est "+itemID);
                    Intent editIntent = new Intent(Login.this,EditDataActivity.class);
                    editIntent.putExtra("id",itemID);
                    editIntent.putExtra("name",name);
                    startActivity(editIntent);
                }
                else{
                    toastMessage("Aucun compte associé avec ce nom");
                }
            }
        });
    }


    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}