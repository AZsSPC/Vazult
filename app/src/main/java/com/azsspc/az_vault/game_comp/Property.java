package com.azsspc.az_vault.game_comp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getCPFS;
import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Property extends Tile {
    String visibility;
    boolean allow;
    String[] tags;
    String[] items;

    public Property(JSONObject data) throws JSONException {
        super(data);
        this.allow = data.getBoolean("allow");
        this.visibility = data.getString("visibility");
        this.items = getFromJSONArray(data.getJSONArray("items"));
        this.tags = getFromJSONArray(data.getJSONArray("tags"));
    }

    public static HashMap<String, Property> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Property> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Property property = new Property(data.getJSONObject(i));
                ret.put(property.getId(), property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return ret;
    }

    public String[] getItems() {
        return items;
    }

    public String getVisibility() {
        return visibility;
    }

    public boolean isAllow() {
        return allow;
    }

    public String[] getTags() {
        return tags;
    }
}
