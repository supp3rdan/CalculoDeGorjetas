package com.danrley.calculogorjeta;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
//import java.text.DecimalFormat;
//import java.math.RoundingMode;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText editValor;
    private TextView textPorcentagem;
    private TextView textGorjeta;
    private TextView textTotal;
    private SeekBar seekBarGorjeta;
    private double porcentagem = 0; // armazenando o valor da seek bar
    //DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculando variaveis
        editValor           = findViewById(R.id.editValor);
        textPorcentagem     = findViewById(R.id.textPorcentagem);
        textGorjeta         = findViewById(R.id.textGorjeta);
        textTotal           = findViewById(R.id.textTotal);
        seekBarGorjeta      = findViewById(R.id.seekBarGorjeta);
        //df.setRoundingMode(RoundingMode.FLOOR);


        // Adicionar listener seekbar
        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textPorcentagem.setText(Math.round(porcentagem) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // Metodo que calcula o valor a ser pago pelo cliente
    public void calcular(){
        String valorRecuperado = editValor.getText().toString();

        // Caso o usuario nao informe o valor
        if( valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            // Convertendo string do valor recuperado para double
            double valorDigitado = Double.parseDouble( valorRecuperado );

            double gorjeta = valorDigitado * ( porcentagem / 100);
            double total = gorjeta + valorDigitado;
//            textGorjeta.setText("R$ " + Math.round(gorjeta));
            textGorjeta.setText("R$ " + Math.round(gorjeta));
            textTotal.setText("R$ " + Math.round(total));


        }
    }
}