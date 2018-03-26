package com.example.yazuke.applimobilemachine_a_sous;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Objects;


public class MaTacheInsynchrone
        extends AsyncTask<Void, Integer, String> {




    private final AsyncListener listener;
    private int id;
    private Rouleau rouleau;
    public boolean cancel;

    public MaTacheInsynchrone(AsyncListener listener,int id) {
        this.listener = listener;
        this.id=id;
        this.rouleau=new Rouleau(id);
    }





    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        while(!cancel) {
            try {
                Thread.sleep(1000L/id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress();
        }
        return "Executed";
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate();

        listener.onProgressUpdate(this.id,this.rouleau.getProchain());



    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        listener.onComplete(this.id);


    }
}