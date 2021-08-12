package com.azsspc.az_vault;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    public static Animation tile_center_close, tile_center_open;

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
        tile_center_close = AnimationUtils.loadAnimation(this, R.anim.close_center);
        tile_center_open = AnimationUtils.loadAnimation(this, R.anim.open_center);
    }

    void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            Toast.makeText(this, getString(R.string.restart_it), Toast.LENGTH_LONG).show();
        }
    }

    public static String[] getFromJSONArray(JSONArray data) throws JSONException {
        int rel = data.length();
        String[] ret = new String[rel];
        for (int i = 0; i < rel; i++)
            ret[i] = data.getString(i);
        return ret;
    }

    public static Class<?> getCPFS(String s) throws ClassNotFoundException {
        return Class.forName("com.azsspc.az_vault.game_comp.special." + s);
    }
}

