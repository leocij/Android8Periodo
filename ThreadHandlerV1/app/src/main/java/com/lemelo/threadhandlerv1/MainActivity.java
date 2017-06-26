package com.lemelo.threadhandlerv1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

   // private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        final Button button = (Button) findViewById(R.id.button);
        //handler = new Handler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i<=100; i++){
                            final int value = i;
                            try{
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            //button.setText("Nao funciona");

//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBar.setProgress(value);
//                                }
//                            });

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(value);
                                }
                            });
                        }
                    }
                };
                new Thread(runnable).start();
            }
        });
    }
}
