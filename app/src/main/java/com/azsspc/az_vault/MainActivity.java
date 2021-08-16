package com.azsspc.az_vault;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.azsspc.az_vault.gamp.views.MainGameScreen;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static com.azsspc.az_vault.DataLoader.as_settings;
import static com.azsspc.az_vault.DataLoader.as_url;
import static com.azsspc.az_vault.DataLoader.loadScript;

public class MainActivity extends AppCompatActivity {
    //public static Animation tile_center_close, tile_center_open;
    public static final String SP_KEY_AS = "spas_kay";
    public static SharedPreferences sp;

    public void buttonScriptManager(View v) {
        startActivity(new Intent(this, ScriptManagerActivity.class));
    }

    public void buttonHowToPlay(View v) {
        startActivity(new Intent(this, HowToPlayActivity.class));
    }

    public void buttonPlay(View v) {
        startActivity(new Intent(this, MainGameScreen.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        DataLoader.loadScript(this, sp.getString(SP_KEY_AS, getResources().getStringArray(R.array.gs_url)[0]), false);
        ((TextView) findViewById(R.id.placeholder)).setText(as_settings.getName());
        findViewById(R.id.img_bg_main).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_center));
        //tile_center_close = AnimationUtils.loadAnimation(this, R.anim.close_center);
        //tile_center_open = AnimationUtils.loadAnimation(this, R.anim.open_center);
    }

    void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            Toast.makeText(this, getString(R.string.restart_it), Toast.LENGTH_LONG).show();
        }
    }

    public static String[] getFromJSONArray(JSONArray data, boolean lowerCase) throws JSONException {
        int rel = data.length();
        String[] ret = new String[rel];
        for (int i = 0; i < rel; i++)
            ret[i] = lowerCase ? data.getString(i).toLowerCase() : data.getString(i);
        return ret;
    }

    public static Class<?> getTileClass(String s) throws ClassNotFoundException {
        return Class.forName("com.azsspc.az_vault.gamp.tiles.special." + s);
    }

    public static void setClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(context.getString(R.string.text_copied), text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, context.getString(R.string.text_copied), Toast.LENGTH_SHORT).show();
    }

    public void connect(View v) {
    }

    public void server(View v) {
    }
}

