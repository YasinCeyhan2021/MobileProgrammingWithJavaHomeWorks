package com.example.odev;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView  lblDyeri, lblHobies;
    Button btnSon;
    RadioGroup rdoCinsiyet;
    RadioButton rButton;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        lblHobies = (TextView) findViewById(R.id.lblHobies);
        lblDyeri = (TextView) findViewById(R.id.lblDyeri);
        btnSon = (Button) findViewById(R.id.btnSon);
        rdoCinsiyet = (RadioGroup) findViewById(R.id.rdoCinsiyet);


        Intent intent = getIntent();

        String dYeri = intent.getStringExtra("dYeri");
        String msj = intent.getStringExtra("msj");

        lblDyeri.setText(dYeri);
        lblHobies.setText(msj);


        btnSon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int rButtonId = rdoCinsiyet.getCheckedRadioButtonId();
                rButton = (RadioButton) findViewById(rButtonId);
                String cinsiyet = rButton.getText().toString();
                alertMesaji("Cinsiyetiniz : " + cinsiyet);

            }
        });

    }
    private void alertMesaji(String uyrMsj){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Bilgi")
                .setMessage(uyrMsj)
                .setCancelable(false)
                .setNegativeButton("ÇIKIŞ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setPositiveButton("Geri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }
}