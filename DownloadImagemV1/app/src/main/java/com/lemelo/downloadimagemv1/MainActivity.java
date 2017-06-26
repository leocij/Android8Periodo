package com.lemelo.downloadimagemv1;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private ProgressDialog dialog;
    private DownloadImageTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(MainActivity.this,"download","downloading");
                task = new DownloadImageTask();
                task.execute("http://static.fnac-static.com/multimedia/Images/PT/NR/dd/0e/0b/724701/1507-1.jpg");
            }
        });
    }

    @Override
    protected void onDestroy(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
        if(task != null){
            task.cancel(true);
        }
        super.onDestroy();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                return downloadBitmap(params[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap result){
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null){
                imageView.setImageBitmap(result);
            }
        }

        private Bitmap downloadBitmap(String url) throws IOException{
            URL imageUrl = null;
            try{
                imageUrl = new URL(url);
            }catch (MalformedURLException e){
                e.printStackTrace();
                return null;
            }
            Bitmap bitmapImage = null;
            try{
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();

                bitmapImage = BitmapFactory.decodeStream(is);
            }catch (IOException e){
                e.printStackTrace();
            }
            return bitmapImage;
        }
    }
}
