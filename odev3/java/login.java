package com.example.odev;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    EditText txtMail, txtPass;
    Button btnGiris;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        txtMail = findViewById(R.id.txtMail);
        txtPass = findViewById(R.id.txtPass);
        btnGiris = findViewById(R.id.btnGiris);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail, pass;
                mail = txtMail.getText().toString();
                pass = txtPass.getText().toString();
                if(mail.equals("")){
                    Toast.makeText(context,"Mail adresini boş bırakamazsınız!!!", Toast.LENGTH_LONG).show();
                }else if(pass.length() < 5){
                    Toast.makeText(login.this,"Şifre 5 karakterden kısa olamaz!!!", Toast.LENGTH_LONG).show();
                }else{
                    Intent i̇ntent = new Intent(getApplicationContext(), MainActivity.class);
                    i̇ntent.putExtra("txtMail", mail);
                    startActivity(i̇ntent);
                    // startActivityForIntent(); bak
                    finish();
                }
            }
        });


    }
}