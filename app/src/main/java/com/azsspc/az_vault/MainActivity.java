package com.azsspc.az_vault;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    public void buttonScriptManager(View v) {
        startActivity(new Intent(this, ScriptManagerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        DataLoader.loadScript(this, getResources().getStringArray(R.array.gs_url)[0]);
        findViewById(R.id.img_bg_main).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_center));

    }

    void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            Toast.makeText(this, getString(R.string.restart_it), Toast.LENGTH_LONG).show();
        }
    }
}

