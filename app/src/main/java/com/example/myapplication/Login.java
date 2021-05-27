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
import android.widget.Toast;

import com.example.myapplication.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public  class Login  extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="SIGNIN";
    Button btnLogin;
    EditText etEmail, etPassword;
    String email,password;
    private FirebaseAuth mAute;
    String admin = "galfeniger@gmail.com";
    public static final String MyPREFERENCES ="myprefs" ;
    SharedPreferences sharedPreferences;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public static User theuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAute= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");

        init_views();

        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        email=sharedPreferences.getString("email", "");
        etEmail.setText(email);
        password = sharedPreferences.getString("password","");
        etPassword.setText(password);
    }

    private void init_views(){
        etEmail= findViewById(R.id.etEmailLogin);
        etPassword=findViewById(R.id.etPassLogin);
        btnLogin=findViewById(R.id.btnLogin2);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        email = etEmail.getText().toString();
        password=etPassword.getText().toString();
        mAute.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser fireUser = mAute.getCurrentUser();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();

                    myRef.child(fireUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                 theuser = dataSnapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w("TAG", "Failed to read value.", error.toException());
                        }
                    });
                    if (email.equals(admin)) {
                        MainActivity.Admin=true;
                        Intent go= new Intent(Login.this,userPage.class);//change to AdminPage
                        startActivity(go);
                    }
                    else {
                        MainActivity.Admin=false;
                        Intent go = new Intent(Login.this, userPage.class);
                        startActivity(go);
                    }
                }
                else {
                        Log.w(TAG , "signInWithEmail:failure",task.getException());
                        Toast.makeText(Login.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}