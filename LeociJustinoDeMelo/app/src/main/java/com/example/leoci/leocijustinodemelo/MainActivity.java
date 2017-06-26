package com.example.leoci.leocijustinodemelo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FabricaConexao fabrica;
    private Acao acao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fabrica = new FabricaConexao(MainActivity.this);
        MostraTelaMain();
    }

    private void MostraTelaMain() {
        setContentView(R.layout.activity_main);

        final EditText txtDataInicio = (EditText) findViewById(R.id.txtDataInicio);
        final EditText txtDataFinal = (EditText) findViewById(R.id.txtDataFinal);
        final EditText txtNroFaixas = (EditText) findViewById(R.id.txtNroFaixas);

        final SimpleDateFormat data1 = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault());
        final SimpleDateFormat data2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault());

        final String strData1 = data1.format(Calendar.getInstance().getTime());
        final String strData2 = data2.format(Calendar.getInstance().getTime());

        txtDataInicio.setText(strData1);
        txtDataFinal.setText(strData2);

        final Button btnExecutar = (Button) findViewById(R.id.btnExecutar);
        btnExecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDate1 = txtDataInicio.getText().toString();
                DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

                String newDate2 = txtDataFinal.getText().toString();
                DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

                long longNroFaixas = Long.parseLong(txtNroFaixas.getText().toString());

                try {
                    Date otherDate1 = dateFormat1.parse(newDate1);
                    Date otherDate2 = dateFormat2.parse(newDate2);

                    long longData1 = otherDate1.getTime();
                    long longData2 = otherDate2.getTime();
                    long resultado = longData2 - longData1;

                    resultado = resultado / longNroFaixas;

                    List<Long> faixas = new ArrayList<Long>();
                    long i=longData1;
                    for(; i<longData2; i = i + resultado){
                        faixas.add(i);
                    }
                    faixas.add(i);

                    for(int j=0; j<faixas.size()-1; j++){
                        System.out.println("Inicio: " + new Date(faixas.get(j)));
                        System.out.println("Fim: " + new Date(faixas.get(j+1)));
                    }

                    acao = acao.NOVO;
                    SalvaNoBanco(faixas);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            private void SalvaNoBanco(List<Long> faixas) {
                SQLiteDatabase db = null;

                try{
                    db = fabrica.getWritableDatabase();
                    FaixaDAO faixaDAO = new FaixaDAO(db);

                    Faixa faixa = new Faixa();

                    for( int i =0 ; i<faixas.size()-1; i++){
                        faixa.setInicio( (new Date(faixas.get(i))).toString());
                        faixa.setFim((new Date(faixas.get(i+1))).toString());
                        faixaDAO.insert(faixa);
                    }

                    Toast.makeText(getApplicationContext(), "Salvou!", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    throw e;
                } finally {
                    if (db != null && db.isOpen()) {
                        db.close();
                    }
                }
            }
        });

        final Button btnListar = (Button) findViewById(R.id.btnListar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MostraTelaListar();
            }
        });

    }

    private void MostraTelaListar() {
        setContentView(R.layout.activity_listar);

        SQLiteDatabase db = null;

        try{
            db = fabrica.getWritableDatabase();
            FaixaDAO faixaDAO = new FaixaDAO(db);
            List<Faixa> faixas = faixaDAO.listAll();

            ArrayAdapter<Faixa> arrayAdapter = new ArrayAdapter<Faixa>(MainActivity.this,android.R.layout.simple_list_item_1,faixas);
            final ListView lvFaixas = (ListView) findViewById(R.id.lvFaixas);
            lvFaixas.setAdapter(arrayAdapter);


        } catch (Exception e) {
            throw e;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

    }
}
