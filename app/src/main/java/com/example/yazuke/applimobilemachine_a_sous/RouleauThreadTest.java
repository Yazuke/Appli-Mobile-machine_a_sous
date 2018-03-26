//package com.example.yazuke.applimobilemachine_a_sous;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//public class RouleauThreadTest extends Activity implements View.OnClickListener {
//
//    Button startTask;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        startTask=(Button)findViewById(R.id.buttonAsync);
//        startTask.setOnClickListener(this);
//
//        TextView txtAsync=(TextView)findViewById(R.id.txtAsync);
//        txtAsync.setText('a');
//    }
//
//
//    public void onClick(View v){
//
//        switch (v.getId()) {
//            case R.id.buttonAsync:
//                new BackgroundTask().execute();
//                break;
//        }
//
//    }
//
//
//    private class BackgroundTask extends AsyncTask<String, Integer, String>{
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params){
//            try {Thread.sleep(500);} catch (InterruptedException e) {Thread.interrupted();}
//            return "Executed";
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values){}
//
//        @Override
//        protected void onPostExecute(String result){
//            super.onPostExecute(result);
//            Toast.makeText(MainActivity.this,"Fin de l'async task",Toast.LENGTH_LONG).show();
//            TextView txt = (TextView) findViewById(R.id.txtAsync);
//            txt.setText("Executed");
//        }
//
//
//
//
//    }
//
//
//
//}
