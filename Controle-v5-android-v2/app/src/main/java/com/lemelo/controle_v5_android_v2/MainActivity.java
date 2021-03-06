package com.lemelo.controle_v5_android_v2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String serverSide;

    private String cookie;
    private String resposta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CarregaTelaLogin();

    }

    private void CarregaTelaLogin() {
        setContentView(R.layout.activity_login);

        final Button btnLoginLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtLoginUsername = (EditText) findViewById(R.id.txtLoginUsername);
                final EditText txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);

                String username = txtLoginUsername.getText().toString();
                String password = txtLoginPassword.getText().toString();
                String postParamaters = "username=" + username + "&password=" + password;

                UserPostAsync userPostAsync = new UserPostAsync();

                // AWS
                serverSide = "http://ec2-54-207-44-125.sa-east-1.compute.amazonaws.com:5000/";

                // Heroku
                //serverSide = "https://provav2.herokuapp.com/";

                // Rede Casa
                //serverSide = "http://192.168.1.7:5000/";

                // Rede Celular
                //serverSide = "http://192.168.43.147:5000/";

                try {

                    resposta = userPostAsync.execute(serverSide + "login",postParamaters).get();

                    if(resposta != null){
                        setCookie(resposta);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(resposta != null){
                    hideSoftKeyboard(MainActivity.this);
                    CarregaTelaPrincipal();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
                    //CarregaTelaLogin();
                }
            }

            public void hideSoftKeyboard(Activity activity) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) activity.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(), 0);
            }
        });

        final Button btnLoginCancel = (Button) findViewById(R.id.btnLoginCancel);
        btnLoginCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaPrincipal();
            }
        });
    }

    private void CarregaTelaPrincipal() {
        setContentView(R.layout.activity_main);

        final Button btnMainControle = (Button) findViewById(R.id.btnMainControle);
        btnMainControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CarregaTelaControle();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button btnMainBarganha = (Button) findViewById(R.id.btnMainBarganha);
        btnMainBarganha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CarregaTelaBarganha();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void CarregaTelaBarganha() throws InterruptedException, ExecutionException, ParseException, JSONException {
        setContentView(R.layout.activity_barganha);

        imprimeBarganhas();

        final Button btnBarganhaVoltar = (Button) findViewById(R.id.btnBarganhaVoltar);
        btnBarganhaVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaPrincipal();
            }
        });
    }

    private void imprimeBarganhas() throws ExecutionException, InterruptedException, JSONException, ParseException {
        BarganhaGetAsync barganhaGetAsync = new BarganhaGetAsync();

        String barganhas = barganhaGetAsync.execute(serverSide + "barganhas", this.getCookie()).get();

        JSONArray arr = null;

        arr = new JSONArray(barganhas);

        List<Barganha> listBarganhas = new ArrayList<>();

        for(int i = 0; i < arr.length(); i++){
            Barganha b1 = new Barganha();
            JSONObject jsonObject = arr.getJSONObject(i);

            if(jsonObject.has("identifier")){
                b1.setIdentifier(jsonObject.getLong("identifier"));
            }

            if(jsonObject.has("data")){
                String strData = jsonObject.getString("data").toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date utilData = new Date(dateFormat.parse(strData).getTime());
                java.sql.Date sqlData = new java.sql.Date(utilData.getTime());
                b1.setData(sqlData);
            }

            if(jsonObject.has("descricao")){
                b1.setDescricao(jsonObject.getString("descricao"));
            }

            if(jsonObject.has("valor")){
                b1.setValor(new BigDecimal(jsonObject.getDouble("valor")));
            }

            listBarganhas.add(b1);

            ArrayAdapter<Barganha> arrayAdapter = new ArrayAdapter<Barganha>(MainActivity.this, android.R.layout.simple_list_item_1, listBarganhas);
            ListView lvBarganhas = (ListView) findViewById(R.id.lvBarganhas);
            lvBarganhas.setAdapter(arrayAdapter);
        }
    }

    private void CarregaTelaControle() throws ExecutionException, InterruptedException, JSONException, ParseException {
        setContentView(R.layout.activity_controle);

        imprimeControles();

        final Button btnControleVoltar = (Button) findViewById(R.id.btnControleVoltar);
        btnControleVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregaTelaPrincipal();
            }
        });

    }

    private void imprimeControles() throws ExecutionException, InterruptedException, JSONException, ParseException {

        //System.out.println("Cookie no Post: " + this.getCookie());

        ControleGetAsync controleGetAsync = new ControleGetAsync();

        String controles = controleGetAsync.execute(serverSide + "controles", this.getCookie()).get();

        JSONArray arr = null;

        arr = new JSONArray(controles);

        //List<String> list = new ArrayList<String>();
        List<Controle> listControles = new ArrayList<>();

        //DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        for(int i = 0; i < arr.length(); i++){
            Controle c1 = new Controle();
            JSONObject jsonObject = arr.getJSONObject(i);

            if(jsonObject.has("identifier")){
                c1.setIdentifier(jsonObject.getLong("identifier"));
            }

            if(jsonObject.has("data")){
                String strData = jsonObject.getString("data").toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date utilData = new Date(dateFormat.parse(strData).getTime());
                java.sql.Date sqlData = new java.sql.Date(utilData.getTime());
                c1.setData(sqlData);
            }

            if(jsonObject.has("descricao")){
                c1.setDescricao(jsonObject.getString("descricao"));
            }

            if(jsonObject.has("entrada")){
                c1.setEntrada(new BigDecimal(jsonObject.getDouble("entrada")));
            }

            if(jsonObject.has("saida")){
                c1.setSaida(new BigDecimal(jsonObject.getDouble("saida")));
            }

            listControles.add(c1);

            ArrayAdapter<Controle> arrayAdapter = new ArrayAdapter<Controle>(MainActivity.this, android.R.layout.simple_list_item_1, listControles);
            ListView lvControles = (ListView) findViewById(R.id.lvControles);
            lvControles.setAdapter(arrayAdapter);
        }
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}


