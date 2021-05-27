package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    String admin = "galfeniger@gmail.com";
    EditText etFname, etLnmaee, etPass,etEmail, etPhone;
    Button btnReg;
    TextView etbdate;
    String  fname, lname, pass, email, phone, date;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public static final String MyPREFERENCES ="myprefs" ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init_views();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    private void init_views() {
        etFname=findViewById(R.id.etFname);
        etLnmaee=findViewById(R.id.etLastname);
        etEmail=findViewById(R.id.etEmailAddress);
        etPass=findViewById(R.id.ettPassword);
        etPhone=findViewById(R.id.etPhone);
        etbdate=findViewById(R.id.etDate);
        btnReg=findViewById(R.id.btnReg);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fname= String.valueOf(etFname.getText());
        lname=etLnmaee.getText().toString();
        phone=etPhone.getText().toString();
        email=etEmail.getText().toString();
        pass=etPass.getText().toString();
        date=etbdate.getText().toString();

        Toast.makeText(this,fname+" "+lname+" "+phone+" ",Toast.LENGTH_LONG).show();

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("TAG", "createUserWithEmail:success");
                    FirebaseUser Fireuser = mAuth.getCurrentUser();
                    User newUser=  new User(Fireuser.getUid(),fname,lname,phone,email,pass,date);
                    myRef.child(Fireuser.getUid()).setValue(newUser);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
                    editor.apply();

                    if (email.equals(admin)) {
                        MainActivity.Admin=true;
                        Intent go= new Intent(Register.this,userPage.class);//change to AdminPage
                        startActivity(go);
                    }
                    else {
                        MainActivity.Admin=false;
                        Intent go = new Intent(Register.this, userPage.class);
                        startActivity(go);
                    }
                } else {
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
