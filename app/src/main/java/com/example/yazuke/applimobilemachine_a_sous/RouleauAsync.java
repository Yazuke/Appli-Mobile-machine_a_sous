package com.example.yazuke.applimobilemachine_a_sous;

import android.os.AsyncTask;
import android.util.Log;

public class RouleauAsync
        extends AsyncTask<Void, Integer, String> {

    private final AsyncListener listener;
    private int id;
    public Rouleau rouleau;
    public boolean cancel;
    public static long temps=500L;

    //Crée l'Async task sans la démarrer.On lui attribue un rouleau
    public RouleauAsync(AsyncListener listener, int id, int roll) {
        this.listener = listener;
        this.id=id;
        this.rouleau=new Rouleau(id,roll);
        cancel=false;
    }

    public String[] getSequenceAffichee(){
        return this.rouleau.getSequenceAffichee();
    }



    //Avant l'execution (ne se passe rien)
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Pendant l'execution, l'AsyncTask appelle publishProgress() régulièrement. L'appel peut être récupéré dans l'UI
    @Override
    protected String doInBackground(Void... params) {
        while(!cancel){
            try {
                Thread.sleep(temps/id);
                this.rouleau.roll();
                publishProgress();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        return "Executed";
    }

    //Envoie l'id du rouleau et le prochain symbole à l'UI
    //L'UI va le récupérer, afficher le prochain symbole et faire tourner le rouleau
    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate();
        listener.onProgressUpdate(this.id,this.rouleau.getProchain());
    }

    //A la fin de l'execution
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listener.onComplete(this.id,this.rouleau.getProchain());
    }
}