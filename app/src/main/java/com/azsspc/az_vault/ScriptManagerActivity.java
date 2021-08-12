package com.azsspc.az_vault;

import static com.azsspc.az_vault.DataLoader.active_script_url;
import static com.azsspc.az_vault.DataLoader.as_settings;
import static com.azsspc.az_vault.DataLoader.getFromCloud;
import static com.azsspc.az_vault.DataLoader.loadScript;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.azsspc.az_vault.game_comp.SMSL;

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
        sc_path.setText(active_script_url);
        sc_author.setText(as_settings.getAuthor());
        sc_lore.setText(as_settings.getLore());
        sc_name.setText(as_settings.getName());
        sc_inventory.setText(getString(R.string.sc_inventory) + ": " + as_settings.getInventorySize());
        sc_prop.setText(getString(R.string.sc_prop) + ": " + as_settings.getPropertiesCount());
        sc_balance.setText(getString(R.string.sc_balance) + ": " + as_settings.getBalance());
        sc_avatar.setText(getString(R.string.sc_avatar) + ": " + (as_settings.isAvatarAllow() ? getString(R.string.sc_true) : getString(R.string.sc_false)));
        sc_target.setText(getString(R.string.sc_target) + ": " + (as_settings.isTargetAllow() ? getString(R.string.sc_true) : getString(R.string.sc_false)));
    }

    public void reloadActiveScript(View v) {
        Toast.makeText(this, getString(R.string.sc_load_wait), Toast.LENGTH_SHORT).show();
        getFromCloud(this, active_script_url.replaceAll("^.+/", ""), active_script_url);
        loadScript(this, active_script_url);
        setSettingsView();
        Toast.makeText(this, getString(R.string.sc_load_done), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NonConstantResourceId")
    public void showMeArrayOf(View v) {
        /*
        Intent intent = new Intent(this, SMSL.class);
        switch (v.getId()) {
            case R.id.b_show_avatars:
                intent.putExtra(SMSL.key, SMSL.key_a);
                break;
            case R.id.b_show_items:
                intent.putExtra(SMSL.key, SMSL.key_i);
                break;
            case R.id.b_show_prop:
                intent.putExtra(SMSL.key, SMSL.key_p);
                break;
            case R.id.b_show_targets:
                intent.putExtra(SMSL.key, SMSL.key_t);
                break;
            default:
               return;
        }
        startActivity(intent);
        */
    }
}