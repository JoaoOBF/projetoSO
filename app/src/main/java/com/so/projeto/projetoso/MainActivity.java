package com.so.projeto.projetoso;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Management management;
    long decolagemTime;
    long pousoTime;
    ArrayList<Plane> pousosArray;
    ArrayList<Plane> decolagensArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        management = new Management();
        decolagemTime = management.decolagemTime;
        pousoTime = management.pousoTime;
        pousosArray = management.pousosArray;
        decolagensArray = management.decolagensArray;
    }



    Boolean pistaOcupada = false;

    void processaPista(){
        if (!pistaOcupada) {

            if (pousosArray.get(0) != null){
                pousosArray.remove(0);
                pistaOcupada = true;
//                startTimer[pousoTime];
            }
            if (decolagensArray.get(0) != null){
                decolagensArray.remove(0);
                pistaOcupada = true;
//                startTimer[decolagemTime];
            }
        }
    }

//    final Handler handler = new Handler();
//    handler.postDelayed(new Runnable() {
//        @Override
//        public void run() {
//            //Do something after 100ms
//            Toast.makeText(c, "check", Toast.LENGTH_SHORT).show();
//            handler.postDelayed(this, 2000);
//        }
//    }, 1500);

}

