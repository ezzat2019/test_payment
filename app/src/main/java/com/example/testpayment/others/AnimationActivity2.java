package com.example.testpayment.others;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.example.testpayment.R;

public class AnimationActivity2 extends AppCompatActivity {
    private MotionLayout motionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation2);

//        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .setInitialDelay(10, TimeUnit.SECONDS).build();
//
//        WorkManager.getInstance(getApplicationContext()).enqueue(oneTimeWorkRequest);

    }

    public void starrt(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}