package com.example.odev;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView lblGiris;
    CheckBox chckBxKitap, chckBxSeyahat, chckBxSpor;
    Button btnGo;
    String msj;
    String dYeri;
    Context context = this;
    Spinner spinIller;
    int pstn = 0;
    public static String mail2=""; // buna bak
    String[] iller = new String[] {"Seçiniz...", "Sinop", "Kastamonu", "İstanbul", "Ankara", "Tokat"};
    ArrayAdapter<String> illerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lblGiris = findViewById(R.id.lblGiris);
        chckBxKitap = findViewById(R.id.chckBxKitap);
        chckBxSeyahat = findViewById(R.id.chckBxSeyahat);
        chckBxSpor = findViewById(R.id.chckBxSpor);
        btnGo = findViewById(R.id.btnGo);
        spinIller = findViewById(R.id.spinner);

        Intent intent = getIntent();

        String mail = intent.getStringExtra("txtMail");

        lblGiris.setText("Hoşgeldiniz " + mail);

        illerAdapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, iller); // simle_spinner_item bak
        spinIller.setAdapter(illerAdapter);
        spinIller.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pstn = position;
                dYeri  = "Doğum Yeri : " + iller[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        btnGo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(pstn == 0){
                    Toast.makeText(getApplicationContext(),"Doğum Yeri Seçiminizi yapınız!!!", Toast.LENGTH_LONG).show();
                }else if(!chckBxKitap.isChecked() && !chckBxSeyahat.isChecked() && !chckBxSpor.isChecked()){
                    Toast.makeText(getApplicationContext(),"Lütfen Hobi Seçiminizi yapınız!!!", Toast.LENGTH_LONG).show();
                }else{
                    // ArrayLİst<Stirng>
                    msj = "Hobiler\n";
                    if(chckBxKitap.isChecked()){
                        msj += "- Kitap\n";
                    }
                    if(chckBxSeyahat.isChecked()){
                        msj += "- Seyahat\n";
                    }
                    if(chckBxSpor.isChecked()){
                        msj += "- Spor\n";
                    }
                    Intent i̇ntent = new Intent(context, MainActivity2.class);
                    i̇ntent.putExtra("msj", msj)
                                    .putExtra("dYeri", dYeri);
                    startActivity(i̇ntent);
                    msj = "";
                }

            }
        });
    }
}