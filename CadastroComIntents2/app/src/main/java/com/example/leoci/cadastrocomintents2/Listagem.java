package com.example.leoci.cadastrocomintents2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 27/02/2017.
 */
public class Listagem extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem);

        MainActivity mainActivity = new MainActivity();
        //Intent i = getIntent();
        ArrayList<Contato> contatos = (ArrayList<Contato>) mainActivity.getContatos();

        final List<Contato> contatosString = new ArrayList<>();
        for(Contato contato: contatos){
            contatosString.add(contato);
        }

        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(
                getApplicationContext(), android.R.layout.simple_list_item_1
        );

        adapter.clear();

        final ListView lvContatos = (ListView) findViewById(R.id.listView1);
        lvContatos.setAdapter(adapter);
        adapter.addAll(contatosString);

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                String contato = (String) arg0.getItemAtPosition(arg2);
                showMessage("Contato selecionado", "Selecionado: " + contato);
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

    private void showMessage(String Caption, String Title) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Listagem.this);
        dialogo.setTitle(Title);
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }
}
