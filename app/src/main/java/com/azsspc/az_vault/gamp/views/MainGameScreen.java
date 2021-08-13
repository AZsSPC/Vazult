package com.azsspc.az_vault.gamp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Avatar;
import com.azsspc.az_vault.gamp.Target;

import static com.azsspc.az_vault.DataLoader.as_avatars;
import static com.azsspc.az_vault.DataLoader.as_targets;

public class MainGameScreen extends AppCompatActivity {
    TextView ap_t_t, ap_t_c, ap_t_b, ap_a_t, ap_a_c, ap_a_b;
    RecyclerView rv_p, rv_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_screen);
        rv_p = findViewById(R.id.ap_properties);
        rv_i = findViewById(R.id.ap_items);
        ap_t_t = findViewById(R.id.ap_t_top);
        ap_t_c = findViewById(R.id.ap_t_center);
        ap_t_b = findViewById(R.id.ap_t_bottom);
        ap_a_t = findViewById(R.id.ap_a_top);
        ap_a_c = findViewById(R.id.ap_a_center);
        ap_a_b = findViewById(R.id.ap_a_bottom);
        Target t = as_targets.get("survivor");
        Avatar a = as_avatars.get("a_0");
        ap_t_t.setText(t.getTop(this));
        ap_t_c.setText(t.getCenter(this));
        ap_t_b.setText(t.getBottom(this));
        ap_a_t.setText(a.getTop(this));
        ap_a_c.setText(a.getCenter(this));
        ap_a_b.setText(a.getBottom(this));
    }
}