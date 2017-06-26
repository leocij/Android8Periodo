package com.example.leoci.meuprimeirobanco;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carregaTelaPrincipal();
    }

    private void carregaTelaPrincipal() {
        setContentView(R.layout.activity_main);

        final Button btnNovoCarro = (Button) findViewById(R.id.button1);
        final Button btnListagemCarros = (Button) findViewById(R.id.button2);

        btnNovoCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregaTelaCadastro();
            }
        });

    }

    private FabricaConexao fabrica;
    private Acao acao = null;

    private void carregaTelaCadastro() {

        final EditText editNroChassi = (EditText) findViewById(R.id.editText1);
        final EditText editMarca = (EditText) findViewById(R.id.editText2);
        final EditText editModelo = (EditText) findViewById(R.id.editText3);

        setContentView(R.layout.cadastro);

        SQLiteDatabase db = null;
        try {
            Integer nroChassi = Integer.parseInt(editNroChassi.getText().toString());
            String marca = editMarca.getText().toString();
            String modelo = editModelo.getText().toString();
            db = fabrica.getWritableDatabase();
            CarroDAO carroDAO = new CarroDAO(db);
            Carro carro = new Carro();
            if(acao == Acao.NOVO) {
                carroDAO.insert(carro);
            } else if(acao == Acao.EDITAR) {
                carroDAO.update(carro);
            }
            //ActivityUtil.showMessage(MainActivity.this, "OK!", "Salvou!");
        } catch(Exception ex) {
           // ActivityUtil.showMessage(MainActivity.this, "ERRO!!!",
                    //ex.getMessage());
        } finally {
            if(db != null && db.isOpen()) { db.close(); }
        }
    }


}
