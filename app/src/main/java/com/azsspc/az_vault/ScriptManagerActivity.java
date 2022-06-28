package com.azsspc.az_vault;

import static com.azsspc.az_vault.DataLoader.loadScript;
import static com.azsspc.az_vault.DataLoader.script;
import static com.azsspc.az_vault.MainActivity.SP_KEY_AS;
import static com.azsspc.az_vault.MainActivity.sp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScriptManagerActivity extends AppCompatActivity {

    TextView sc_name, sc_lore, sc_path, sc_author, sc_avatar, sc_prop, sc_inventory, sc_target, sc_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_manager);
        sc_author = findViewById(R.id.script_author);
        sc_lore = findViewById(R.id.script_lore);
        sc_path = findViewById(R.id.script_url);
        sc_name = findViewById(R.id.script_name);
        sc_prop = findViewById(R.id.script_prop);
        sc_avatar = findViewById(R.id.script_avatar);
        sc_target = findViewById(R.id.script_target);
        sc_balance = findViewById(R.id.script_balance);
        sc_inventory = findViewById(R.id.script_inventory);
        setSettingsView();
    }

    @SuppressLint("SetTextI18n")
    void setSettingsView() {
        //sc_path.setText(script.url);
        sc_lore.setText(script.settings.getLore());
        sc_name.setText(script.settings.getName());
        sc_author.setText(getString(R.string.sc_author) + ": " + script.settings.getAuthor());
        sc_inventory.setText(getString(R.string.sc_inventory) + ": " + script.settings.getInventory());
        sc_prop.setText(getString(R.string.sc_prop) + ": " + script.settings.getProperties());
        sc_balance.setText(getString(R.string.sc_balance) + ": [" + script.settings.getBalance_min() + ", " + script.settings.getBalance_max() + "]");
    }

    public void buttonReloadActiveScript(View v) {
        loadScript(this, sp.getString(SP_KEY_AS, getResources().getStringArray(R.array.gs_url)[0]), true);
        setSettingsView();
    }

    @SuppressLint("NonConstantResourceId")
    public void showMeArrayOf(View v) {
        Intent intent = new Intent(this, SMSLActivity.class);
        switch (v.getId()) {
            case R.id.b_show_avatars:
                intent.putExtra(SMSLActivity.key, SMSLActivity.key_a);
                break;
            case R.id.b_show_items:
                intent.putExtra(SMSLActivity.key, SMSLActivity.key_i);
                break;
            case R.id.b_show_prop:
                intent.putExtra(SMSLActivity.key, SMSLActivity.key_p);
                break;
            case R.id.b_show_targets:
                intent.putExtra(SMSLActivity.key, SMSLActivity.key_t);
                break;
            default:
                return;
        }
        startActivity(intent);
    }
}