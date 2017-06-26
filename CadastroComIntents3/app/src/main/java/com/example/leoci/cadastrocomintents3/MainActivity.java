package com.example.leoci.cadastrocomintents3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contato> contatos = iniciaContatos();
    private boolean flag = true;

    private List<Contato> iniciaContatos() {
        if(flag){
            flag = false;
            contatos = new ArrayList<>();
            Contato c = new Contato();
            c.setNome("Fulano de Tal");
            c.setFone("2939-2384");
            contatos.add(c);
        }

        return contatos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnListar = (Button) findViewById(R.id.btnListar);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar.class);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,"Passei",Toast.LENGTH_LONG).show();
            }
        });

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(intent);

            }
        });
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
