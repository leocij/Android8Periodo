package com.example.leoci.aluguelcarrov3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final EditText editText3 = (EditText) findViewById(R.id.editText3);
        final Button btnSomar = (Button) findViewById(R.id.btnSomar);

        final SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        final SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        final String strDate1 = date1.format(Calendar.getInstance().getTime());
        final String strDate2 = date2.format(Calendar.getInstance().getTime());

        editText1.setText(strDate1);
        editText2.setText(strDate2);

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newDate1 = editText1.getText().toString();
                DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                String newDate2 = editText2.getText().toString();
                DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date otherDate1 = dateFormat1.parse(newDate1);
                    Date otherDate2 = dateFormat2.parse(newDate2);

                    long difference = otherDate2.getTime() - otherDate1.getTime();
                    long diff = TimeUnit.DAYS.convert(difference,TimeUnit.MILLISECONDS);

                    String saida = ""+diff;

                    editText3.setText("Intervalo de " + saida + " dias.");


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
