package com.example.odev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Son extends AppCompatActivity {
    TextView lblGeriSayim, lblSonuc;
    Button btnSonuc;
    MainActivity main;
    CheckBox chckbxKitap, chckbxSeyahat, chckbxBisiklet ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.son);

        // Adım 1: SharedPreferences Nesnesi Oluşturma
        SharedPreferences sharedPreferences = getSharedPreferences("kullanici", MODE_PRIVATE);
        // Adım 2: Editor Nesnesi Almak
        SharedPreferences.Editor editor = sharedPreferences.edit();

        main = new MainActivity();

        Log.i(main.LogCat, "Giris");

        btnSonuc = (Button) findViewById(R.id.btnSonuc);
        lblGeriSayim = (TextView) findViewById(R.id.lblGeriSayim);
        lblSonuc = (TextView) findViewById(R.id.lblSonuc);
        chckbxKitap = (CheckBox) findViewById(R.id.chckBxKitap);
        chckbxSeyahat = (CheckBox) findViewById(R.id.chckBxSeyahat);
        chckbxBisiklet = (CheckBox) findViewById(R.id.chckBxBisiklet);

        btnSonuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(10000, 1000){ //(Toplam süre, Periot)
                    @Override
                    public void onTick(long l) { //Periyotlarda ne olsun
                        lblGeriSayim.setText((l / 1000) + " sn");
                    }

                    @Override
                    public void onFinish() { //Bitince ne olsun

                        editor.putBoolean("kitap", chckbxKitap.isChecked()); // key: "username", value: "john_doe"
                        editor.putBoolean("seyahat", chckbxSeyahat.isChecked());
                        editor.putBoolean("bisiklet", chckbxBisiklet.isChecked());

                        // Kaydedilen veriyi hemen disk üzerine yazmak
                        editor.commit();


                        String username = sharedPreferences.getString("kAdi", "defaultKadi"); // key: "username", default: "default_username"
                        String pass = sharedPreferences.getString("parola", "defaulPass");
                        String dYili = sharedPreferences.getString("dYili", "defaulPass");
                        String bolum = sharedPreferences.getString("bolum", "defaulPass");
                        String cinsiyet = sharedPreferences.getString("cinsiyet", "defaulPass");
                        Boolean kitap = sharedPreferences.getBoolean("kitap", false);
                        Boolean seyahat = sharedPreferences.getBoolean("seyahat", false);
                        Boolean bisiklet = sharedPreferences.getBoolean("bisiklet", false);

                        Integer yas = 2025 - Integer.parseInt(dYili);
                        String icerik ="";

                        icerik +="K.Adi: ";
                        icerik +=username;
                        icerik +="\nYas: ";
                        icerik +=Integer.toString(yas);
                        icerik +="\nCinsiyet: ";
                        icerik +=cinsiyet;
                        icerik +="\nBölüm: ";
                        icerik +=bolum;
                        icerik +="\nHobiler: ";
                        if(kitap){
                            icerik +="\n-Kitap";
                        }
                        if(seyahat){
                            icerik +="\n-Seyahat";
                        }
                        if(bisiklet){
                            icerik +="\n-Bisiklet";
                        }
                        lblSonuc.setText(icerik);

                    }
                }.start();
            }
        });


    }
}