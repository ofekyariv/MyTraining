package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userPage extends AppCompatActivity implements View.OnClickListener {
    Button search,create,live;
    TextView tv_contact,tv_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        create = findViewById(R.id.add_training);
        search = findViewById(R.id.search_training);
        live = findViewById(R.id.live_training);
        tv_contact = findViewById(R.id.tv_contact);
        tv_about = findViewById(R.id.tv_about);

        search.setOnClickListener(this);
        create.setOnClickListener(this);
        live.setOnClickListener(this);
        tv_contact.setOnClickListener(this);
        tv_about.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == create) {
            Intent intent = new Intent(this, AddTrain.class);
            startActivity(intent);
        }

        if (v == search) {
            Intent intent = new Intent(this, SearchTrain.class);
            startActivity(intent);
        }
        if (v == live) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
        if (v == tv_contact) {
            Intent intent = new Intent(this, Contact.class);
            startActivity(intent);
        }
        if (v == tv_about) {
            Intent intent = new Intent(this, Aboutas.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Add menu
        if (MainActivity.Admin) {
            getMenuInflater().inflate(R.menu.admin_main_menu, menu);
        }
        else{
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.share) // identify item pressed
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download MyTraining Now!");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        if(item.getItemId() == R.id.DataBaseControl)
        {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://console.firebase.google.com/u/7/project/danaproject-eb72a/database/danaproject-eb72a/data"));
            startActivity(browserIntent);
        }

        return true;
    }
}