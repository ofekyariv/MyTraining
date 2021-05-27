package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.myapplication.models.Train;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {
    TextView tvTrainName, tvTrainDescription;
    private YouTubePlayerView youTubeView;
    Button btnfinish;
    public static final String API_KEY="AIzaSyAVjPNii8mdvEKQNHTX20g4sZD_szLAkvA";

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    Train theTrain;
    private String TrainRef;
    private String TrainName;
    private String TrainDesc;

    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_youtube);
        theTrain = SearchTrain.chosenTrain;
        TrainRef=theTrain.getVideoRef();

        tvTrainName=findViewById(R.id.tvTrainName);
        TrainName=theTrain.getName();
        tvTrainName.setText(TrainName);

        tvTrainDescription=findViewById(R.id.tvTrainDescription);
        TrainDesc=theTrain.getDesc();
        tvTrainDescription.setText(TrainDesc);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youTubeView);
        try {
        youTubeView.initialize(API_KEY, this);
        } catch(IllegalStateException ignored){}
        btnfinish=findViewById(R.id.btnfinish);
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youTubeView);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        final YouTubePlayer player, boolean wasRestored) {
        String videoref = TrainRef.replace("https://www.youtube.com/watch?v=","");
        videoref = videoref.replace("https://youtu.be/","");
        if (!wasRestored) {
            player.loadVideo(videoref);//will auto play video
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else {
            String errorMessage = String.format(
                    "Error playing video: %s", errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    public void openDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("כל הכבוד! סיימת את האימון!");
        alertDialogBuilder.setMessage("האם לצפות שוב?");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                youTubeView.initialize(API_KEY, Youtube.this);
            }
        });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        receiver = new BroadcastCharging();
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, ifilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}