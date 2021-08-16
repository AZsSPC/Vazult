package com.azsspc.az_vault;

import static com.azsspc.az_vault.DataLoader.as_url;
import static com.azsspc.az_vault.DataLoader.as_settings;
import static com.azsspc.az_vault.DataLoader.getFromCloud;
import static com.azsspc.az_vault.DataLoader.loadScript;

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
        sc_path.setText(as_url);
        sc_lore.setText(as_settings.getLore());
        sc_name.setText(as_settings.getName());
        sc_author.setText(getString(R.string.sc_author) + ": " + as_settings.getAuthor());
        sc_inventory.setText(getString(R.string.sc_inventory) + ": " + as_settings.getInventorySize());
        sc_prop.setText(getString(R.string.sc_prop) + ": " + as_settings.getPropertiesCount());
        sc_balance.setText(getString(R.string.sc_balance) + ": [" + as_settings.getBalanceMin() + ", " + as_settings.getBalanceMax() + "]");
    }

    public void buttonReloadActiveScript(View v) {
        Log.e("asd", "" + loadScript(this, as_url, true));
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