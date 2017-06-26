package com.example.leoci.cadastrocomintents3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by leoci on 27/02/2017.
 */
public class Listar extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);

        ArrayList<Contato> contatos;

        Cadastrar cadastrar = new Cadastrar();
        if(cadastrar.getContatos().size()>0){
            contatos = (ArrayList<Contato>) cadastrar.getContatos();
        } else {
            MainActivity mainActivity = new MainActivity();
            contatos = (ArrayList<Contato>) mainActivity.getContatos();
        }


        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(
          getApplicationContext(),android.R.layout.simple_list_item_1
        );

        adapter.clear();

        final ListView lvContatos = (ListView) findViewById(R.id.listView1);
        lvContatos.setAdapter(adapter);
        adapter.addAll(contatos);

        adapter.notifyDataSetChanged();

        final Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
