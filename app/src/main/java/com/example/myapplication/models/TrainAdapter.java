package com.example.myapplication.models;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class TrainAdapter extends ArrayAdapter<Train> {

    Context context;
    List<Train> objects;

    public  TrainAdapter(Context context, int resource, int textViewResourceId, List<Train> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context=context;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.onetrain, parent, false);

        TextView tvTrainname = (TextView)view.findViewById(R.id.tvTrainname);
        TextView tvTraintype = (TextView)view.findViewById(R.id.tvTrainType);
        TextView tvTrainlevel = (TextView)view.findViewById(R.id.tvTrainlevel);
        TextView tvTrainequipment = (TextView)view.findViewById(R.id.tvTrainEquipment);
        TextView tvTraintime = (TextView)view.findViewById(R.id.tvTrainTime);
        TextView tvTrainnumofwatched = (TextView)view.findViewById(R.id.tvTrainNumOfWatched);

        Train temp = objects.get(position);
        tvTrainname.setText(temp.getName());
        tvTraintype.setText(temp.getType());
        tvTrainequipment.setText(temp.getEquipment());
        tvTrainlevel.setText(temp.getLevel());
        tvTrainnumofwatched.setText("צפיות: "+String.valueOf(temp.getNumOfWatched()));
        tvTraintime.setText("זמן: "+String.valueOf(temp.getTime())+" דקות");

        return view;
    } }
