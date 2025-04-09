package com.example.odev;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    EditText ad, soyad, mail, pass, passT;
    Button btnSignIn, btnLogin;
    ArrayList<String> yilListesi = new ArrayList<>();
    ArrayAdapter<String> yilAdapter;
    Spinner spinYil;
    SharedPreferencesClass sharedPrefClass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);

        sharedPrefClass = new SharedPreferencesClass();

        ad = (EditText) findViewById(R.id.txtAd);
        soyad = (EditText) findViewById(R.id.txtSoyad);
        mail = (EditText) findViewById(R.id.txtMail);
        pass = (EditText) findViewById(R.id.txtPass);
        passT = (EditText) findViewById(R.id.txtPassT);
        spinYil = (Spinner) findViewById(R.id.spinner);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        yilListesi.add("Seçiniz...");
        for(int i = 1990; i <= 2020 ;i++){
            yilListesi.add(Integer.toString(i)); // String.valueOf()
        }

        yilAdapter = new ArrayAdapter<String>(register.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, yilListesi); // simle_spinner_item bak
        spinYil.setAdapter(yilAdapter);


        btnSignIn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
                if(ad.getText().toString().equals("") || soyad.getText().toString().equals("")){
                    Toast.makeText(register.this,"Ad ve soyad kısımlarını boş bırakamayınız!!!", Toast.LENGTH_LONG).show();
                }else if(spinYil.getSelectedItem().toString().equals("Seçiniz...")){
                    Toast.makeText(register.this,"Doğum yılınızı seçiniz!!!", Toast.LENGTH_LONG).show();
                }else if(mail.getText().toString().isEmpty()){
                    Toast.makeText(register.this, "Mail kısımını boş bırakamayınız!!!", Toast.LENGTH_LONG).show();
                }else if(!(isValidEmail(mail.getText().toString()))) {
                    Toast.makeText(register.this, "Yanlış mail girdiniz!!!", Toast.LENGTH_LONG).show();
                }else if(!(pass.getText().toString().length() >= 5 && 8 >= pass.getText().toString().length())){
                    Toast.makeText(register.this, "Parolanız 5-8 karakter olmalıdır!!!", Toast.LENGTH_LONG).show();
                }else if(passT.getText().toString().isEmpty()){
                    Toast.makeText(register.this, "Parola tekrar kısımını boş bırakamayınız!!!", Toast.LENGTH_LONG).show();
                }else if(!pass.getText().toString().equals(passT.getText().toString())) {
                    Toast.makeText(register.this, "Parolanız eşleşmiyor!!!", Toast.LENGTH_LONG).show();
                }else{
                    sharedPrefClass.fileWrite(register.this, "ad", ad.getText().toString());
                    sharedPrefClass.fileWrite(register.this, "soyad", soyad.getText().toString());
                    sharedPrefClass.fileWrite(register.this, "dYili", spinYil.getSelectedItem().toString());
                    sharedPrefClass.fileWrite(register.this, "mail", mail.getText().toString());
                    sharedPrefClass.fileWrite(register.this, "parola", pass.getText().toString());
                    alertMesaji("Kayıt tamamlandı.\nLogin sayfasına yönlendiriliyorsunuz!!!", "Login");
                }
           }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                alertMesaji("Login sayfasına yönlendiriliyorsunuz!!!", "Okay");
            }
        });
    }

    boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email != null && email.matches(emailPattern);

    }
    private void alertMesaji(String uyrMsj, String btnName){
        AlertDialog.Builder alert = new AlertDialog.Builder(register.this);
        alert.setTitle("Alert")
                .setMessage(uyrMsj)
                .setCancelable(false)
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i̇ntent = new Intent(register.this, login.class);
                        startActivity(i̇ntent);
                        finish();
                    }
                })
                .show();
    }

}