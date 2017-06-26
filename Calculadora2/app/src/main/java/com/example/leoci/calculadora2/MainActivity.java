package com.example.leoci.calculadora2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView textViewGuardaValor1, textViewGuardaValor2, textViewOperador;
    EditText resultado;
    EditText editTextConta;
    private boolean novoNumero;
    private boolean ponto;
    private String guardaValor1 = "";
    private String guardaValor2 = "";
    private String operador = "";
    private boolean maisOperacoes;
    private DecimalFormat decimalFormat;
    private boolean podeApertarIgual;
    private String conta = "";
    private String operadorAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewGuardaValor1 = (TextView) findViewById(R.id.textViewGuardaValor1);
        textViewGuardaValor2 = (TextView) findViewById(R.id.textViewGuardaValor2);
        textViewOperador = (TextView) findViewById(R.id.textViewOperador);

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
        operador = "";
        guardaValor1 = "0";
        guardaValor2 = "0";
        textViewGuardaValor1.setText("GuardaValor1-1: " + guardaValor1);
        textViewGuardaValor2.setText("GuardaValor2-1: " + guardaValor2);
        textViewOperador.setText("Operador: " + operador);
        maisOperacoes = true;
        podeApertarIgual = false;
        editTextConta.setText("");
        operadorAnterior = "";
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

    private void digitaIgual(Button buttonIgual) {
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operador.equals("")) {
                    if (!guardaValor1.equals("0")) {
                        if (podeApertarIgual) {
                            operadorAnterior = "";
                            if (operador.equals("+")) {
                                operacao("+");
                            } else if (operador.equals("-")) {
                                operacao("-");
                                resultado.setText(guardaValor2);
                            } else if (operador.equals("*")) {
                                operacao("*");
                            } else if (operador.equals("/")) {
                                if(guardaValor1.equals("0")){
                                    resultado.setText("Eu divido " + guardaValor2 + " por " + guardaValor1 + ". Mas só que não!");
                                }else {
                                    operacao("/");
                                }
                            }
                            if (!guardaValor2.equals("0")) {
                                resultado.setText(guardaValor2);
                                guardaValor2 = "0";
                                textViewGuardaValor2.setText("GuardaValor2-4: " + guardaValor2);
                                podeApertarIgual = false;
                            }
                        }
                    } else {
                        if (!guardaValor2.equals("0")) {
                            resultado.setText(guardaValor2);
                        }
                    }
                }

                operador = "";
                textViewOperador.setText("OperadorIgual: " + operador);
                maisOperacoes = true;
                editTextConta.setText("");
                conta = "";
            }
        });
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

        conta = conta + op + " ";
        editTextConta.setText(conta);

        if (guardaValor1.equals("0") && guardaValor2.equals("0") && !resultado.equals("0")) {
            guardaValor1 = resultado.getText().toString();
        }
        if (maisOperacoes) {
            maisOperacoes = false;
            guardaValor2 = guardaValor1;
            textViewGuardaValor2.setText("GuardaValor2-2: " + guardaValor2);
            guardaValor1 = "0";
            textViewGuardaValor1.setText("GuardaValor1-3: " + guardaValor1);
            novoNumero = true;
            operador = op;
            textViewOperador.setText("Operador1: " + operador);
            operadorAnterior = op;
        } else {
            if(operadorAnterior.equals("")){
                String aux = op;
            }
            if (!guardaValor1.equals("0")) {
                if (op.equals("+")) {
                    guardaValor2 = String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2) + Double.parseDouble(guardaValor1)));
                } else if (op.equals("-")) {
                    guardaValor2 = String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2) - Double.parseDouble(guardaValor1)));
                } else if (op.equals("*")) {
                    guardaValor2 = String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2) * Double.parseDouble(guardaValor1)));
                } else if (op.equals("/")) {
                    guardaValor2 = String.valueOf(decimalFormat.format(Double.parseDouble(guardaValor2) / Double.parseDouble(guardaValor1)));
                }
                textViewGuardaValor2.setText("GuardaValor2-3: " + guardaValor2);
                guardaValor1 = "0";
                textViewGuardaValor1.setText("GuardaValor1-3: " + guardaValor1);
                novoNumero = true;
                operador = op;
                textViewOperador.setText("Operador2: " + operador);
            }
        }
    }

    private void preenche(String digito) {
        String numeroAtual = resultado.getText().toString();

        conta = conta + digito  + " ";
        editTextConta.setText(conta);

        if (novoNumero) {
            novoNumero = false;
            resultado.setText("");
            if (numeroAtual.equals("0") && !digito.equals(".")) {
                resultado.setText(digito);
            } else {
                if (digito.equals(".")) {
                    resultado.setText(numeroAtual + digito);
                } else {
                    resultado.setText(digito);
                }
            }
        } else {
            if (numeroAtual.equals("0") && !digito.equals(".")) {
                resultado.setText("");
                resultado.setText(digito);
            } else {
                resultado.setText(numeroAtual + digito);
            }
        }
        guardaValor1 = resultado.getText().toString();
        podeApertarIgual = true;
        textViewGuardaValor1.setText("GuardaValor1-2: " + guardaValor1);
    }
}
