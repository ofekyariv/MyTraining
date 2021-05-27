package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnGoLog, btnGoReg;
    public static boolean Admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoLog = findViewById(R.id.btngolog);
        btnGoReg = findViewById(R.id.btnGoReg);
        btnGoReg.setOnClickListener(this);
        btnGoLog.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == btnGoLog) {
            Intent goLog = new Intent(this, Login.class);
            startActivity(goLog);
        }
        if (view == btnGoReg) {
            Intent goReg = new Intent(this, Register.class);
            startActivity(goReg);
        }

    }
}