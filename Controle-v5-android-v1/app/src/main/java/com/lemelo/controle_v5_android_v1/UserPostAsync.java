package com.lemelo.controle_v5_android_v1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by leoci on 22/04/2017.
 */

class UserPostAsync extends AsyncTask<String,Void,String>{
    private String cookie;



    @Override
    protected String doInBackground(String... params) {

        String data = "";
        HttpURLConnection httpURLConnection = null;

        try {
            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(params[1].getBytes().length);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(params[1]);
            wr.flush();
            wr.close();

            //TODO pegar Token.

            int codeResponse = httpURLConnection.getResponseCode();

            cookie = httpURLConnection.getHeaderField("Set-Cookie");

            String[] cookies1 = cookie.split(";");
            String[] cookies2 = cookies1[0].split("=");

            setCookie(cookies2[1]);

            System.out.println("Cookie: " + cookie);
            System.out.println("Cookie2[1]: " + cookies2[1]);

            if (codeResponse != 200) {
                throw new RuntimeException("Erro HTTP: " + httpURLConnection.getResponseCode());
            }

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        System.out.println("Data: " + data);

        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.e("TAG", result);
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
