package com.example.leoci.cadastrocomintents4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leoci on 01/03/2017.
 */
public class Listar extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);

        ArrayList<Contato> contatos = (ArrayList<Contato>) getIntent().getSerializableExtra("contatos");

        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(
                getApplicationContext(),android.R.layout.simple_list_item_1
        );
        adapter.clear();
        final ListView lvContatos = (ListView) findViewById(R.id.listView1);
        lvContatos.setAdapter(adapter);
        adapter.addAll(contatos);

        final Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
