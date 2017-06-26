package com.example.leoci.listacomprav1;

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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FabricaConexao fabrica;
    private Acao acao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fabrica = new FabricaConexao(MainActivity.this);
        CarregaMainActivity();
    }

    private void CarregaMainActivity() {
        setContentView(R.layout.activity_main);

        final Button bntMainNovaLista = (Button) findViewById(R.id.bntMainNovaLista);
        bntMainNovaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acao = acao.NOVO;
                CarregaTelaCadastroLista();
            }
        });

        ImprimeListas();
    }

    private void ImprimeListas() {
        SQLiteDatabase db = null;

        try{
            db = fabrica.getWritableDatabase();
            ListaDAO listaDAO = new ListaDAO(db);
            List<Lista> listas = listaDAO.listAll();

            ArrayAdapter<Lista> arrayAdapter = new ArrayAdapter<Lista>(MainActivity.this,android.R.layout.simple_list_item_1,listas);
            final ListView lvMainListas = (ListView) findViewById(R.id.lvMainListas);
            lvMainListas.setAdapter(arrayAdapter);

            lvMainListas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Lista listaSelecionada = (Lista) parent.getItemAtPosition(position);
                    trataListaSelecionada(listaSelecionada);
                }
            });
        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    private void trataListaSelecionada(final Lista listaSelecionada) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Mostrar / Apagar ?");
        dialogo.setMessage(listaSelecionada.toString());

        dialogo.setNegativeButton("Mostrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CarregaTelaListas(listaSelecionada);
            }
        });

        dialogo.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = null;

                try{
                    Long id = listaSelecionada.getId();
                    db = fabrica.getWritableDatabase();
                    ListaDAO listaDAO = new ListaDAO(db);
                    listaDAO.deleteById(id);
                } catch (Exception e) {
                    throw e;
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
                CarregaMainActivity();
            }
        });

        dialogo.setNeutralButton("Cancelar", null);
        dialogo.show();
    }

    private void CarregaTelaListas(final Lista listaSelecionada) {
        setContentView(R.layout.activity_listas);

        final Button btnListProduto = (Button) findViewById(R.id.btnListProduto);
        btnListProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acao = acao.NOVO;
                CarregaTelaCadastroProduto(listaSelecionada);
            }
        });

        final Button btnListVoltar = (Button) findViewById(R.id.btnListVoltar);
        btnListVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaMainActivity();
            }
        });

        imprimeProdutos(listaSelecionada);
    }

    private void imprimeProdutos(final Lista listaSelecionada) {
        SQLiteDatabase db = null;

        try{
            db = fabrica.getWritableDatabase();
            ProdutoDAO produtoDao = new ProdutoDAO(db);
            List<Produto> produtos = produtoDao.listAll(listaSelecionada.getId());
            BigDecimal totalLista = new BigDecimal("0");
            int nQuant = 0;
            for(int i = 0; i<produtos.size(); i++){
                BigDecimal daVez = new BigDecimal(produtos.get(i).getPreco().toString());
                nQuant = produtos.get(i).getQuantidade();
                daVez = daVez.multiply(BigDecimal.valueOf(nQuant));
                totalLista = totalLista.add(daVez);
            }

            final EditText txtListTotal = (EditText) findViewById(R.id.txtListTotal);
            txtListTotal.setText(totalLista.setScale(2,RoundingMode.DOWN).toString());

            ArrayAdapter<Produto> arrayAdapter = new ArrayAdapter<Produto>(MainActivity.this, android.R.layout.simple_list_item_1,produtos);
            final ListView lvListProdutos = (ListView) findViewById(R.id.lvListProdutos);
            lvListProdutos.setAdapter(arrayAdapter);

            lvListProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Produto produtoSelecionado = (Produto) parent.getItemAtPosition(position);
                    trataProdutoSelecionado(produtoSelecionado, listaSelecionada);
                }
            });
        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    private void trataProdutoSelecionado(final Produto produtoSelecionado, final Lista listaSelecionada) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle("Editar / Apagar ?");
        dialogo.setMessage(produtoSelecionado.toString());

        dialogo.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acao = Acao.EDITAR;
                CarregaTelaEditar(produtoSelecionado,listaSelecionada);
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
                CarregaTelaListas(listaSelecionada);
            }
        });

        dialogo.setNeutralButton("Cancelar", null);
        dialogo.show();
    }

    private void CarregaTelaEditar(Produto produtoSelecionado, final Lista listaSelecionada) {
        setContentView(R.layout.activity_produto_editar);

        final EditText txtEditProdutoId = (EditText) findViewById(R.id.txtEditProdutoId);
        final EditText txtEditListaId = (EditText) findViewById(R.id.txtEditListaId);
        final EditText txtEditProduto = (EditText) findViewById(R.id.txtEditProduto);
        final EditText txtEditQuant = (EditText) findViewById(R.id.txtEditQuant);
        final EditText txtEditPreco = (EditText) findViewById(R.id.txtEditPreco);

        txtEditProdutoId.setText(produtoSelecionado.getProdutoId().toString());
        txtEditProdutoId.setEnabled(false);
        txtEditListaId.setText(produtoSelecionado.getListaId().toString());
        txtEditListaId.setEnabled(false);
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
                    Long listaId = Long.parseLong(txtEditListaId.getText().toString());
                    String produto = txtEditProduto.getText().toString();
                    int quantidade = Integer.parseInt(txtEditQuant.getText().toString());
                    BigDecimal preco = new BigDecimal(txtEditPreco.getText().toString());

                    // TODO - Colocar print para testar a saida no console.

                    db = fabrica.getWritableDatabase();
                    ProdutoDAO produtoDao = new ProdutoDAO(db);

                    Produto prod = new Produto();
                    prod.setProdutoId(produtoId);
                    prod.setListaId(listaId);
                    prod.setProduto(produto);
                    prod.setQuantidade(quantidade);
                    prod.setPreco(preco);

                    if(acao == Acao.NOVO){
                        produtoDao.insert(prod);
                    } else if (acao == Acao.EDITAR){
                        produtoDao.update(prod);
                    }

                    Toast.makeText(getApplicationContext(), "Produto Salvo!", Toast.LENGTH_LONG).show();

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
                CarregaTelaListas(listaSelecionada);
            }
        });

    }

    private void CarregaTelaCadastroProduto(final Lista listaSelecionada) {
        setContentView(R.layout.activity_cadastro_produto);
        final Button btnCadProdSalvar = (Button) findViewById(R.id.btnCadProdSalvar);
        btnCadProdSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtCadProdProduto = (EditText) findViewById(R.id.txtCadProdProduto);
                final EditText txtCadProdQuant = (EditText) findViewById(R.id.txtCadProdQuant);
                final EditText txtCadProdPreco = (EditText) findViewById(R.id.txtCadProdPreco);

                SQLiteDatabase db = null;

                try{
                    Long listaid = listaSelecionada.getId();
                    String produto = txtCadProdProduto.getText().toString();
                    int quantidade = Integer.parseInt(txtCadProdQuant.getText().toString());
                    BigDecimal preco = new BigDecimal(txtCadProdPreco.getText().toString());

                    db = fabrica.getWritableDatabase();
                    ProdutoDAO produtoDao = new ProdutoDAO(db);

                    Produto prod = new Produto();
                    prod.setListaId(listaid);
                    prod.setProduto(produto);
                    prod.setQuantidade(quantidade);
                    prod.setPreco(preco);

                    if(acao == Acao.NOVO){
                        produtoDao.insert(prod);
                    } else if (acao == Acao.EDITAR){
                        produtoDao.update(prod);
                    }

                    Toast.makeText(getApplicationContext(), "Produto Salvo!", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    throw e;
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        });

        final Button btnCadProdVoltar = (Button) findViewById(R.id.btnCadProdVoltar);
        btnCadProdVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaListas(listaSelecionada);
            }
        });
    }

    protected void CarregaTelaCadastroLista() {
        setContentView(R.layout.activity_cadastro_lista);

        final EditText txtCadListSup = (EditText) findViewById(R.id.txtCadListSup);
        final EditText txtCadListData = (EditText) findViewById(R.id.txtCadListData);

        final SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        final String strData = data.format(Calendar.getInstance().getTime());
        txtCadListData.setText(strData);

        final Button btnCadListSalvar = (Button) findViewById(R.id.btnCadListSalvar);
        btnCadListSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = null;

                try{
                    String supermercado = txtCadListSup.getText().toString();
                    String newData = txtCadListData.getText().toString();

                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date d = dateFormat.parse(newData);
                    java.sql.Date dt = new java.sql.Date(d.getTime());

                    db = fabrica.getWritableDatabase();
                    ListaDAO listaDao = new ListaDAO(db);

                    Lista list = new Lista();
                    list.setSupermercado(supermercado);
                    list.setData(dt);
                    if(acao == Acao.NOVO){
                        listaDao.insert(list);
                    } else if(acao == Acao.EDITAR){
                        listaDao.update(list);
                    }

                    Toast.makeText(getApplicationContext(), "Lista salva!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    try {
                        throw e;
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        });

        final Button btnCadListVoltar = (Button) findViewById(R.id.btnCadListVoltar);
        btnCadListVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaMainActivity();
            }
        });
    }


}
