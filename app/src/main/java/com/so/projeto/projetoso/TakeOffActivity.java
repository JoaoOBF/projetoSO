package com.so.projeto.projetoso;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.LongToIntFunction;

public class TakeOffActivity extends AppCompatActivity {

    Management management;
    long decolagemTime;
    long pousoTime;
    long ocupadaTime;
    int qtdPousos = 0;
    int qtdDecolagens = 0;
    boolean hasTakenOff;
    ArrayList<Plane> pousosArray;
    ArrayList<Plane> decolagensArray;
    Boolean pistaOcupada = false;
    TextView txt_nome_pouso;
    TextView txt_id_pouso;
    ProgressBar progressBarDecolar;
    ProgressBar progressBarPouso;
    TextView txt_id_decolar;
    TextView txt_nome_decolar;
    int numberOfPlanesToTakeOff;
    int numberOfPlanesToLand;
    CountDownTimer Count;
    Runnable mPendingRunnable;
    final Handler processaPistaHandler = new Handler();


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
        numberOfPlanesToTakeOff = getIntent().getIntExtra("decola", 0);
        numberOfPlanesToLand = getIntent().getIntExtra("pousar", 0);
        txt_id_decolar = (TextView) findViewById(R.id.txt_id_decolar);
        txt_id_decolar.setText(String.valueOf(numberOfPlanesToTakeOff));
        txt_id_pouso = (TextView) findViewById(R.id.txt_id_pouso);
        txt_id_pouso.setText(String.valueOf(numberOfPlanesToLand));

        criarDecolarAviao();
        criarPousarAviao();

        if (decolagensArray.isEmpty()){
            hasTakenOff = false;
        } else{
            hasTakenOff = true;
        }

        mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                if (!pistaOcupada) {

                    if (pousosArray.isEmpty() && decolagensArray.isEmpty()) {
                        Log.i("finalizado", "");
                        processaPistaHandler.removeCallbacks(mPendingRunnable);
                        txt_id_pouso.setText("0 - FINALIZOU");
                        txt_id_decolar.setText("0 - FINALIZOU");

                    } else {
                        Log.i("naofinalizado", hasTakenOff + "decolagem->" + String.valueOf(decolagensArray.size()) + "pouso->" + String.valueOf(pousosArray.size()));
                        if (!pousosArray.isEmpty() && pousosArray.get(0) != null && !hasTakenOff) {
                            Log.i("Entrou no:" + hasTakenOff, "pouso");
                            pistaOcupada = true;
                            ocupadaTime = pousoTime;

                            processaPista();

                        }
                        if (!decolagensArray.isEmpty() && decolagensArray.get(0) != null && hasTakenOff) {
                            Log.i("Entrou no:" + hasTakenOff, "decolagem");
                            pistaOcupada = true;
                            ocupadaTime = decolagemTime;

                            processaPista();

                        }
                    }


                }
                processaPistaHandler.postDelayed(this, 1000);
            }
        };

        processaPistaHandler.post(mPendingRunnable);

    }


    void processaPista() {

        // Countdown

        Count = new CountDownTimer(ocupadaTime, 1000) {

            public void onTick(long millisUntilFinished) {

                Integer intMillis = (int) (long) millisUntilFinished;
                Integer intOcupada = (int) (long) ocupadaTime;

                if (!hasTakenOff) {
                    progressBarPouso.setProgress((intOcupada - intMillis) * 100 / intOcupada);
                } else {
                    progressBarDecolar.setProgress((intOcupada - intMillis) * 100 / intOcupada);
                }
            }

            public void onFinish() {
                pistaOcupada = false;

                if (hasTakenOff && !decolagensArray.isEmpty()) {
                    Log.i("decolagem:", String.valueOf(decolagensArray.size()));
                    if (!pousosArray.isEmpty()) {
                        hasTakenOff = false;
                    }

                    decolagensArray.remove(0);
                    txt_id_decolar.setText(String.valueOf(decolagensArray.size()));
                } else if (!hasTakenOff && !pousosArray.isEmpty()) {
                    Log.i("pouso:", String.valueOf(pousosArray.size()));
                    if (!decolagensArray.isEmpty()) {
                        hasTakenOff = true;
                    }

                    pousosArray.remove(0);
                    txt_id_pouso.setText(String.valueOf(pousosArray.size()));
                }

                progressBarPouso.setProgress(0);
                progressBarDecolar.setProgress(0);
            }

        }.start();

    }


    void criarDecolarAviao() {
        decolagensArray = management.decolagensArray;
        for (int i = 0; i < numberOfPlanesToTakeOff; i++) {
            decolagensArray.add(new Plane("Avião" + i, "D"));
        }

    }

    void criarPousarAviao() {
        pousosArray = management.pousosArray;
        for (int i = 0; i < numberOfPlanesToLand; i++) {
            pousosArray.add(new Plane("Avião" + i, "P"));
        }

    }
}
