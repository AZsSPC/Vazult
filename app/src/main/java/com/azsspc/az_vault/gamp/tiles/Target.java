package com.azsspc.az_vault.gamp.tiles;

import android.content.Context;

import com.azsspc.az_vault.R;
import com.azsspc.az_vault.gamp.Tile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Target extends Tile {
    int chance;
    String[] properties;
    String[] items;

    Target(JSONObject data) throws JSONException {
        super(data);
        this.img = R.drawable.ic_target;
        this.color = R.color.target;
        this.chance = data.getInt("chance");
        this.properties = getFromJSONArray(data.getJSONArray("properties"), true);
        this.items = getFromJSONArray(data.getJSONArray("items"), true);
    }

    public static HashMap<String, Target> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Target> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Target target = new Target(data.getJSONObject(i));
                ret.put(target.getId(), target);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return ret;
    }

    public int getChance() {
        return chance;
    }

    public String[] getItems() {
        return items;
    }


    public String getFullCenter(Context c) {
        return "  • " + c.getString(R.string.target_chance) + ": " + chance + "\n" + getCenter(c);
    }

    public String[] getProperties() {
        return properties;
    }
}
