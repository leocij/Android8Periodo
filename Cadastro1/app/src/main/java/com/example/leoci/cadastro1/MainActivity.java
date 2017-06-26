package com.example.leoci.cadastro1;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contato> contatos = iniciaContatos();
    private Contato contatoInicio = null;

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
        carregaTelaPrincipal();
    }

    private void carregaTelaPrincipal() {
        setContentView(R.layout.activity_main);

        final Button btnCadastrar = (Button) findViewById(R.id.button1);
        final Button btnListar = (Button) findViewById(R.id.button2);

        btnCadastrar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                carregaTelaCadastro(contatoInicio);
            }
        });

        btnListar.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                carregaTelaListagem();
            }
        });

    }

    protected void carregaTelaCadastro(Contato contatoEditar) {
        final Button btnSalvar = (Button) findViewById(R.id.button1);
        final Button btnCancelar = (Button)findViewById(R.id.button2);
        final EditText txtNome = (EditText) findViewById(R.id.editText1);
        final EditText txtFone = (EditText) findViewById(R.id.editText2);

        if(contatoEditar!=null){
            txtNome.setText(contatoEditar.getNome());
            txtFone.setText(contatoEditar.getFone());
        }else{
            setContentView(R.layout.cadastro);



            btnSalvar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Contato contatoNovo = new Contato();
                    contatoNovo.setNome(txtNome.getText().toString());
                    contatoNovo.setFone(txtFone.getText().toString());
                    contatos.add(contatoNovo);
                    showMessage("Salvo com sucesso!", contatoNovo.toString());
                }
            });

            btnCancelar.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carregaTelaPrincipal();
                }
            });
        }

    }

    private void showMessage(String Caption, String Title) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(Title);
        dialogo.setMessage(Caption);
        dialogo.setNeutralButton("OK", null);
        dialogo.show();
    }

    protected void carregaTelaListagem() {
        setContentView(R.layout.listagem);
        final List<Contato> contatosString = new ArrayList<>();
        for(Contato contato: contatos) {
            contatosString.add(contato);
        }

        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(
                getApplicationContext(), android.R.layout.simple_list_item_1);
        adapter.clear();
        final ListView lvContatos = (ListView)findViewById(R.id.listView1);
        lvContatos.setAdapter(adapter);
        adapter.addAll(contatosString);

        lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                String contato = (String) arg0.getItemAtPosition(arg2);
                showMessage("Contato selecionado", "Selecionado: " + contato);
            }
        });

        final Button btnVoltar = (Button) findViewById(R.id.button1);
        btnVoltar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaPrincipal();
            }
        });

        final Button editarBtn = (Button) findViewById(R.id.editarBtn);
        editarBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pos = lvContatos.getSelectedItemPosition();
                carregaTelaCadastro(contatos.get(pos));

                adapter.notifyDataSetChanged();
            }
        });
    }


}
