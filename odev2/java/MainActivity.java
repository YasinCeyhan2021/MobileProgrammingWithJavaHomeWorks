package com.example.odev;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtAd, txtDyili;
    Button btnGiris;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtAd = findViewById(R.id.txtAd);
        txtDyili = findViewById(R.id.txtDyili);
        btnGiris = findViewById(R.id.btnGiris);


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(txtAd.getText().toString().equals("") && txtDyili.getText().toString().equals("")){
                        uyariMesaji("Lütfen ad ve doğum yılı kısmını boş bırakmayınız!!!");
                    }else if(txtAd.getText().toString().equals("")){
                        uyariMesaji("Lütfen ad kısmını boş bırakmayınız!!!");
                    }else if(txtDyili.getText().toString().equals("")){
                        uyariMesaji("Lütfen doğum yılı kısmını boş bırakmayınız!!!");
                    }else if(Integer.parseInt(txtDyili.getText().toString()) > 2025){
                        uyariMesaji("Doğum yılı 2025'ten büyük olamaz!!!");
                    }else{
                        Intent i̇ntent = new Intent(context, MainActivity2.class);
                        i̇ntent.putExtra("txtAd", txtAd.getText().toString())
                                .putExtra("txtDyili", Integer.parseInt(txtDyili.getText().toString()));
                        startActivity(i̇ntent);
                        finish();
                    }
                }catch (Exception e) {
                    uyariMesaji("Doğum yılı kısmını düzgün giriniz!!!");
                }
            }
        });

    }
    public void uyariMesaji(String uyrMsj){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("UYARI")
                .setMessage(uyrMsj)
                .setIcon(R.drawable.baseline_error_24)
                .setCancelable(false)
                .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}