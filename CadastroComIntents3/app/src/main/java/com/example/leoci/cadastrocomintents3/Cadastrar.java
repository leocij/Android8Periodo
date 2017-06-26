package com.example.leoci.cadastrocomintents3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 27/02/2017.
 */
public class Cadastrar extends Activity{

    private List<Contato> contatos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final MainActivity mainActivity = new MainActivity();
        final List<Contato> contatos = mainActivity.getContatos();

        final Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etNome = (EditText) findViewById(R.id.etNome);
                final EditText etFone = (EditText) findViewById(R.id.etFone);

                Contato contatoNovo = new Contato();
                contatoNovo.setNome(etNome.getText().toString());
                contatoNovo.setFone(etFone.getText().toString());
                contatos.add(contatoNovo);
                Cadastrar.this.setContatos(contatos);
                finish();
            }
        });

        final Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
