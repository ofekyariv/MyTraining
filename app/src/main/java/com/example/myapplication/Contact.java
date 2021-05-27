package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

public class Contact extends AppCompatActivity {
    EditText etProblem;
    Button btnContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        etProblem=findViewById(R.id.etProblem);
        btnContact = findViewById(R.id.btnContact);

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendProblemToWhatsApp(etProblem.getText().toString());
            }
        });
    }
    private void sendProblemToWhatsApp(String text) {
        PackageManager packageManager = Contact.this.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone=" + "0523397869" + "&text=" + text;
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                Contact.this.startActivity(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}