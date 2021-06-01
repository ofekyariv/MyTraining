package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class userPage extends AppCompatActivity implements View.OnClickListener {
    Button search,create,live;
    TextView tv_contact,tv_about;
    private static final int VIDEO_CAPTURE = 101;

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
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, VIDEO_CAPTURE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri videoUri = data.getData();

        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        videoUri, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
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