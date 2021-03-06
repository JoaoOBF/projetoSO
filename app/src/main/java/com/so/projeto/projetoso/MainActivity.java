package com.so.projeto.projetoso;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText decolar;
    EditText pousar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_enviar);
        decolar = (EditText) findViewById(R.id.edt_aviao_solo);
        pousar = (EditText) findViewById(R.id.edt_aviao_ar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valueDecola = Integer.parseInt(decolar.getText().toString());
                int valuePousar = Integer.parseInt(pousar.getText().toString());
                if (valueDecola <= 6 && valuePousar <= 3) {

                    Intent i = new Intent(getApplicationContext(), TakeOffActivity.class);
                    i.putExtra("decola", valueDecola);
                    i.putExtra("pousar", valuePousar);
                    startActivity(i);
                } else {
                        //Colocar dialog informando o erro
                    // Toast toast = new Toast(getApplicationContext(),"Por favor, digitar menos que 6 voos no solo e 3 voos no ar!",Toast.LENGTH_LONG);
                    Context context = getApplicationContext();
                    CharSequence text = "Por favor, digitar até 6 voos no solo e até 3 voos no ar!!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
//                    toast.show();
                }

            }
        });

    }


}

