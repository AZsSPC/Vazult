package com.azsspc.az_vault.gamp.views;

import static com.azsspc.az_vault.DataLoader.script;
import static com.azsspc.az_vault.MainActivity.setClipboard;
import static com.azsspc.az_vault.gamp.Tile.doesTagsEquals;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Character;
import com.azsspc.az_vault.gamp.ingame.ItemAdapter;
import com.azsspc.az_vault.gamp.ingame.PropertyAdapter;
import com.azsspc.az_vault.gamp.tiles.Avatar;
import com.azsspc.az_vault.gamp.tiles.Target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainGameScreen extends AppCompatActivity {
    TextView ap_t_t, ap_t_c, ap_t_b, ap_a_t, ap_a_c, ap_a_b, char_key_view;
    RecyclerView rv_p, rv_i;
    View ap_t_context, ap_a_context, swb_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_screen);
        findAllViews();
        initContext();
    }

    void findAllViews() {
        char_key_view = findViewById(R.id.char_key);
        rv_p = findViewById(R.id.ap_properties);
        rv_i = findViewById(R.id.ap_items);
        ap_t_t = findViewById(R.id.ap_t_top);
        ap_t_c = findViewById(R.id.ap_t_center);
        ap_t_b = findViewById(R.id.ap_t_bottom);
        ap_a_t = findViewById(R.id.ap_a_top);
        ap_a_c = findViewById(R.id.ap_a_center);
        ap_a_b = findViewById(R.id.ap_a_bottom);
        ap_t_context = findViewById(R.id.ap_t_context);
        ap_a_context = findViewById(R.id.ap_a_context);
        swb_map = findViewById(R.id.ap_game);
    }

    void initContext() {
        Character player = new Character(tryGenKey(1));
        Target t = player.getTarget();
        Avatar a = player.getAvatar();
        char_key_view.setText(player.getCKC());
        ap_t_t.setText(t.getTop(this));
        ap_t_c.setText(t.getCenter(this));
        ap_t_b.setText(t.getBottom(this));
        ap_a_t.setText(a.getTop(this));
        ap_a_c.setText(a.getCenter(this));
        ap_a_b.setText(a.getBottom(this));
        rv_p.setAdapter(new PropertyAdapter(this, Arrays.asList(player.getProperties())));
        rv_i.setAdapter(new ItemAdapter(this, Arrays.asList(player.getInventory())));

        ap_t_context.setOnClickListener(v -> {
            if (ap_t_b.getVisibility() == View.VISIBLE) ap_t_b.setVisibility(View.GONE);
            else ap_t_b.setVisibility(View.VISIBLE);
        });
        ap_t_context.setOnLongClickListener(v -> {
            if (ap_t_c.getVisibility() == View.VISIBLE) ap_t_c.setVisibility(View.GONE);
            else ap_t_c.setVisibility(View.VISIBLE);
            return true;
        });
        ap_a_context.setOnClickListener(v -> {
            if (ap_a_b.getVisibility() == View.VISIBLE) ap_a_b.setVisibility(View.GONE);
            else ap_a_b.setVisibility(View.VISIBLE);
        });
        ap_a_context.setOnLongClickListener(v -> {
            if (ap_a_c.getVisibility() == View.VISIBLE) ap_a_c.setVisibility(View.GONE);
            else ap_a_c.setVisibility(View.VISIBLE);
            return true;
        });

        char_key_view.setOnLongClickListener(v -> {
            setClipboard(v.getContext(), player.getCKC());
            return true;
        });
    }

    public void switchView(View v) {
        int id = v.getId();
        rv_i.setVisibility(id == R.id.to_inventory ? View.VISIBLE : View.GONE);
        rv_p.setVisibility(id == R.id.to_properties ? View.VISIBLE : View.GONE);
        swb_map.setVisibility(id == R.id.to_map ? View.VISIBLE : View.GONE);
    }

    String getID(String[] n) {
        return n[(int) ((n.length - 0.1) * Math.random())];
    }

    String tryGenKey(int counter) {
        StringBuilder char_key = new StringBuilder();
        ArrayList<String> uf = new ArrayList<>(script.properties.keySet());

        String target_id = getID(script.targets.keySet().toArray(new String[0]));
        Target target = script.targets.get(target_id);
        String avatar_id = getID(script.avatars.keySet().toArray(new String[0]));
        Avatar avatar = script.avatars.get(avatar_id);
        char_key.append(target_id).append(" ").append(avatar_id);
        uf.removeAll(Arrays.asList(target.getProperties()));
        uf.removeAll(Arrays.asList(avatar.getProperties()));

        HashSet<String> tags = new HashSet<>();
        ArrayList<String> list_prop = new ArrayList<>();
        list_prop.addAll(Arrays.asList(avatar.getProperties()));
        list_prop.addAll(Arrays.asList(target.getProperties()));
        for (String sin : list_prop) tags.addAll(Arrays.asList(script.properties.get(sin).getTags()));
        int prop_count = script.settings.getProperties();
        for (int i = 0; i < prop_count; i++)
            try {
                String s = uf.get((int) ((uf.size() - 0.01) * Math.random()));
                char_key.append(" ").append(s);
                uf.removeIf(sin -> doesTagsEquals(script.properties.get(sin).getTags(), tags.toArray(new String[0])));
                uf.remove(s);
            } catch (Exception ignored) {
            }
        Character player = new Character(char_key.toString());
        if (player.getBalance() < script.settings.getBalance_min() || player.getBalance() > script.settings.getBalance_max())
            return tryGenKey(++counter);
        Toast.makeText(this, getString(R.string.key_gen_tries) + ": " + counter, Toast.LENGTH_SHORT).show();
        return char_key.toString();
    }
}