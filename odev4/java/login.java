package com.example.odev;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    EditText txtMailLog, txtParolaLog;
    Button btnSignInLog, btnLoginLog;
    SharedPreferencesClass sharedPrefClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        txtMailLog = (EditText) findViewById(R.id.txtMailLog);
        txtParolaLog = (EditText) findViewById(R.id.txtPassLog);
        btnSignInLog = (Button) findViewById(R.id.btnSignInLog);
        btnLoginLog = (Button) findViewById(R.id.btnLoginLog);
        sharedPrefClass = new SharedPreferencesClass();

        String mail = sharedPrefClass.fileRead(login.this, "mail");
        String parola = sharedPrefClass.fileRead(login.this, "parola");

        btnLoginLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMailLog.getText().toString().equals(mail) && txtParolaLog.getText().toString().equals(parola)){
                    alertMesaji("Giriş sayfasına yönlendiriliyorsunuz!!!", "Okay" , giris.class);
                }else{
                    Toast.makeText(login.this, "Mail ve Parolanız yanlış!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignInLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMesaji("Sign In sayfasına yönlendiriliyorsunuz!!!", "Okay" , register.class);
            }
        });

    }
    private void alertMesaji(String uyrMsj, String btnName, Class goClass){
        AlertDialog.Builder alert = new AlertDialog.Builder(login.this);
        alert.setTitle("Alert")
                .setMessage(uyrMsj)
                .setCancelable(false)
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i̇ntent = new Intent(login.this, goClass);
                        startActivity(i̇ntent);
                        finish();
                    }
                })
                .show();
    }
}