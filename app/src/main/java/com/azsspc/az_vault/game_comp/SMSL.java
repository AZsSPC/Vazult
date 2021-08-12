package com.azsspc.az_vault.game_comp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.azsspc.az_vault.R;

public class SMSL extends AppCompatActivity {
    public static final String key = "smsl_key";
    public static final String key_a = "smsl_key_a";
    public static final String key_i = "smsl_key_i";
    public static final String key_t = "smsl_key_t";
    public static final String key_p = "smsl_key_p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        setContentView(R.layout.activity_smsl);
        switch (arguments.getString(key)) {
            case key_a:
                break;
            case key_i:
                break;
            case key_t:
                break;
            case key_p:
                break;
        }
    }
}