package com.example.testpayment.video_ticktock;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testpayment.R;
import com.example.testpayment.video_ticktock.adapter.VideoAdapter;
import com.example.testpayment.video_ticktock.data.VideoModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    private VideoAdapter videoAdapter;
    private ViewPager2 viewPager2;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        viewPager2 = findViewById(R.id.viewPager22);
        ArrayList<VideoModel> videoModels = new ArrayList<>();
        VideoModel videoModel = new VideoModel("Big Buck Bunny", "By Blender Foundation", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        VideoModel videoModel2 = new VideoModel("Elephant Dream", "By Blender Foundation", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        VideoModel videoModel3 = new VideoModel("For Bigger Escape", "By Blender ", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");

        videoModels.add(videoModel);
        videoModels.add(videoModel2);
        videoModels.add(videoModel3);


        videoAdapter = new VideoAdapter();
        videoAdapter.setList(videoModels);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //Log.d("aaaaa", "onPageScrolled: "+position);


            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                MediaItem mediaItem = MediaItem.fromUri(videoModels.get(position).getSources());


                VideoAdapter.simpleExoPlayer.setMediaItem(mediaItem);
                VideoAdapter.simpleExoPlayer.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ALL);
                VideoAdapter.simpleExoPlayer.prepare();


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                //Log.d("aaaaa", "onPageScrollStateChanged: "+state);

            }
        });
        viewPager2.setAdapter(videoAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (VideoAdapter.simpleExoPlayer!=null)
//        {
//            if (!VideoAdapter.simpleExoPlayer.isPlaying())
//            {
//                VideoAdapter.simpleExoPlayer.play();
//            }
//        }
    }
}