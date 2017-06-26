package com.example.leoci.cadastrocomintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contato> contatos = iniciaContatos();

    private List<Contato> iniciaContatos() {
        contatos = new ArrayList<>();
        Contato c = new Contato();
        c.setNome("Fulano de Tal");
        c.setFone("2939-2384");
        contatos.add(c);
        return contatos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnCadastrar = (Button) findViewById(R.id.button2);
        final Button btnListar = (Button) findViewById(R.id.button1);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                //intent.putExtra("contatos", (Serializable) contatos);
                startActivity(intent);
                Cadastrar cadastrar = new Cadastrar();
                contatos = cadastrar.getContatos();
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listagem.class);
                //intent.putExtra("contatos", (Serializable) contatos);
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
