package com.example.leoci.meuprimeirobancov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carregaTelaPrincipal();
    }

    private void carregaTelaPrincipal() {
        setContentView(R.layout.activity_main);

        final Button btnMainNovo = (Button) findViewById(R.id.btnMainNovo);
        final Button btnMainListagem = (Button) findViewById(R.id.btnCadSalvar);

        btnMainNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaCadastro();
            }
        });

        btnMainListagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaListagem();
            }
        });
    }

    protected void carregaTelaListagem() {
        setContentView(R.layout.activity_listagem);

        final Button btnListVoltar = (Button) findViewById(R.id.btnListVoltar);

        btnListVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaPrincipal();
            }
        });
    }

    protected void carregaTelaCadastro() {
        setContentView(R.layout.activity_cadastro);

        final Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        final Button btnSalvar = (Button) findViewById(R.id.btnCadSalvar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaPrincipal();
            }
        });

    }
}
