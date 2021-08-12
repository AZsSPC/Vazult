package com.azsspc.az_vault.game_comp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
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
    String custom_class;
    JSONObject data;
    boolean custom;

    public Property(JSONObject data) throws JSONException {
        this.allow = data.getBoolean("allow");
        this.id = data.getString("id");
        this.name = data.getString("name");
        this.visibility = data.getString("visibility");
        this.lore = data.getString("lore");
        this.abilities = getFromJSONArray(data.getJSONArray("abilities"));
        this.tags = getFromJSONArray(data.getJSONArray("tags"));
        this.custom = false;
        try {
            this.custom_class = data.getString("class");
            data.remove("class");
            data.remove("allow");
            data.remove("id");
            data.remove("name");
            data.remove("visibility");
            data.remove("lore");
            data.remove("abilities");
            data.remove("tags");
            this.data = data;
            this.custom = true;
        } catch (Exception ignored) {
        }
    }

    public static HashMap<String, Object> createArray(JSONArray data) {
        int rel = data.length();
        HashMap<String, Object> ret = new HashMap<>();
        for (int i = 0; i < rel; i++)
            try {
                Property property = new Property(data.getJSONObject(i));
                Object prop = property;
                if (property.isCustom()) try {
                    prop = Class.forName("com.azsspc.az_vault.game_comp.special" + property.getCustomClass())
                            .getConstructor(Property.class, JSONObject.class)
                            .newInstance(property, property.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("asdasd", "Class is " + prop.getClass());
                ret.put(property.getId(), prop);
            } catch (Exception e) {
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

    public JSONObject getData() {
        return data;
    }

    public boolean isCustom() {
        return custom;
    }

    public String getCustomClass() {
        return custom_class;
    }
}
