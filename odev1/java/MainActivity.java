package com.example.odev;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void gonder(View view){

        EditText nesne1,nesne2,nesne3;
        TextView textView = (TextView)findViewById(R.id.textViewSonuc);

        int dyili , yas;
        String ad, soyad;

        nesne1 = findViewById(R.id.plainTextAd);
        nesne2 = findViewById(R.id.plainTextSoyad);
        nesne3 = findViewById(R.id.plainTextDYili);
        try{
            ad  = nesne1.getText().toString();
            soyad  = nesne2.getText().toString();
            dyili  = Integer.parseInt(nesne3.getText().toString());

            if(2026 > dyili){
                yas = 2025 - dyili;
                textView.setText("Ad : " + ad + "\nSoyad : " + soyad + "\nYas : " + yas);
            }else{
                textView.setText("En fazla 4 basamak girebilirsin ve 2026 dan küçük olmalı!!!");
            }
        }catch(Exception err){
            textView.setText("Lütfen Yaş Kısmını Boş Bırakmayınız!!!");
        }


    }
}