package com.example.codigolectorallisson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button Scanear;
    EditText Respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scanear= findViewById(R.id.Scanear);
        Respuesta= findViewById(R.id.Respuesta);

        Scanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrador= new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("CODIGO DE BARRITAS");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result=IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result!= null){
            if(result.getContents()== null){
                Toast.makeText(this, "LECTURA FINAL, VUELVA HA EMPEZAR", Toast.LENGTH_LONG).show();
            }else{

                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                Respuesta.setText(result.getContents());
            }
        }else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}