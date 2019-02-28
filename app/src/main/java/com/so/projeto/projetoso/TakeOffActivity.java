package com.so.projeto.projetoso;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TakeOffActivity extends AppCompatActivity {

    Management management;
    long decolagemTime;
    long pousoTime;
    ArrayList<Plane> pousosArray;
    ArrayList<Plane> decolagensArray;
    Boolean pistaOcupada = false;
    TextView txt_nome_pouso;
    TextView txt_id_pouso;
    ProgressBar progressBarDecolar;
    ProgressBar progressBarPouso;
    TextView txt_id_decolar;
    TextView txt_nome_decolar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_off_landing_process);
        management = new Management();
        decolagemTime = management.decolagemTime;
        pousoTime = management.pousoTime;
        txt_nome_pouso = (TextView) findViewById(R.id.txt_nome_pouso);
        progressBarDecolar = (ProgressBar) findViewById(R.id.progressBar2);
        txt_nome_decolar = (TextView) findViewById(R.id.txt_nome_decola);
        progressBarPouso = (ProgressBar) findViewById(R.id.progressBar1);
        txt_id_decolar = (TextView) findViewById(R.id.txt_id_decolar);
        txt_id_pouso = (TextView) findViewById(R.id.txt_id_pouso);

        criarDecolarAviao();
        criarPousarAviao();
        processaPista();

    }


    void processaPista() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                if (!pistaOcupada) {

                    if (pousosArray.get(0) != null) {
                        pousosArray.remove(0);
                        pistaOcupada = true;
//                startTimer[pousoTime];
                    }
                    if (decolagensArray.get(0) != null) {
                        decolagensArray.remove(0);
                        pistaOcupada = true;
//                startTimer[decolagemTime];
                    }
                }
                handler.postDelayed(this, 2000);
            }
        }, 1500);

    }

    void criarDecolarAviao() {
        decolagensArray = management.decolagensArray;
        for (int i = 0; i < 6; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 17 + i);
            cal.set(Calendar.MINUTE, 30);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date d = cal.getTime();
            decolagensArray.add(new Plane("Avião" + i, "D", d));
        }

    }

    void criarPousarAviao() {
        pousosArray = management.pousosArray;
        for (int i = 0; i < 3; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 17 + i);
            cal.set(Calendar.MINUTE, 30);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date d = cal.getTime();
            pousosArray.add(new Plane("Avião" + i, "D", d));
        }

    }
}
