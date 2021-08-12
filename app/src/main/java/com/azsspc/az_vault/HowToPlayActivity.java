package com.azsspc.az_vault;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        findViewById(R.id.img_bg_main).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_center));
    }
}