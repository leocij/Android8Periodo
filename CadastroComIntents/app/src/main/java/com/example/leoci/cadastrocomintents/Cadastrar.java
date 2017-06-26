package com.example.leoci.cadastrocomintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by leoci on 27/02/2017.
 */
public class Cadastrar extends Activity{

    private ArrayList<Contato> contatos;

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final MainActivity mainActivity = new MainActivity();
        contatos = (ArrayList<Contato>) mainActivity.getContatos();

        final Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        final Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtNome = (EditText) findViewById(R.id.editText1);
                final EditText txtFone = (EditText) findViewById(R.id.editText2);

                Contato contatoNovo = new Contato();
                contatoNovo.setNome(txtNome.getText().toString());
                contatoNovo.setFone(txtFone.getText().toString());
                contatos.add(contatoNovo);
                //Toast.makeText(mainActivity,contatoNovo.toString(),Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
