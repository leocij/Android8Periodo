package com.example.leoci.somanumeros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText numero1, numero2, resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = (EditText)findViewById(R.id.editTextNumero1);
        numero2 = (EditText)findViewById(R.id.editTextNumero2);
        resultado = (EditText)findViewById(R.id.editTextResultado);

        Button buttonSomar = (Button)findViewById(R.id.buttonSomar);
        Button buttonSubtrair = (Button)findViewById(R.id.buttonSubtrair);

        buttonSomar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                double num1 = Double.parseDouble(numero1.getText().toString());
                double num2 = Double.parseDouble(numero2.getText().toString());

                double res = num1+num2;

                resultado.setText(String.valueOf(res));
            }
        });

        buttonSubtrair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(numero1.getText().toString());
                double num2 = Double.parseDouble(numero2.getText().toString());

                double res = num1-num2;

                resultado.setText(String.valueOf(res));
            }
        });
    }
}
