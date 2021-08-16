package com.azsspc.az_vault.gamp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.azsspc.az_vault.MainActivity;
import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Character;
import com.azsspc.az_vault.gamp.tiles.Avatar;
import com.azsspc.az_vault.gamp.tiles.Item;
import com.azsspc.az_vault.gamp.tiles.Property;
import com.azsspc.az_vault.gamp.tiles.Target;
import com.azsspc.az_vault.gamp.ingame.PropertyAdapter;
import com.azsspc.az_vault.gamp.ingame.ItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static com.azsspc.az_vault.DataLoader.as_avatars;
import static com.azsspc.az_vault.DataLoader.as_items;
import static com.azsspc.az_vault.DataLoader.as_properties;
import static com.azsspc.az_vault.DataLoader.as_settings;
import static com.azsspc.az_vault.DataLoader.as_targets;
import static com.azsspc.az_vault.MainActivity.setClipboard;
import static com.azsspc.az_vault.gamp.Tile.doesTagsEquals;

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

        ap_t_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ap_t_b.getVisibility() == View.VISIBLE) ap_t_b.setVisibility(View.GONE);
                else ap_t_b.setVisibility(View.VISIBLE);
            }
        });
        ap_t_context.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ap_t_c.getVisibility() == View.VISIBLE) ap_t_c.setVisibility(View.GONE);
                else ap_t_c.setVisibility(View.VISIBLE);
                return true;
            }
        });
        ap_a_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ap_a_b.getVisibility() == View.VISIBLE) ap_a_b.setVisibility(View.GONE);
                else ap_a_b.setVisibility(View.VISIBLE);
            }
        });
        ap_a_context.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ap_a_c.getVisibility() == View.VISIBLE) ap_a_c.setVisibility(View.GONE);
                else ap_a_c.setVisibility(View.VISIBLE);
                return true;
            }
        });

        char_key_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setClipboard(v.getContext(), player.getCKC());
                return true;
            }
        });
    }

    public void switchView(View v) {
        int id = v.getId();
        rv_i.setVisibility(id == R.id.to_inventory ? View.VISIBLE : View.GONE);
        rv_p.setVisibility(id == R.id.to_properties ? View.VISIBLE : View.GONE);
        swb_map.setVisibility(id == R.id.to_map ? View.VISIBLE : View.GONE);
    }

    String tryGenKey(int counter) {
        StringBuilder char_key = new StringBuilder();
        ArrayList<String> uf = new ArrayList<>(as_properties.keySet());

        String[] buf = as_targets.keySet().toArray(new String[0]);
        Target target = as_targets.get(buf[(int) (buf.length * Math.random())]);
        buf = as_avatars.keySet().toArray(new String[0]);
        Avatar avatar = as_avatars.get(buf[(int) (buf.length * Math.random())]);
        char_key.append(target.getId()).append(" ").append(avatar.getId());
        uf.removeAll(Arrays.asList(target.getProperties()));
        uf.removeAll(Arrays.asList(avatar.getProperties()));

        HashSet<String> tags = new HashSet<>();
        ArrayList<String> list_prop = new ArrayList<>();
        list_prop.addAll(Arrays.asList(avatar.getProperties()));
        list_prop.addAll(Arrays.asList(target.getProperties()));
        for (String sin : list_prop) tags.addAll(Arrays.asList(as_properties.get(sin).getTags()));
        int prop_count = as_settings.getPropertiesCount();
        for (int i = 0; i < prop_count; i++) {
            String s = uf.get((int) (uf.size() * Math.random()));
            char_key.append(" ").append(as_properties.get(s).getId());
            for (String sin : uf)
                if (doesTagsEquals(as_properties.get(sin).getTags(), tags.toArray(new String[0])))
                    uf.remove(sin);
            uf.remove(s);
        }
        Character player = new Character(char_key.toString());
        if (player.getBalance() < as_settings.getBalanceMin() || player.getBalance() > as_settings.getBalanceMax())
            return tryGenKey(++counter);
        Toast.makeText(this, getString(R.string.key_gen_tries) + ": " + counter, Toast.LENGTH_SHORT).show();
        return char_key.toString();
    }
}