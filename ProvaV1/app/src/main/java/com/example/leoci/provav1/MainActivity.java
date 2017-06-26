package com.example.leoci.provav1;

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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fabrica = new FabricaConexao(MainActivity.this);
        CarregaMainActivity();
    }

    protected void CarregaMainActivity() {
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

    private void CarregaTelaListar() {
        setContentView(R.layout.activity_listar);

        SQLiteDatabase db = null;

        try{
            db = fabrica.getWritableDatabase();
            ProdutoDAO produtoDao = new ProdutoDAO(db);
            List<Produto> produtos = produtoDao.listAll();

            ArrayAdapter<Produto> arrayAdapter = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1,produtos);

            final ListView lvProdutos = (ListView) findViewById(R.id.lvProdutos);
            lvProdutos.setAdapter(arrayAdapter);

            lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
                    Produto produtoSelecionado = (Produto) parent.getItemAtPosition(position);
                    trataProdutoSelecionado(produtoSelecionado);
                }
            });
        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        final Button btnListarVoltar = (Button) findViewById(R.id.btnListarVoltar);
        btnListarVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaMainActivity();
            }
        });
    }

    private void trataProdutoSelecionado(final Produto produtoSelecionado) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Editar / Apagar ?");
        dialogo.setMessage(produtoSelecionado.toString());

        dialogo.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acao = Acao.EDITAR;
                CarregaTelaEditar(produtoSelecionado);
            }
        });

        dialogo.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = null;

                try{
                    Long produtoId = produtoSelecionado.getProdutoId();
                    db = fabrica.getWritableDatabase();
                    ProdutoDAO produtoDAO = new ProdutoDAO(db);
                    produtoDAO.deleteById(produtoId);
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

    private void CarregaTelaEditar(Produto produtoSelecionado) {
        setContentView(R.layout.activity_editar);

        final EditText txtEditProdutoId = (EditText) findViewById(R.id.txtEditProdutoId);
        final EditText txtEditProduto = (EditText) findViewById(R.id.txtEditProduto);
        final EditText txtEditQuant = (EditText) findViewById(R.id.txtEditQuant);
        final EditText txtEditPreco = (EditText) findViewById(R.id.txtEditPreco);



        txtEditProdutoId.setText(produtoSelecionado.getProdutoId().toString());
        txtEditProdutoId.setEnabled(false);
        txtEditProduto.setText(produtoSelecionado.getProduto().toString());
        txtEditQuant.setText(String.valueOf(produtoSelecionado.getQuantidade()));
        txtEditPreco.setText(produtoSelecionado.getPreco().setScale(2, RoundingMode.DOWN).toString());


        final Button btnEditSalvar = (Button) findViewById(R.id.btnEditSalvar);
        btnEditSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = null;

                try{
                    Long produtoId = Long.parseLong(txtEditProdutoId.getText().toString());
                    String produto = txtEditProduto.getText().toString();
                    int quantidade = Integer.parseInt(txtEditQuant.getText().toString());
                    BigDecimal preco = new BigDecimal(txtEditPreco.getText().toString());

                    // TODO - Colocar print para testar a saida no console.

                    db = fabrica.getWritableDatabase();
                    ProdutoDAO produtoDao = new ProdutoDAO(db);

                    Produto prod = new Produto();
                    prod.setProdutoId(produtoId);
                    prod.setProduto(produto);
                    prod.setQuantidade(quantidade);
                    prod.setPreco(preco);

                    if(acao == Acao.NOVO){
                        produtoDao.insert(prod);
                    } else if (acao == Acao.EDITAR){
                        produtoDao.update(prod);
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



        final Button btnEditVoltar = (Button) findViewById(R.id.btnEditVoltar);
        btnEditVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaListar();
            }
        });
    }

    private FabricaConexao fabrica;
    private Acao acao = null;

    protected void CarregaTelaCadastro() {
        setContentView(R.layout.activity_cadastro);

        final Button btnCadSalvar = (Button) findViewById(R.id.btnCadSalvar);
        btnCadSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtCadProduto = (EditText) findViewById(R.id.txtCadProduto);
                final EditText txtCadQuant = (EditText) findViewById(R.id.txtCadQuant);
                final EditText txtCadPreco = (EditText) findViewById(R.id.txtCadPreco);

                SQLiteDatabase db = null;

                try{
                    String produto = txtCadProduto.getText().toString();
                    int quantidade = Integer.parseInt(txtCadQuant.getText().toString());
                    BigDecimal preco = new BigDecimal(txtCadPreco.getText().toString());

                    // TODO - Colocar print para testar a saida no console.

                    db = fabrica.getWritableDatabase();
                    ProdutoDAO produtoDao = new ProdutoDAO(db);

                    Produto prod = new Produto();
                    prod.setProduto(produto);
                    prod.setQuantidade(quantidade);
                    prod.setPreco(preco);

                    if(acao == Acao.NOVO){
                        produtoDao.insert(prod);
                    } else if (acao == Acao.EDITAR){
                        produtoDao.update(prod);
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
                CarregaMainActivity();
            }
        });
    }


}
