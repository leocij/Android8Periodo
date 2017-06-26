package com.lemelo.testeservicov1;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        int count = 0;
        public void handleMessage(Message message) {
            count++;
            Object path = message.obj;
            if (message.arg1 == RESULT_OK && path != null) {
                Toast.makeText(getApplicationContext() ,
                        "download em: " + path.toString() + " " + count, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "download erro: ", Toast.LENGTH_LONG).show();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownloadService.class);
                Messenger messenger = new Messenger(handler);
                intent.putExtra("messenger", messenger);
                intent.setData(Uri.parse("index.html"));
                intent.putExtra("urlPath", "http://velocidade.ctbc.com.br/");
                startService(intent);
            }
        });
    }
}
