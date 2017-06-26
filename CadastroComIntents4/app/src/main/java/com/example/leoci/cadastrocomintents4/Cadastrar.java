package com.example.leoci.cadastrocomintents4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by leoci on 01/03/2017.
 */
public class Cadastrar extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final ArrayList<Contato> cadastros = (ArrayList<Contato>) getIntent().getSerializableExtra("contatos");


        final Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etNome = (EditText) findViewById(R.id.etNome);
                final EditText etFone = (EditText) findViewById(R.id.etFone);

                Contato contatoNovo = new Contato();
                contatoNovo.setNome(etNome.getText().toString());
                contatoNovo.setFone(etFone.getText().toString());
                cadastros.add(contatoNovo);
                MainActivity m = new MainActivity();
                m.setContatos(cadastros);
//                Intent intent = new Intent(Cadastrar.this,MainActivity.class);
//                intent.putExtra("contatos", cadastros);
                finish();
            }
        });

    }
}
