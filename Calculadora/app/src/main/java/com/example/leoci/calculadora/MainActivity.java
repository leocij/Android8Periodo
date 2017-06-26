package com.example.leoci.calculadora;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    EditText resultado;
    Integer ponto = 0;
    boolean novoNumero = true;
    private Double guardaValor, valorAnterior = Double.NaN;
    private DecimalFormat decimalFormat;
    private String operador = "";
    boolean podeApertarIgual = false;
    String numeroInicial = "", numeroProximo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = (EditText) findViewById(R.id.editText);
        resultado.setText("");
        resultado.setText("0");

        decimalFormat = new DecimalFormat("#.##########");

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonDividir = (Button) findViewById(R.id.buttonDividir);
        Button buttonIgual = (Button) findViewById(R.id.buttonIgual);
        Button buttonMultiplicar = (Button) findViewById(R.id.buttonMultiplicar);
        Button buttonSomar = (Button) findViewById(R.id.buttonSomar);
        Button buttonSubtrair = (Button) findViewById(R.id.buttonSubtrair);
        Button buttonPonto = (Button) findViewById(R.id.buttonPonto);

        digita0(button0);
        digita1(button1);
        digita2(button2);
        digita3(button3);
        digita4(button4);
        digita5(button5);
        digita6(button6);
        digita7(button7);
        digita8(button8);
        digita9(button9);
        digitaC(buttonC);
        digitaDividir(buttonDividir);
        digitaIgual(buttonIgual);
        digitaMultiplicar(buttonMultiplicar);
        digitaSomar(buttonSomar);
        digitaSubtrair(buttonSubtrair);
        digitaPonto(buttonPonto);


    }


    private void digitaSubtrair(Button buttonSubtrair) {

    }


    private void digitaMultiplicar(Button buttonMultiplicar) {

    }

    private void digitaIgual(Button buttonIgual) {
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operador.equals("")) {
                    if (podeApertarIgual) {
                        if (operador.equals("+")) {
                            somar();
                            resultado.setText(decimalFormat.format(valorAnterior));
                        }
                    }
                }
            }
        });
    }

    private void digitaDividir(Button buttonDividir) {

    }

    private void digitaSomar(Button buttonSomar) {
        buttonSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operador = "+";
                novoNumero = true;
            }
        });
    }

    private void somar() {


//        if(!Double.isNaN(valorAnterior)){
//            guardaValor = Double.parseDouble(resultado.getText().toString());
//            valorAnterior =  valorAnterior + guardaValor;
//            //resultado.setText("");
//            ponto = 0;
//        }else{
//            guardaValor = Double.parseDouble(resultado.getText().toString());
//            valorAnterior =  guardaValor;
//            //resultado.setText("");
//            ponto = 0;
//        }
//        novoNumero = 0;
    }

    private void digitaPonto(Button buttonPonto) {
        buttonPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ponto == 0 && !resultado.getText().toString().equals("")) {
                    ponto = 1;
                    preenche(".");
                }
            }
        });
    }

    private void digitaC(Button buttonC) {
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultado.setText("");
                ponto = 0;
                valorAnterior = Double.NaN;
                resultado.setText("0");
            }
        });
    }

    private void digita9(Button button9) {
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("9");
            }
        });
    }

    private void digita8(Button button8) {
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("8");
            }
        });
    }

    private void digita7(Button button7) {
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("7");
            }
        });
    }

    private void digita6(Button button6) {
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("6");
            }
        });
    }

    private void digita5(Button button5) {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("5");
            }
        });
    }

    private void digita4(Button button4) {
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("4");
            }
        });
    }

    private void digita3(Button button3) {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("3");
            }
        });
    }

    private void digita2(Button button2) {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("2");
            }
        });
    }

    private void digita1(Button button1) {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("1");
            }
        });
    }

    private void digita0(Button button0) {
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("0");
            }
        });
    }

    private void preenche(String string) {

        String texto = resultado.getText().toString();
        if (novoNumero) {
            novoNumero = false;
            resultado.setText("");
            if (texto.equals("0") && !string.equals(".")) {
                resultado.setText("");
                numeroProximo = string;
                resultado.setText(numeroProximo);
            } else {
                resultado.setText(numeroProximo);
            }
        } else {
            if (texto.equals("0") && !string.equals(".")) {
                resultado.setText("");
                numeroProximo = string;
                resultado.setText(numeroProximo);
            } else {
                resultado.setText(texto + string);
            }
        }
    }

}
