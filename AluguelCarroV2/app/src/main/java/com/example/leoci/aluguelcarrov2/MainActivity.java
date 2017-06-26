package com.example.leoci.aluguelcarrov2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtMainDescricao = (EditText) findViewById(R.id.txtMainDescricao);
        final Button btnMainSalvar = (Button) findViewById(R.id.btnMainSalvar);

        btnMainSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtMainDescricao.getText().equals("")){
                    JSONObject postData = new JSONObject();
                    try {
                        //{"descricao": "teste2"}
                        postData.put("descricao", txtMainDescricao.getText().toString());
                        new SendDeviceDetails().execute("https://ppi2v2v3.herokuapp.com/multas/", postData.toString());
                        Toast.makeText(getApplicationContext(), "Registro Salvo!", Toast.LENGTH_LONG).show();
                        txtMainDescricao.setText("");
                        txtMainDescricao.setEnabled(false);
                        txtMainDescricao.setEnabled(true);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Digite alguma coisa.", Toast.LENGTH_LONG).show();
                }

            }
        });

        final Button btnMainListar = (Button) findViewById(R.id.btnMainListar);

        btnMainListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Multa multa = new Multa();
                String multas = multa.ListAll();

                JSONArray arr = null;
                try {
                    arr = new JSONArray(multas);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<String> list = new ArrayList<String>();
                List<Multa> listMultas = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    Multa m1 = new Multa();
                    try {
                        m1.setIdentifier(arr.getJSONObject(i).getString("identifier"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        m1.setDescricao(arr.getJSONObject(i).getString("descricao"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listMultas.add(m1);


                    ArrayAdapter<Multa> adapter = new ArrayAdapter<Multa>(MainActivity.this, android.R.layout.simple_list_item_1, listMultas);
                    ListView listView = (ListView) findViewById(R.id.listView1);
                    listView.setAdapter(adapter);
                    txtMainDescricao.setEnabled(false);
                    txtMainDescricao.setEnabled(true);
                }
            }
        });
    }
}
