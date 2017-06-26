package com.example.leoci.mysharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final EditText editText1 = (EditText) findViewById(R.id.editText1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                Editor editor = sharedPreferences.edit();
                editor.putString("nome_usuario", editText1.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(), "Salvou nome: " + editText1.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                String nome = sharedPreferences.getString("nome_usuario", null);
                Toast.makeText(getApplicationContext(), "Nome lido: " + nome, Toast.LENGTH_LONG).show();
            }
        });
    }
}
