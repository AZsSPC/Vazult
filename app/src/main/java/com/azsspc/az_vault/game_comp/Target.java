package com.azsspc.az_vault.game_comp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Target extends Tile {
    int chance;

    Target(JSONObject data) throws JSONException {
        super(data);
        this.chance = data.getInt("chance");
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

}
