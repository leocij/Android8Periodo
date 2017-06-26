package com.example.leoci.calculadora5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextResultado, editTextConta;
    NumeroClicado numeroClicado;
    private String string;
    private boolean fazCalculo;
    private String operador;
    private List<String> numeros;
    private double resultado;
    private DecimalFormat decimalFormat;
    private boolean naoDivide0;
    private boolean colocaPonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        editTextResultado = (EditText) findViewById(R.id.editTextResultado);
        editTextConta = (EditText) findViewById(R.id.editTextConta);

        zeroInicial();

        registra();
    }

    private void zeroInicial() {
        editTextResultado.setText("0");
        editTextConta.setText("");
        string = "";
        fazCalculo = false;
        numeros = new ArrayList<>();
        operador = "";
        naoDivide0 = false;
        colocaPonto = false;
    }

    private class NumeroClicado implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String digito = button.getText().toString();

            if (digito.equals("C")) {
                zeroInicial();
            } else if (digito.equals("+") || digito.equals("-") || digito.equals("*") || digito.equals("/")) {
                fazCalculo = true;
                string = string + digito;
                editTextResultado.setText(string);
                colocaPonto = true;
            } else if (digito.equals("=")) {
                editTextConta.setText("");
                editTextResultado.setText(String.valueOf(decimalFormat.format(resultado)));
                fazCalculo = false;
            } else {
                preenche(digito);
            }
        }
    }

    private void preenche(String digito) {

        string = editTextResultado.getText().toString().replace(",",".");

        if (digito.equals("0")) {
            if (!string.equals("0")) {
                string = string + digito;
            }

            if (fazCalculo) {
                calcular();
            }
        } else if (digito.equals(".")) {
            if (!string.contains(".") || colocaPonto) {
                if (string.charAt(string.length() - 1) == '+' || string.charAt(string.length() - 1) == '-' || string.charAt(string.length() - 1) == '*' || string.charAt(string.length() - 1) == '/') {
                    string = string + "0" + digito;
                } else {
                    string = string + digito;
                }
                colocaPonto = false;
            }
        } else {
            if (string.equals("0")) {
                string = digito;
            } else {
                string = string + digito;
            }

            if (fazCalculo) {
                calcular();
            }
        }



        editTextResultado.setText(string);

    }

    private void calcular() {
        editTextResultado.setText(string);

        int k = 0;

        StringBuilder auxiliar = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '+' || string.charAt(i) == '-' || string.charAt(i) == '*' || string.charAt(i) == '/') {
                operador = operador + string.charAt(i);
                k++;
                numeros.add(auxiliar.toString());
                auxiliar = new StringBuilder();
            } else {
                auxiliar.append(string.charAt(i));
            }
        }

        numeros.add(auxiliar.toString());

        resultado = Double.parseDouble(numeros.get(0).toString());
        for (int i = 0; i < k; i++) {
            if (operador.charAt(i) == '+') {
                resultado = resultado + Double.parseDouble(numeros.get(i + 1).toString());
            } else if (operador.charAt(i) == '-') {
                resultado = resultado - Double.parseDouble(numeros.get(i + 1).toString());
            } else if (operador.charAt(i) == '*') {
                resultado = resultado * Double.parseDouble(numeros.get(i + 1).toString());
            } else if (operador.charAt(i) == '/') {
                if (numeros.get(i + 1).equals("0")) {
                    naoDivide0 = true;
                } else {
                    resultado = resultado / Double.parseDouble(numeros.get(i + 1).toString());
                }
            }

        }

        numeros = new ArrayList<>();
        operador = "";

        if (naoDivide0) {
            naoDivide0 = false;
            editTextConta.setText("Não quero dividir por 0. Obrigado!");
        } else {
            editTextConta.setText(String.valueOf(decimalFormat.format(resultado)));
        }
    }

    private void registra() {

        numeroClicado = new NumeroClicado();

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(numeroClicado);

        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(numeroClicado);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(numeroClicado);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(numeroClicado);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(numeroClicado);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(numeroClicado);

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(numeroClicado);

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(numeroClicado);

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(numeroClicado);

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(numeroClicado);

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(numeroClicado);

        Button buttonSomar = (Button) findViewById(R.id.buttonSomar);
        buttonSomar.setOnClickListener(numeroClicado);

        Button buttonSubtrair = (Button) findViewById(R.id.buttonSubtrair);
        buttonSubtrair.setOnClickListener(numeroClicado);

        Button buttonMultiplicar = (Button) findViewById(R.id.buttonMultiplicar);
        buttonMultiplicar.setOnClickListener(numeroClicado);

        Button buttonDividir = (Button) findViewById(R.id.buttonDividir);
        buttonDividir.setOnClickListener(numeroClicado);

        Button buttonPonto = (Button) findViewById(R.id.buttonPonto);
        buttonPonto.setOnClickListener(numeroClicado);

        Button buttonIgual = (Button) findViewById(R.id.buttonIgual);
        buttonIgual.setOnClickListener(numeroClicado);
    }
}
