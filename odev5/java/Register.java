package com.example.odev;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    TextView lblKadiReg, lblParolaReg, lblDyili, lblProgressBar;
    EditText txtKadiReg, txtParolaReg, txtDateOicker;
    Button btnLoginReg, btnSignUpReg;
    MainActivity main;
    ImageView btnDatePicker;
    ProgressBar progressBar;
    int progressStatus;
    LinearLayout progressLayout;
    Handler handler = new Handler();
    String selectedGun="", selectedAy="", selectedYil="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);

        // Adım 1: SharedPreferences Nesnesi Oluşturma
        SharedPreferences sharedPreferences = getSharedPreferences("kullanici", MODE_PRIVATE);
        // Adım 2: Editor Nesnesi Almak
        SharedPreferences.Editor editor = sharedPreferences.edit();

        main = new MainActivity();

        Log.i(main.LogCat, "Sign Up");

        lblKadiReg = (TextView) findViewById(R.id.lblKadiReg);
        lblParolaReg = (TextView) findViewById(R.id.lblParolaReg);
        lblDyili = (TextView) findViewById(R.id.lblDyili);
        txtKadiReg = (EditText) findViewById(R.id.txtKadiReg);
        txtParolaReg = (EditText) findViewById(R.id.txtParolaReg);
        btnLoginReg = (Button) findViewById(R.id.btnLoginReg);
        btnSignUpReg = (Button) findViewById(R.id.btnSignUpReg);
        txtDateOicker = (EditText) findViewById(R.id.txtDatePicker);
        btnDatePicker = (ImageView) findViewById(R.id.btnDatePicker);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressLayout = (LinearLayout) findViewById(R.id.progressLayout);
        lblProgressBar = (TextView) findViewById(R.id.lblProgressBar);


        lblKadiReg.setText("K.Adi :");
        lblParolaReg.setText("Parola :");
        lblDyili.setText("D.Yılı :");
        txtKadiReg.setText("");
        txtParolaReg.setText("");
        txtKadiReg.setHint("Kullanıcı Adı");
        txtParolaReg.setHint("Parola");


        btnLoginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
                Log.i(main.LogCat, "Login Tıklandı");
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mevcut tarihi almak için Calendar nesnesi oluştur
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // DatePickerDialog aç
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Kullanıcının seçtiği tarihi EditText'e yazdır
                    txtDateOicker.setHint(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    selectedGun = Integer.toString(selectedDay);
                    selectedAy = Integer.toString(selectedMonth);
                    selectedYil = Integer.toString(selectedYear);
                }, year, month, day);

                datePickerDialog.show();
            }

        });
        btnSignUpReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressLayout.setVisibility(View.VISIBLE);
                progressStatus = 0;
                progressBar.setProgress(progressStatus);
                lblProgressBar.setText("%" + progressStatus);// setProgress setMax

                new CountDownTimer(5000, 50){ //(Toplam süre, Periot)
                    @Override
                    public void onTick(long l) { //Periyotlarda ne olsun
                        progressStatus += 1; // %2 artır
                        progressBar.setProgress(progressStatus);
                        lblProgressBar.setText("%" + progressStatus);
                    }

                    @Override
                    public void onFinish() { //Bitince ne olsun
                        // Adım 3: Veriyi Kaydetmek (Örnek olarak bir String)
                        progressLayout.setVisibility(View.GONE);
                        editor.putString("kAdi", txtKadiReg.getText().toString()); // key: "username", value: "john_doe"
                        editor.putString("parola", txtParolaReg.getText().toString());
                        editor.putString("dGunu", selectedGun);
                        editor.putString("dAyi", selectedAy);
                        editor.putString("dYili", selectedYil);
                        // Kaydedilen veriyi hemen disk üzerine yazmak
                        editor.commit();

                        alertMesaji("Kayıt Tamamlandı!!!\nLogin sayfasına yönlendiriliyorsunuz.", "Login", Login.class);

                    }
                }.start();
            }
        });

    }

    private void alertMesaji(String uyrMsj, String btnName, Class goClass){
        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
        alert.setTitle("Bilgi")
                .setMessage(uyrMsj)
                .setCancelable(false)
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i̇ntent = new Intent(Register.this, goClass);
                        startActivity(i̇ntent);
                        finish();
                    }
                })
                .show();
    }


}