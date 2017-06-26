package com.example.leoci.meuprimeirobancov2;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Carro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fabrica = new FabricaConexao(MainActivity.this);

        CarregaTelaPrincipal();
    }

    protected void CarregaTelaPrincipal() {
        setContentView(R.layout.activity_main);

        final Button btnMainCadastrar = (Button) findViewById(R.id.btnMainCadastrar);
        btnMainCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acao = acao.NOVO;
                CarregaTelaCadastro();
            }
        });

        final Button btnMainListar = (Button) findViewById(R.id.btnMainListar);
        btnMainListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaListar();
            }
        });
    }

    protected void CarregaTelaListar() {
        setContentView(R.layout.activity_listar);

        SQLiteDatabase db = null;

        try {

            db = fabrica.getWritableDatabase();
            CarroDAO carroDAO = new CarroDAO(db);
            List<Carro> carros = carroDAO.listAll();
            ArrayAdapter<Carro> arrayAdapter = new ArrayAdapter<Carro>(MainActivity.this, android.R.layout.simple_list_item_1, carros);

            final ListView lvCarros = (ListView) findViewById(R.id.listView1);
            lvCarros.setAdapter(arrayAdapter);

            lvCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                    Carro carroSelecionado = (Carro) arg0.getItemAtPosition(arg2);
                    trataCarroSelecionado(carroSelecionado);
                }
            });

        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }


        final Button btnListVoltar = (Button) findViewById(R.id.btnListVoltar);
        btnListVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaPrincipal();
            }
        });
    }

    private void trataCarroSelecionado(final Carro carroSelecionado) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Editar / Apagar?");
        dialogo.setMessage(carroSelecionado.toString());

        dialogo.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                carro = carroSelecionado;
                acao = Acao.EDITAR;
                CarregaTelaCadastro();
            }
        });

        dialogo.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = null;

                try {
                    Integer nroChassi = carroSelecionado.getNroChassi();
                    db = fabrica.getWritableDatabase();
                    CarroDAO carroDAO = new CarroDAO(db);
                    carroDAO.delete(nroChassi);
                } catch (Exception e) {
                    throw e;
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
                CarregaTelaListar();
            }
        });

        dialogo.setNeutralButton("Cancelar", null);
        dialogo.show();
    }

    private FabricaConexao fabrica;
    private Acao acao = null;

    protected void CarregaTelaCadastro() {
        setContentView(R.layout.activity_cadastro);

        final Button btnCadSalvar = (Button) findViewById(R.id.btnCadSalvar);
        btnCadSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtCadNroChassi = (EditText) findViewById(R.id.txtCadNroChassi);
                final EditText txtCadMarca = (EditText) findViewById(R.id.txtCadMarca);
                final EditText txtCadModelo = (EditText) findViewById(R.id.txtCadModelo);

                SQLiteDatabase db = null;

                try {
                    Integer nroChassi = Integer.parseInt(txtCadNroChassi.getText().toString());
                    String marca = txtCadMarca.getText().toString();
                    String modelo = txtCadModelo.getText().toString();

                    db = fabrica.getWritableDatabase();
                    CarroDAO carroDao = new CarroDAO(db);

                    Carro carro = new Carro();
                    carro.setNroChassi(nroChassi);
                    carro.setMarca(marca);
                    carro.setModelo(modelo);

                    if (acao == Acao.NOVO) {
                        carroDao.insert(carro);
                    } else if (acao == Acao.EDITAR) {
                        carroDao.update(carro);
                    }

                    Toast.makeText(getApplicationContext(), "Registro Salvo!", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    throw e;
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        });


        final Button btnCadVoltar = (Button) findViewById(R.id.btnCadVoltar);
        btnCadVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaPrincipal();
            }
        });
    }
}
