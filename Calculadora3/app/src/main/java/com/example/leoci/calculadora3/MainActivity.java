package com.example.leoci.calculadora3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText resultado;
    EditText editTextConta;
    private boolean novoNumero;
    private boolean ponto;
    private String guardaValor1 = "";
    private String guardaValor2 = "";
    private boolean maisOperacoes;
    private DecimalFormat decimalFormat;
    private boolean podeApertarIgual;
    private String conta = "";
    private String operadorAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        editTextConta = (EditText) findViewById(R.id.editTextConta);
        resultado = (EditText) findViewById(R.id.editText);
        zeroInicial();

        Button buttonC = (Button) findViewById(R.id.buttonC);
        digitaC(buttonC);

        Button button0 = (Button) findViewById(R.id.button0);
        digita0(button0);

        Button button1 = (Button) findViewById(R.id.button1);
        digita1(button1);

        Button button2 = (Button) findViewById(R.id.button2);
        digita2(button2);

        Button button3 = (Button) findViewById(R.id.button3);
        digita3(button3);

        Button button4 = (Button) findViewById(R.id.button4);
        digita4(button4);

        Button button5 = (Button) findViewById(R.id.button5);
        digita5(button5);

        Button button6 = (Button) findViewById(R.id.button6);
        digita6(button6);

        Button button7 = (Button) findViewById(R.id.button7);
        digita7(button7);

        Button button8 = (Button) findViewById(R.id.button8);
        digita8(button8);

        Button button9 = (Button) findViewById(R.id.button9);
        digita9(button9);

        Button buttonPonto = (Button) findViewById(R.id.buttonPonto);
        digitaPonto(buttonPonto);

        Button buttonIgual = (Button) findViewById(R.id.buttonIgual);
        digitaIgual(buttonIgual);

        Button buttonSomar = (Button) findViewById(R.id.buttonSomar);
        digitaSomar(buttonSomar);

        Button buttonSubtrair = (Button) findViewById(R.id.buttonSubtrair);
        digitaSubtrair(buttonSubtrair);

        Button buttonMultiplicar = (Button) findViewById(R.id.buttonMultiplicar);
        digitaMultiplicar(buttonMultiplicar);

        Button buttonDividir = (Button) findViewById(R.id.buttonDividir);
        digitaDividir(buttonDividir);
    }

    private void zeroInicial() {
        resultado.setText("");
        resultado.setText("0");
        novoNumero = true;
        ponto = true;
        guardaValor1 = "0";
        guardaValor2 = "0";
        operadorAnterior = "";
        maisOperacoes = true;
        podeApertarIgual = false;
        editTextConta.setText("");
        conta = "";

    }

    private void digitaC(Button buttonC) {
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zeroInicial();
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

    private void digita1(Button button1) {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("1");
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

    private void digita3(Button button3) {
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("3");
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

    private void digita5(Button button5) {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("5");
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

    private void digita7(Button button7) {
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("7");
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

    private void digita9(Button button9) {
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preenche("9");
            }
        });
    }

    private void digitaPonto(Button buttonPonto) {
        buttonPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ponto && !resultado.getText().toString().equals("")) {
                    ponto = false;
                    preenche(".");
                }
            }
        });
    }

    private void preenche(String digito) {
        String numeroAtual = resultado.getText().toString();

        if (novoNumero) {
            novoNumero = false;
            resultado.setText("");
            if (numeroAtual.equals("0") && !digito.equals(".")) {
                resultado.setText(digito);
                conta = conta + digito + " ";
                editTextConta.setText(conta);
                //editTextConta.setText("numeroAtual = " + numeroAtual + " digito = " + digito);
            } else {
                if (digito.equals(".")) {
                    if(resultado.getText().equals("0")){
                        resultado.setText(numeroAtual + digito);
                        conta = numeroAtual + digito;
                        editTextConta.setText(conta);
                    }else{
                        resultado.setText("0" + digito);
                        conta = "0" + digito;
                        editTextConta.setText(conta);
                    }

                } else {
                    resultado.setText(digito);
                    conta = conta + " ";
                    editTextConta.setText(conta);
                    //editTextConta.setText("numeroAtual = " + numeroAtual + " digito = " + digito);
                }
            }
        } else {
            if (numeroAtual.equals("0") && !digito.equals(".")) {
                resultado.setText("");
                resultado.setText(digito);
            } else {
                resultado.setText(numeroAtual + digito);
            }

            conta = conta + digito + " ";
            editTextConta.setText(conta);
            //editTextConta.setText("numeroAtual = " + numeroAtual + " digito = " + digito);
        }



        guardaValor1 = resultado.getText().toString();
        podeApertarIgual = true;
    }

    private void digitaSomar(Button buttonSomar) {
        buttonSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao("+");
            }
        });
    }

    private void digitaSubtrair(Button buttonSubtrair) {
        buttonSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao("-");
            }
        });
    }

    private void digitaMultiplicar(Button buttonMultiplicar) {
        buttonMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao("*");
            }
        });
    }

    private void digitaDividir(Button buttonDividir) {
        buttonDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacao("/");
            }
        });
    }

    private void operacao(String op) {

        //editTextConta.setText("valor1 = " + guardaValor1 + " valor2 = " + guardaValor2 + " resultado = " + resultado.getText());
        if (guardaValor1.equals("0") && !resultado.getText().equals("0")) {
            guardaValor1 = "";
            guardaValor1 = guardaValor2;
            conta = guardaValor1 + " ";
            //editTextConta.setText("valor1 = " + guardaValor1);
        }

        //editTextConta.setText("valor1 = " + guardaValor1);
        conta = conta + op + " ";
        editTextConta.setText(conta);

        if (maisOperacoes) {
            maisOperacoes = false;
            guardaValor2 = guardaValor1;
            guardaValor1 = "0";
            novoNumero = true;
            ponto = true;
            operadorAnterior = op;
        } else {
            if (!guardaValor1.equals("0")) {
                if (operadorAnterior.equals("+")) {
                    guardaValor2 = String.valueOf(Double.parseDouble(guardaValor2) + Double.parseDouble(guardaValor1));
                } else if (operadorAnterior.equals("-")) {
                    guardaValor2 = String.valueOf(Double.parseDouble(guardaValor2) - Double.parseDouble(guardaValor1));
                } else if (operadorAnterior.equals("*")) {
                    guardaValor2 = String.valueOf(Double.parseDouble(guardaValor2) * Double.parseDouble(guardaValor1));
                } else if (operadorAnterior.equals("/")) {
                    guardaValor2 = String.valueOf(Double.parseDouble(guardaValor2) / Double.parseDouble(guardaValor1));
                }

                guardaValor1 = "0";
                novoNumero = true;
                ponto = true;
                operadorAnterior = op;
            }
        }
    }

    private void digitaIgual(Button buttonIgual) {
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operadorAnterior.equals("")) {
                    if (!guardaValor1.equals("0")) {
                        if (podeApertarIgual) {
                            operacao("=");
                            if (operadorAnterior.equals("=")) {
                                resultado.setText(String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2))));
                            } else {
                                if (!guardaValor2.equals("0")) {
                                    resultado.setText(String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2))));
                                    guardaValor2 = "0";
                                    podeApertarIgual = false;
                                }
                            }
                        }
                    } else {
                        if (!guardaValor2.equals("0")) {
                            resultado.setText(String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2))));
                        }
                    }
                }

                operadorAnterior = "";
                maisOperacoes = true;
                editTextConta.setText("");
                conta = "";
            }
        });
    }
}
