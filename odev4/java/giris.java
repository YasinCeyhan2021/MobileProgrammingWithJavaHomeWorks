package com.example.odev;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class giris extends AppCompatActivity {
    TextView lblGiris, lblGeriSayim;
    SharedPreferencesClass sharedPrefClass;
    RadioGroup rdoSinif;
    RadioButton rdoBtn;
    Button btnGetir;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.giris);

        lblGiris = (TextView) findViewById(R.id.lblGiris);
        lblGeriSayim = (TextView) findViewById(R.id.lblGeriSayim);
        rdoSinif = (RadioGroup) findViewById(R.id.rdoSinif);
        btnGetir = (Button) findViewById(R.id.btnGetir);
        imageView = (ImageView) findViewById(R.id.imageView);

        sharedPrefClass = new SharedPreferencesClass();

        String ad = sharedPrefClass.fileRead(giris.this, "ad");
        String soyad = sharedPrefClass.fileRead(giris.this, "soyad");
        Integer dYili = Integer.parseInt(sharedPrefClass.fileRead(giris.this, "dYili"));
        Integer yas = 2025 - dYili;

        lblGiris.setText("Hoşgeldiniz " + ad + " " + soyad + " " +yas);

        btnGetir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rdoBtnId = rdoSinif.getCheckedRadioButtonId();
                rdoBtn = (RadioButton) findViewById(rdoBtnId);

                new CountDownTimer(11000, 1000){ //(Toplam süre, Periot)
                    @Override
                    public void onTick(long l) { //Periyotlarda ne olsun
                        lblGeriSayim.setText((l / 1000) + " sn");
                    }

                    @Override
                    public void onFinish() { //Bitince ne olsun
                        switch (rdoBtn.getText().toString()){
                            case "1. Sınıf":
                                imageView.setBackgroundResource(R.drawable.img1); // imageView.setImageResource(R.drawable.img1);
                                break;
                            case "2. Sınıf":
                                imageView.setBackgroundResource(R.drawable.img2);
                                break;
                            case "3. Sınıf":
                                imageView.setBackgroundResource(R.drawable.img3);
                                break;
                            case "4. Sınıf":
                                imageView.setBackgroundResource(R.drawable.img4);
                                break;
                        }
                    }
                }.start();
            }
        });
    }
}