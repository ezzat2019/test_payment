package com.example.testpayment.exo_player;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testpayment.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

public class ExoActivity extends AppCompatActivity {
    private StyledPlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    private ImageView full;

    private Boolean is = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_exo);
        playerView = findViewById(R.id.player_exo);

        full = findViewById(R.id.exo_fullscreen2);
        initPlayer();
        getVideoFromGallary();
        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("1111111111111111", "onClick: 10");
                if (is) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                else
                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                is=!is;

            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 20) {
                Uri selectedImageUri = data.getData();

                // Build the media item.
                MediaItem mediaItem = MediaItem.fromUri(selectedImageUri);
// Set the media item to be played.
                simpleExoPlayer.setMediaItem(mediaItem);
// Prepare the player.
                simpleExoPlayer.prepare();
                simpleExoPlayer.setRepeatMode(Player.REPEAT_MODE_ALL);
// Start the playback.
                simpleExoPlayer.play();


                simpleExoPlayer.addListener(new Player.EventListener() {
                    @Override
                    public void onPlaybackStateChanged(int state) {
                        if (state == Player.STATE_BUFFERING) {
                            Log.d("bbbbbbb", "onPlaybackStateChanged: wait");
                        } else {

                        }
                    }
                });

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        simpleExoPlayer.setPlayWhenReady(false);
//        simpleExoPlayer.getPlaybackState();
        simpleExoPlayer.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        simpleExoPlayer.setPlayWhenReady(true);
//        simpleExoPlayer.getPlaybackState();
        simpleExoPlayer.play();
    }

    private void getVideoFromGallary() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), 20);

    }

    private void initPlayer() {
        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter meter = new DefaultBandwidthMeter();
        TrackSelector selector = new DefaultTrackSelector(getApplicationContext());
        playerView.setKeepScreenOn(true);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

        playerView.setPlayer(simpleExoPlayer);
    }
}