package com.lemelo.controle_v5_android_v1;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leoci on 21/04/2017.
 */

class ControleIntent extends Activity {

    private ControleGetAsync controleGetAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        imprimeControle();
    }

    private void imprimeControle() {

        controleGetAsync = new ControleGetAsync();
        controleGetAsync.execute("http://192.168.1.4:5000/controles");

        String saida = controleGetAsync.getDados();
        System.out.println("Saida: " + saida);
    }


}
