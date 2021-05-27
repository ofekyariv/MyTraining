package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.models.Train;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrain extends AppCompatActivity implements View.OnClickListener {

    Spinner spType, spLevel, spTime;
    EditText etTrainingName,etDescription, etVidoRef;
    RadioButton rWith,rWithout;
    String name, dedc, video, type, level,time, equipment;

    Button btnAddTrain;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);
        initViews();

        database = FirebaseDatabase.getInstance();

    }

    private void initViews() {
        spType=findViewById(R.id.sp_type);
        spLevel=findViewById(R.id.sp_level);
        spTime=findViewById(R.id.sp_time);
        etTrainingName=findViewById(R.id.training_name);
        etDescription=findViewById(R.id.desc_training);
        rWith=findViewById(R.id.with_equ);
        rWithout=findViewById(R.id.with_no_equ);
        etVidoRef=findViewById(R.id.etVideoRef);
        btnAddTrain=findViewById(R.id.btn_add_tra);
        btnAddTrain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        myRef = database.getReference("training").push();
        name=etTrainingName.getText().toString();
        dedc=etDescription.getText().toString();
        video=etVidoRef.getText().toString();
        type=spType.getSelectedItem().toString();
        level=spLevel.getSelectedItem().toString();
        time=spTime.getSelectedItem().toString().replace(" דקות","");
        if (rWith.isChecked())
            equipment=rWith.getText().toString();
        else equipment=rWithout.getText().toString();
        if(name.equals("")){
            Toast.makeText(AddTrain.this,"please enter name of training",Toast.LENGTH_SHORT).show();
        }
        else if(dedc.equals("")){
            Toast.makeText(AddTrain.this,"please enter description of training",Toast.LENGTH_SHORT).show();
        }
        else if(video.equals("")){
            Toast.makeText(AddTrain.this,"please enter video of training",Toast.LENGTH_SHORT).show();
        }
        else if(type.equals("סוג אימון")){
            Toast.makeText(AddTrain.this,"please select type of training",Toast.LENGTH_SHORT).show();
        }
        else if(level.equals("רמת האימון")){
            Toast.makeText(AddTrain.this,"please select level of training",Toast.LENGTH_SHORT).show();
        }
        else if(time.equals("משך זמן האימון")){
            Toast.makeText(AddTrain.this,"please select time of training",Toast.LENGTH_SHORT).show();
        }
        else{
            Train newTrain=new Train(myRef.getKey(),name,type,level,equipment,dedc,0,0,Login.theuser,video,Integer.parseInt(time));
            myRef.setValue(newTrain);
            Toast.makeText(AddTrain.this,"training added!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}