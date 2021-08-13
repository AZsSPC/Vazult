package com.azsspc.az_vault.gamp;

import android.content.Context;

import com.azsspc.az_vault.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Target extends Tile {
    int chance;

    Target(JSONObject data) throws JSONException {
        super(data);
        this.img = R.drawable.ic_target;
        this.color = R.color.target;
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

    public String getFullCenter(Context c) {
        return "  • " + c.getString(R.string.target_chance) + ": " + chance + "\n" + getCenter(c);
    }
}
