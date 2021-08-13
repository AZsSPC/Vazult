package com.azsspc.az_vault;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.azsspc.az_vault.gamp.Avatar;
import com.azsspc.az_vault.gamp.Item;
import com.azsspc.az_vault.gamp.smsl.AvatarAdapter;
import com.azsspc.az_vault.gamp.smsl.ItemAdapter;
import com.azsspc.az_vault.gamp.Property;
import com.azsspc.az_vault.gamp.smsl.PropertyAdapter;
import com.azsspc.az_vault.gamp.Target;
import com.azsspc.az_vault.gamp.smsl.TargetAdapter;

import java.util.ArrayList;
import java.util.Map;

import static com.azsspc.az_vault.DataLoader.as_avatars;
import static com.azsspc.az_vault.DataLoader.as_items;
import static com.azsspc.az_vault.DataLoader.as_properties;
import static com.azsspc.az_vault.DataLoader.as_targets;

public class SMSLActivity extends AppCompatActivity {
    public static final String key = "smsl_key";
    public static final String key_a = "smsl_key_a";
    public static final String key_i = "smsl_key_i";
    public static final String key_t = "smsl_key_t";
    public static final String key_p = "smsl_key_p";
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        setContentView(R.layout.activity_smsl);
        rv = findViewById(R.id.smsl);

        switch (arguments.getString(key)) {
            case key_a:
                ArrayList<Avatar> avatars = new ArrayList<>();
                for (Map.Entry<String, Avatar> entry : as_avatars.entrySet())
                    avatars.add(entry.getValue());
                rv.setAdapter(new AvatarAdapter(this, avatars));
                break;
            case key_i:
                ArrayList<Item> items = new ArrayList<>();
                for (Map.Entry<String, Item> entry : as_items.entrySet())
                    items.add(entry.getValue());
                rv.setAdapter(new ItemAdapter(this, items));
                break;
            case key_t:
                ArrayList<Target> tiles = new ArrayList<>();
                for (Map.Entry<String, Target> entry : as_targets.entrySet())
                    tiles.add(entry.getValue());
                rv.setAdapter(new TargetAdapter(this, tiles));
                break;
            case key_p:
                ArrayList<Property> properties = new ArrayList<>();
                for (Map.Entry<String, Property> entry : as_properties.entrySet())
                    properties.add(entry.getValue());
                rv.setAdapter(new PropertyAdapter(this, properties));
                break;
            default:
        }
    }

}
