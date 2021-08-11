package com.azsspc.az_vault.game_comp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.azsspc.az_vault.MainActivity.getFromJSONArray;

public class Property {
    String id;
    String name;
    String[] abilities;
    String lore;
    String visibility;
    boolean allow;
    String[] tags;

    Property(JSONObject data) throws JSONException {
        this.allow = data.getBoolean("allow");
        this.id = data.getString("id");
        this.name = data.getString("name");
        this.visibility = data.getString("visibility");
        this.lore = data.getString("lore");
        this.abilities = getFromJSONArray(data.getJSONArray("abilities"));
        this.tags = getFromJSONArray(data.getJSONArray("tags"));
    }

    public static HashMap<String, Property> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Property> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Property property = new Property(data.getJSONObject(i));
                ret.put(property.getId(), property);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return ret;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public String getLore() {
        return lore;
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
