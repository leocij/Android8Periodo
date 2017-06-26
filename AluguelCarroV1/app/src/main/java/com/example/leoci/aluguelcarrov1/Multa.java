package com.example.leoci.aluguelcarrov1;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by leoci on 05/04/2017.
 */

public class Multa {
    private String identifier;
    private String descricao;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String ListAll(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try{
            URL url = new URL("https://ppi2v2v3.herokuapp.com/multas/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode!=200){
                throw new RuntimeException("Erro HTTP: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output1 = "";
            String output2 = "";
            System.out.println("\n\nRetorno do Servidor ... \n");
            while((output1=br.readLine())!=null){
                output2 += output1;
            }
            System.out.println("Multas: " + output2);

            conn.disconnect();

            return output2;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return identifier + " - " + descricao;
    }
}
