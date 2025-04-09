package com.example.odev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Giris extends AppCompatActivity {
    MainActivity main;
    TextView lblBolum;
    ArrayList<String> bolumListesi = new ArrayList<>();
    ArrayAdapter<String> bolumAdapter;
    Spinner spinBolum;
    ImageView imageView;
    Button btnGo;
    ProgressBar progressBar;
    RadioGroup rdoSinif;
    RadioButton rdoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.giris);

        // Adım 1: SharedPreferences Nesnesi Oluşturma
        SharedPreferences sharedPreferences = getSharedPreferences("kullanici", MODE_PRIVATE);
        // Adım 2: Editor Nesnesi Almak
        SharedPreferences.Editor editor = sharedPreferences.edit();

        main = new MainActivity();

        Log.i(main.LogCat, "Giris");

        lblBolum = (TextView) findViewById(R.id.lblBolum);
        spinBolum = (Spinner) findViewById(R.id.spinner);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnGo = (Button) findViewById(R.id.btnGo);
        rdoSinif = (RadioGroup) findViewById(R.id.rdoCinsiyet);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        lblBolum.setText("Bölüm :");
        bolumListesi.add("Seçiniz...");
        bolumListesi.add("Bilgisayar Mühendisliği");
        bolumListesi.add("Makina Mühendisliği");
        bolumListesi.add("Elektrik Elektronik Mühendisliği");

        Log.i(main.LogCat, "Giris - 1");

        bolumAdapter = new ArrayAdapter<String>(Giris.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bolumListesi); // simle_spinner_item bak
        spinBolum.setAdapter(bolumAdapter);

        Log.i(main.LogCat, "Giris - 2");

        spinBolum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(main.LogCat, "Giris - 3");
                switch (position){
                    case 0:
                        imageView.setVisibility(View.GONE); // imageView.setImageResource(R.drawable.img1);
                        break;
                    case 1:
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setBackgroundResource(R.drawable.bil);
                        break;
                    case 2:
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setBackgroundResource(R.drawable.mak);
                        break;
                    case 3:
                        imageView.setVisibility(View.VISIBLE);
                        imageView.setBackgroundResource(R.drawable.elek);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new CountDownTimer(5000, 100){ //(Toplam süre, Periot)
                    @Override
                    public void onTick(long l) { //Periyotlarda ne olsun


                    }

                    @Override
                    public void onFinish() { //Bitince ne olsun
                        // Adım 3: Veriyi Kaydetmek (Örnek olarak bir String)

                        Log.i(main.LogCat, "Giris - 10");
                        int rdoBtnId = rdoSinif.getCheckedRadioButtonId();
                        rdoBtn = (RadioButton) findViewById(rdoBtnId);
                        editor.putString("cinsiyet", rdoBtn.getText().toString()); // key: "username", value: "john_doe"
                        editor.putString("bolum", spinBolum.getSelectedItem().toString());

                        // Kaydedilen veriyi hemen disk üzerine yazmak
                        editor.commit();
                        Log.i(main.LogCat, "Giris - 11");
                        Intent i̇ntent = new Intent(Giris.this, Son.class);
                        startActivity(i̇ntent);
                        finish();
                    }
                }.start();
            }
        });
        Log.i(main.LogCat, "Giris - 12");
    }

}