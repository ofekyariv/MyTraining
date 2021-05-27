package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.models.Train;
import com.example.myapplication.models.TrainAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SearchTrain extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText etSearch;
    Button btnSearch, btnShowAll;
    ListView lvSearch;
    Spinner spType, spTime, spEquipment,spLevel;

    ArrayList<Train> all=new ArrayList<>();
    ArrayList<Train> search=new ArrayList<>();

    TrainAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String type="";
    String equipment="";
    String level="";

    int numOfWathed, time=0;
    double rating;

    public static Train chosenTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("training");

        spType = findViewById(R.id.spSErchType);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    type = parent.getItemAtPosition(position).toString();
                }
                else{
                    type="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spEquipment = findViewById(R.id.spEquipmentSe);
        spEquipment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    equipment = parent.getItemAtPosition(position).toString();
                }
                else{
                    equipment="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spLevel = findViewById(R.id.sp_levelse);
        spLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    level = parent.getItemAtPosition(position).toString();
                }
                else {
                    level="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spTime = findViewById(R.id.sp_timese);
        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    time = Integer.parseInt(parent.getItemAtPosition(position).toString().replace(" דקות",""));
                }
                else{
                    time=0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etSearch = findViewById(R.id.etSearchTrain);
        btnSearch = findViewById(R.id.btnSearchTrain);
        lvSearch = findViewById(R.id.lvITrain);
        btnShowAll = findViewById(R.id.btnAllTrains);
        btnSearch.setOnClickListener(this);
        btnShowAll.setOnClickListener( this);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(SearchTrain.this,"Please wait while getting trainings from cloud..",Toast.LENGTH_LONG).show();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Train value = postSnapshot.getValue(Train.class);
                    all.add(value);
                    Log.d("Train", "Value is: " + value);
                }
                Collections.sort(all);
                Toast.makeText(SearchTrain.this,"Done Loading, enjoy!",Toast.LENGTH_SHORT).show();
                setList(all);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
    public void setList(ArrayList<Train> list){
        adapter = new TrainAdapter(SearchTrain.this, 0, 0, list);
        lvSearch.setAdapter(adapter);
        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Train train= (Train) parent.getItemAtPosition(position);
                Intent intent = new Intent(SearchTrain.this, Youtube.class);
                chosenTrain = train;
                myRef.child(train.getId()).child("numOfWatched").setValue(train.getNumOfWatched()+1);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        String name=etSearch.getText().toString();
        if (v == btnSearch) {
            search.clear();
            for (Train current:all) {
                if ((type.equals("")||current.getType().contains(type))&&
                    (level.equals("")||current.getLevel().equals(level))&&
                    (equipment.equals("")||current.getEquipment().contains(equipment))&&
                    (time==0||current.getTime()==(time))&&
                    (name.equals("")||current.getName().contains(name))) {
                    search.add(current);
                }
            }
            setList(search);
        }
        else if (v == btnShowAll) {
            setList(all);
            Toast.makeText(getApplicationContext(), "Show All", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}


