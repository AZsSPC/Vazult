package com.azsspc.az_vault.game_comp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Target {
    int chance;
    String id;
    String name;
    String lore;
    String[] abilities;

    Target(JSONObject data) throws JSONException {
        this.chance = data.getInt("chance");
        this.id = data.getString("id");
        this.name = data.getString("name");
        this.lore = data.getString("lore");
        this.abilities = getFromJSONArray(data.getJSONArray("abilities"));
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public String[] getAbilities() {
        return abilities;
    }
}
