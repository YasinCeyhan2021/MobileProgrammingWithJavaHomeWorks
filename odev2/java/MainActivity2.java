package com.example.odev;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView lblAd, lblYas;
    Button btnOkay;
    CheckBox chckBxKitap, chckBxSeyahat, chckBxSpor;
    MainActivity mainAct = new MainActivity();
    String msj = "";
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        lblAd = findViewById(R.id.lblAd);
        lblYas = findViewById(R.id.lblYas);
        chckBxKitap = findViewById(R.id.chckBxKitap);
        chckBxSeyahat = findViewById(R.id.chckBxSeyahat);
        chckBxSpor = findViewById(R.id.chckBxSpor);
        btnOkay = findViewById(R.id.btnOkay);



        Intent intent = getIntent();

        String ad = intent.getStringExtra("txtAd");
        Integer dYili = intent.getIntExtra("txtDyili", 2025);

        lblAd.setText("Hoşgeldiniz " + ad);
        lblYas.setText("Yaşınız : " + (2025 - dYili));

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chckBxKitap.isChecked()){
                    msj += "- Kitap\n";
                }
                if(chckBxSeyahat.isChecked()){
                    msj += "- Seyahat\n";
                }
                if(chckBxSpor.isChecked()){
                    msj += "- Spor\n";
                }
                hobilerMesaji(msj);
                msj = "";
            }
        });
    }
    private void hobilerMesaji(String uyrMsj){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Hobileriniz")
                .setMessage(uyrMsj)
                .setIcon(R.drawable.baseline_bubble_chart_24)
                .setCancelable(false)
                .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .show();
    }
}