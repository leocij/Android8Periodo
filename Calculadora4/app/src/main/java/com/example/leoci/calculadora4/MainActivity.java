package com.example.leoci.calculadora4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextResultado;
    private boolean novoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResultado = (EditText) findViewById(R.id.editTextResultado);

        editTextResultado.setText("Passei");

        zeroInicial();

        NumberButtonOnClick numberButtonOnClick = new NumberButtonOnClick();

        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(numberButtonOnClick);

    }

    public class NumberButtonOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Button button = (Button) v;

            String digito = button.getText().toString();

            if(digito.equals("C")){
                zeroInicial();
            }else{
                preenche(digito);
            }
        }
    }

    private void zeroInicial() {
        editTextResultado.setText("");
        editTextResultado.setText("0");
        novoNumero = true;
    }

    private void preenche(String digito) {
        String numeroAtual = editTextResultado.getText().toString();

        if(novoNumero){
            novoNumero = false;
            editTextResultado.setText(numeroAtual);
        }else{
            editTextResultado.setText(numeroAtual + digito);
        }
    }
}
