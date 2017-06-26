package com.example.leoci.cadastrocomintents4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contato> contatos = new ArrayList<Contato>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCriar = (Button) findViewById(R.id.btnCriar);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatos = iniciaContatos();
            }

            private ArrayList<Contato> iniciaContatos() {
                Contato c = new Contato();
                c.setNome("Fulano de Tal");
                c.setFone("2939-2384");
                contatos.add(c);

                return contatos;
            }
        });

        Button btnListar = (Button) findViewById(R.id.btnListar);

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar.class);
                intent.putExtra("contatos", contatos);
                startActivity(intent);
            }
        });

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                intent.putExtra("contatos", contatos);
                startActivity(intent);
//                contatos = (ArrayList<Contato>) getIntent().getSerializableExtra("contatos");
//                setContatos(contatos);
            }
        });
        
    }


    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }
}
