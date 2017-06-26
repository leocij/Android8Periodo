package com.example.leoci.aluguelcarrov1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String identifier;
    private String multas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Multa multa = new Multa();
        multas = multa.ListAll();
        System.out.println("Multas: " + multas);

        try {
            JSONArray arr = new JSONArray(multas);
            List<String> list = new ArrayList<String>();

            List<Multa> listMultas = new ArrayList<>();


            for(int i = 0 ; i< arr.length(); i++){
                Multa m1 = new Multa();
                m1.setIdentifier(arr.getJSONObject(i).getString("identifier"));
                m1.setDescricao(arr.getJSONObject(i).getString("descricao"));
                listMultas.add(m1);

//                String info = arr.getJSONObject(i).getString("identifier")  +" "+ arr.getJSONObject(i).getString("descricao");
//                list.add(info);
//
//                System.out.println("Id = " + arr.getJSONObject(i).getString("identifier"));
//                System.out.println("Descrição = " + arr.getJSONObject(i).getString("descricao"));
            }

            ArrayAdapter<Multa> adapter = new ArrayAdapter<Multa>(MainActivity.this, android.R.layout.simple_list_item_1,listMultas);
            ListView listView = (ListView) findViewById(R.id.myListView);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
