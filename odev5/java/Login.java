package com.example.odev;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    MainActivity main;
    TextView lblKadi, lblParola;
    EditText txtKadi, txtParola;
    Button btnSignUp, btnLogin;
    CheckBox chckbx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        SharedPreferences sharedPreferences = getSharedPreferences("kullanici", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        Boolean durum = sharedPreferences.getBoolean("beniHatirla", false);
        durum = false;
        if(durum){
            Intent i̇ntent = new Intent(Login.this, Giris.class);
            startActivity(i̇ntent);
            finish();
        }

        main = new MainActivity();

        Log.i(main.LogCat, "Login");

        lblKadi= (TextView) findViewById(R.id.lblKadi);
        lblParola = (TextView) findViewById(R.id.lblParola);
        txtKadi = (EditText) findViewById(R.id.txtKadi);
        txtParola = (EditText) findViewById(R.id.txtParola);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chckbx = (CheckBox) findViewById(R.id.chckBxBeniHatirla);



        lblKadi.setText("K.Adi :");
        lblParola.setText("Parola :");
        txtKadi.setText("");
        txtParola.setText("");
        txtKadi.setHint("Kullanıcı Adı");
        txtParola.setHint("Parola");



        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
                Log.i(main.LogCat, "Sign Up tıklandı");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sharedPreferences.getString("kAdi", "defaultKadi"); // key: "username", default: "default_username"
                String pass = sharedPreferences.getString("parola", "defaulPass");
                Log.i(main.LogCat, "K.Adi: " + username+"\npass: "+ pass);
                if(txtKadi.getText().toString().equals(username) && txtParola.getText().toString().equals(pass)){
                    if(chckbx.isChecked()){
                        editor.putBoolean("beniHatirla", true);
                        editor.apply();
                    }
                    alertMesaji("Giriş başarılı!!!\nGiriş sayfasına yönlendiriliyorsunuz.", "Login", Giris.class);

                }else{
                    Toast.makeText(Login.this,"K.Adı veya Parola Yanlış!!!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private void alertMesaji(String uyrMsj, String btnName, Class goClass){
        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
        alert.setTitle("Bilgi")
                .setMessage(uyrMsj)
                .setCancelable(false)
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i̇ntent = new Intent(Login.this, goClass);
                        startActivity(i̇ntent);
                        finish();
                    }
                })
                .show();
    }
}