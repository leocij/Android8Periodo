package com.lemelo.controle_v5_android_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import static com.lemelo.controle_v5_android_v1.R.layout.activity_login;

public class MainActivity extends AppCompatActivity {

    private String usuario;
    private String senha;
    private UserPostAsync userPostAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CarregaTelaLogin();
    }

    private void CarregaTelaLogin() {
        setContentView(activity_login);

        final Button btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtUsuario = (EditText) findViewById(R.id.txtUsuario);
                final EditText txtSenha = (EditText) findViewById(R.id.txtSenha);

                txtUsuario.setText("teste");
                txtSenha.setText("teste");

                usuario = txtUsuario.getText().toString();
                senha = txtSenha.getText().toString();

                String postParamaters = "username=" + usuario + "&password=" + senha;

                userPostAsync = new UserPostAsync();

                // Rede Casa
                userPostAsync.execute("http://192.168.1.4:5000/login",postParamaters);

                // Rede Celular
                //userPostAsync.execute("http://192.168.43.203:5000/login",jsonObject.toString());

                // TODO se retornar logar, chama a tela CarregarTelaMain();
            }
        });

        final Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarregarTelaMain();
            }
        });
    }

    private void CarregarTelaMain() {
        setContentView(R.layout.activity_main);

        final Button btnMainControle = (Button) findViewById(R.id.btnMainControle);
        btnMainControle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ControleIntent.class);
                startActivity(intent);
            }
        });
    }
}
